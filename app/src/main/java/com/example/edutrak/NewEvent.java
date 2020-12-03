package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;


public class NewEvent extends AppCompatActivity {
    private Button cancelbtn;
    private Button savebtn;
    TextInputLayout eventTitleInput, eventDateInput, eventTimeInput, eventCourseInput;
    TextInputEditText eventDescInput;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Integer eventNum = new Random().nextInt();
    String eventKey = Integer.toString(eventNum);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        cancelbtn = (Button) findViewById(R.id.cancel_button);
        savebtn = (Button) findViewById(R.id.save_button);
        eventTitleInput = findViewById(R.id.event_title_layout);
        eventDateInput = findViewById(R.id.event_date_layout);
        eventTimeInput = findViewById(R.id.event_time_layout);
        eventDescInput = findViewById(R.id.text_event_desc);
        eventCourseInput = findViewById(R.id.course_code_layout);

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                closeWindow();
            }
        });


        //Save event data in Firebase on new_button click
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Events");

                String title = eventTitleInput.getEditText().getText().toString();
                String courseNumber = eventCourseInput.getEditText().getText().toString();
                String date = eventDateInput.getEditText().getText().toString();
                String time = eventTimeInput.getEditText().getText().toString();
                String description = eventDescInput.getText().toString();

                EventHelperClass eventHelperClass = new EventHelperClass(title, courseNumber, date, time, description, eventKey);

                reference.child(eventKey).setValue(eventHelperClass);
                closeWindow();
            }
        });
    }

    public void closeWindow(){
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
}