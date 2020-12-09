package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.telephony.SmsManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
public class after_manage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_manage);




        ActivityCompat.requestPermissions(after_manage.this, new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);


        Button En = findViewById(R.id.enable_notification);
        En.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(after_manage.this, "Notification Enabled", Toast.LENGTH_SHORT).show();



                String message = "Hello this is a test reminder";
                String number = "6822418171";
                SmsManager mySmsManager = SmsManager.getDefault();
                mySmsManager.sendTextMessage(number,null, message, null, null);

                Intent intent = new Intent(after_manage.this, after_enable
                        .class);
                startActivity(intent);








                Button Di = findViewById(R.id.disable_notification);
                Di.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(after_manage.this, "Notification Disabled", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(after_manage.this, after_enable
                                .class);
                        startActivity(intent);
                    }
                });
                return;



            }
        });





    }

}

