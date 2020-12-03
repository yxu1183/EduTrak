
package com.example.edutrak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Settings extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore mFirestore;
    String userId = "";
    String username = "";
    String email = "";
    String firstName = "";
    String lastName = "";
    String middleInit = "";
    String phone = "";
    String university = "";
    String classification = "";
    Bundle bundle = new Bundle();
    private static final String FIRE_LOG = "Fire_log";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirestore = FirebaseFirestore.getInstance();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.settings);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tasks:
                        startActivity(new Intent(getApplicationContext()
                                , activity_homepage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendar:
                        startActivity(new Intent(getApplicationContext()
                                , Calendar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.schedulescan:
                        startActivity(new Intent(getApplicationContext()
                                , ScheduleScanner.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        return true;
                }
                return false;
            }
        });

        Button logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember","false");
                editor.apply();
                mFirebaseAuth.signOut();
                Intent intent = new Intent(Settings.this, MainActivity
                        .class);
                startActivity(intent);
            }
        });

        Button edit_info = findViewById(R.id.editinfo_btn);
        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = mFirebaseAuth.getCurrentUser().getUid();
                mFirestore.collection("users").document(userId).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful())
                        {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            username = documentSnapshot.getString("username");
                            email = documentSnapshot.getString("email");
                            firstName = documentSnapshot.getString("firstName");
                            lastName = documentSnapshot.getString("lastName");
                            middleInit = documentSnapshot.getString("middleInitial");
                            phone = documentSnapshot.getString("phone");
                            university = documentSnapshot.getString("university");
                            classification = documentSnapshot.getString("classification");
                        }
                        else
                        {
                            Log.d(FIRE_LOG,"Error: " +task.getException().getMessage());
                        }
                        bundle.putString("username",username);
                        bundle.putString("email",email);
                        bundle.putString("firstName",firstName);
                        bundle.putString("lastName",lastName);
                        bundle.putString("middleInit",middleInit);
                        bundle.putString("phone",phone);
                        bundle.putString("university",university);
                        bundle.putString("classification",classification);
                        Intent intent = new Intent(Settings.this, editprofile
                                .class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
            }
        });
    }
}