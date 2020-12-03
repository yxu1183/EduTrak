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
        email_layout = findViewById(R.id.layout_Editemail);
        firstname_layout = findViewById(R.id.layout_EditFirName);
        middle_layout = findViewById(R.id.editinfo_middleInital);
        lastname_layout = findViewById(R.id.editinfo_Lastname);
        phone_layout = findViewById(R.id.editinfo_phoneNumber);
        univeristy_layout = findViewById(R.id.editinfo_university);
        classification_layout = findViewById(R.id.editinfo_classification);

        Bundle bundle = getIntent().getExtras();
        new_username = bundle.getString("username");
        email = bundle.getString("email");
        firstName = bundle.getString("firstName");
        lastName = bundle.getString("lastName");
        middleInit = bundle.getString("middleInit");
        phone = bundle.getString("phone");
        university = bundle.getString("university");
        classification = bundle.getString("classification");

        username_layout.getEditText().setText(new_username);
        email_layout.getEditText().setText(email);
        firstname_layout.getEditText().setText(firstName);
        middle_layout.getEditText().setText(middleInit);
        lastname_layout.getEditText().setText(lastName);
        phone_layout.getEditText().setText(phone);
        univeristy_layout.getEditText().setText(university);
        classification_layout.getEditText().setText(classification);

        String check_middleInit = middle_layout.getEditText().getText().toString();
        if (check_middleInit.isEmpty() || check_middleInit == null) {
            check_middleInit = "Middle Initial";
            middle_layout.getEditText().setText(check_middleInit);
        }


        String check_universitylayout = univeristy_layout.getEditText().getText().toString();
        if (check_universitylayout.isEmpty() || check_universitylayout == null) {
            check_universitylayout = "University";
            univeristy_layout.getEditText().setText(check_universitylayout);
        }

        String check_classifylayout = classification_layout.getEditText().getText().toString();
        if (check_classifylayout.isEmpty() || check_classifylayout == null) {
            check_classifylayout = "Classification";
            classification_layout.getEditText().setText(check_classifylayout);
        }


        CancelButton = (Button) findViewById(R.id.new_button);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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