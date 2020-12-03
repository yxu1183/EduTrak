package com.example.edutrak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Calendar extends AppCompatActivity {
    private ImageButton createEventBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    RecyclerView todolist;
    ArrayList<EventHelperClass> list;
    TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.calendar);

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
                        return true;
                    case R.id.schedulescan:
                        startActivity(new Intent(getApplicationContext()
                                , ScheduleScanner.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext()
                                , Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        todolist = findViewById(R.id.todolist);
        todolist.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<EventHelperClass>();

        reference = FirebaseDatabase.getInstance().getReference().child("Events");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren())
                {
                    EventHelperClass p = dataSnapshot1.getValue(EventHelperClass.class);
                    list.add(p);
                    taskAdapter = new TaskAdapter(Calendar.this, list);
                    todolist.setAdapter(taskAdapter);
                    taskAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });

        createEventBtn = (ImageButton) findViewById(R.id.create_event_button);
        createEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                openNewEvent();
            }
        });
    }

    public void openNewEvent(){
        Intent intent = new Intent(this, NewEvent.class);
        startActivity(intent);
    }
}