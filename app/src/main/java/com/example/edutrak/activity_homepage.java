package com.example.edutrak;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class activity_homepage extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    TextView titlepage, subtitlepage, endpage;
    RecyclerView todolist;
    ArrayList<EventHelperClass> list;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.tasks);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tasks:
                        return true;
                    case R.id.calendar:
                        startActivity(new Intent(getApplicationContext()
                                , Calendar.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.schedulescan:
                        startActivity(new Intent(getApplicationContext()
                                , ScheduleScanner.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext()
                                , Settings.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

        titlepage = findViewById(R.id.titlepage);
        subtitlepage = findViewById(R.id.subtitle);
        endpage = findViewById(R.id.endpage);

        todolist = findViewById(R.id.todolist);
        todolist.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<EventHelperClass>();

        //get data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Events");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    EventHelperClass p = dataSnapshot1.getValue(EventHelperClass.class);
                    list.add(p);
                    taskAdapter = new TaskAdapter(activity_homepage.this, list);
                    todolist.setAdapter(taskAdapter);
                    taskAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            finishAffinity();
        } else {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }


}