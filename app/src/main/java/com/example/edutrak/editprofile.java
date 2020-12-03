package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;

public class editprofile extends AppCompatActivity {
    Button CancelButton;
    Button changeButton;
    TextInputLayout username_layout;
    TextInputLayout email_layout;
    TextInputLayout firstname_layout;
    TextInputLayout lastname_layout;
    TextInputLayout middle_layout;
    TextInputLayout phone_layout;
    TextInputLayout univeristy_layout;
    TextInputLayout classification_layout;
    String new_username = "";
    String email = "";
    String firstName = "";
    String lastName = "";
    String middleInit = "";
    String phone = "";
    String university = "";
    String classification = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        username_layout = findViewById(R.id.layout_editinfo_username);


        //String put_username =  username_layout.getEditText().getText().toString().trim();
        //username_layout.getEditText().setText(username);
        CancelButton = (Button) findViewById(R.id.new_button);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    //new_username = intent.getStringExtra("userName");
//                    email = bundle.getString("email");
//                    firstName = bundle.getString("firstName");
//                    lastName = bundle.getString("lastName");
//                    middleInit = bundle.getString("middleInit");
//                    phone = bundle.getString("phone");
//                    university = bundle.getString("university");
//                    classification = bundle.getString("classification");

                //System.out.println("My name is" +new_username);
                //System.out.println(email);
                Intent intent = new Intent(editprofile.this, ScheduleScanner
                        .class);
                startActivity(intent);
                Toast.makeText(editprofile.this, "Cancelled!", Toast.LENGTH_SHORT).show();
            }
        });

        changeButton = (Button) findViewById(R.id.button2);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editprofile.this, ScheduleScanner
                        .class);
                startActivity(intent);
            }
        });

    }
}