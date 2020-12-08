package com.example.edutrak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

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
    String getUsername = "";
    String getEmail = "";
    String getFirstName = "";
    String getLastName = "";
    String getMiddleInit = "";
    String getPhone = "";
    String getUniversity = "";
    String getClassification = "";
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    FirebaseUser user;

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

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        user = fAuth.getCurrentUser();

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

        getMiddleInit = middle_layout.getEditText().getText().toString();
        if (getMiddleInit.isEmpty() || getMiddleInit == null) {
            getMiddleInit = "Middle Initial";
            middle_layout.getEditText().setText(getMiddleInit);
        }

        getUniversity = univeristy_layout.getEditText().getText().toString();
        if (getUniversity.isEmpty() || getUniversity == null) {
            getUniversity = "University";
            univeristy_layout.getEditText().setText(getUniversity);
        }

        getClassification = classification_layout.getEditText().getText().toString();
        if (getClassification.isEmpty() || getClassification == null) {
            getClassification = "Classification";
            classification_layout.getEditText().setText(getClassification);
        }

        getUsername = username_layout.getEditText().getText().toString();
        getFirstName = firstname_layout.getEditText().getText().toString();
        getLastName = lastname_layout.getEditText().getText().toString();
        getEmail = email_layout.getEditText().getText().toString();
        getPhone = phone_layout.getEditText().getText().toString();

        CancelButton = (Button) findViewById(R.id.new_button);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(editprofile.this, "Cancelled!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(editprofile.this, Settings
                        .class);
                startActivity(intent);
            }
        });

        changeButton = (Button) findViewById(R.id.button2);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUsername = username_layout.getEditText().getText().toString();
                getFirstName = firstname_layout.getEditText().getText().toString();
                getLastName = lastname_layout.getEditText().getText().toString();
                getEmail = email_layout.getEditText().getText().toString();
                getPhone = phone_layout.getEditText().getText().toString();
                getMiddleInit = middle_layout.getEditText().getText().toString();
                getUniversity = univeristy_layout.getEditText().getText().toString();
                getClassification = classification_layout.getEditText().getText().toString();
                if (getUsername.isEmpty() || getFirstName.isEmpty()|| getLastName.isEmpty() || getEmail.isEmpty() || getPhone.isEmpty())
                {
                    Toast.makeText(editprofile.this, "One or many fields are empty.", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(getEmail).matches())
                {
                    Toast.makeText(editprofile.this, "Invalid Email Address!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Patterns.PHONE.matcher(getPhone).matches())
                {
                    Toast.makeText(editprofile.this, "Invalid Phone Number!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(getUsername.length() >= 16)
                {
                    Toast.makeText(editprofile.this, "Username too long!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (getFirstName.length() <= 1 || getLastName.length() <= 1)
                {
                    Toast.makeText(editprofile.this, "First Name or Last Name too short!", Toast.LENGTH_LONG).show();
                    return;
                }
                user.updateEmail(getEmail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        DocumentReference docRef = fstore.collection("users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("username",getUsername);
                        edited.put("email",getEmail);
                        edited.put("firstName",getFirstName);
                        edited.put("lastName",getLastName);
                        if (getMiddleInit == "Middle Initial")
                        {
                            getMiddleInit = "";
                        }
                        edited.put("middleIntial",getMiddleInit);
                        edited.put("phone",getPhone);
                        if(getUniversity == "University")
                        {
                            getUniversity = "";
                        }
                        edited.put("university",getUniversity);
                        if (getClassification == "Classification")
                        {
                            getClassification = "";
                        }
                        edited.put("classification",getClassification);
                        docRef.update(edited);
                        Toast.makeText(editprofile.this, "Edited fields are changed.",Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(editprofile.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}