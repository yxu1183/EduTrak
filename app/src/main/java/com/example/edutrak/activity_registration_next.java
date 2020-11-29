package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_registration_next extends AppCompatActivity {
    DatabaseReference reff;
    Users user;
    public Button cancl_button;
    public Button register_button;
    public TextInputLayout firstname;
    public TextInputLayout lastname;
    public TextInputLayout phone_num;
    public TextInputLayout middle_init;
    public TextInputLayout university;
    public TextInputLayout classification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_next);

        cancl_button = findViewById(R.id.can_btn);
        register_button = findViewById(R.id.register_btn);
        firstname = findViewById(R.id.firstname_layout);
        lastname = findViewById(R.id.lastname_layout);
        phone_num = findViewById(R.id.phone_layout);
        middle_init = findViewById(R.id.midddle_layout);
        university = findViewById(R.id.university_layout);
        classification = findViewById(R.id.category_layout);

        user = new Users();
        reff = FirebaseDatabase.getInstance().getReference().child("Users");

        cancl_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_registration_next.this,"Registration Cancelled!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity_registration_next.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }


    private boolean validatefirstname()
    {
        String first_name = firstname.getEditText().getText().toString().trim();

        if(first_name.isEmpty())
        {
            firstname.setError("First Name cannot be empty.");
            return false;
        }
        else if (first_name.length() <= 1)
        {
            firstname.setError("First Name too short.");
            return false;
        }
        else
        {
            firstname.setError(null);
            return true;
        }
    }

    private boolean validatemiddleinitial() {
        String middle = middle_init.getEditText().getText().toString().trim();

        if (!middle.isEmpty() && middle.length() > 1) {
                middle_init.setError("Middle Initial too long.");
                return false;
        }
        else {
            middle_init.setError(null);
            return true;
        }
    }

    private boolean validatelastname()
    {
        String last_name = lastname.getEditText().getText().toString().trim();

        if(last_name.isEmpty())
        {
            lastname.setError("Last Name cannot be empty.");
            return false;
        }
        else if (last_name.length() <= 1)
        {
            lastname.setError("Last Name too short.");
            return false;
        }
        else
        {
            lastname.setError(null);
            return true;
        }
    }

    private boolean validatephonenumber()
    {
        String phone = phone_num.getEditText().getText().toString().trim();

        if(phone.isEmpty())
        {
            phone_num.setError("Phone Number cannot be empty.");
            return false;
        }
        else if (!Patterns.PHONE.matcher(phone).matches())
        {
            phone_num.setError("Please enter a valid phone number.");
            return false;
        }
        else
        {
            phone_num.setError(null);
            return true;
        }
    }

    public void validaationInput(View v)
    {
        if(!validatefirstname() | !validatelastname() | !validatephonenumber() | !validatemiddleinitial())
        {
            return;
        }
            String username = "";
            String password = "";
            String email = "";
            String gender = "";

            Bundle bundle = getIntent().getExtras();
            if (bundle!=null){
                username = bundle.getString("username");
                password = bundle.getString("email");
                email = bundle.getString("password");
                gender = bundle.getString("gender");
            }

            String first_name = firstname.getEditText().getText().toString().trim();
            String middle = middle_init.getEditText().getText().toString().trim();
            String last_name = lastname.getEditText().getText().toString().trim();
            String phone = phone_num.getEditText().getText().toString().trim();
            String university_value =university.getEditText().getText().toString().trim();
            String category =classification.getEditText().getText().toString().trim();


            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setGender(gender);
            user.setFirst_name(first_name);
            user.setMiddle_init(middle);
            user.setLast_name(last_name);
            user.setPhone_num(phone);
            user.setUniversity(university_value);
            user.setClassification(category);
            reff.push().setValue(user);
            
            Toast.makeText(activity_registration_next.this,"Registration Complete!",Toast.LENGTH_LONG).show();
            Intent intent =new Intent(activity_registration_next.this,activity_homepage.class);
            startActivity(intent);
    }

}