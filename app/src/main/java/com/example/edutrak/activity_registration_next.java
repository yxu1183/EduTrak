package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class activity_registration_next extends AppCompatActivity {

    public Button cancl_button;
    public Button register_button;
    public TextInputLayout firstname;
    public TextInputLayout lastname;
    public TextInputLayout phone_num;
    public TextInputLayout middle_init;

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
                Toast.makeText(activity_registration_next.this,"Registration Complete!",Toast.LENGTH_LONG).show();
                Intent intent =new Intent(activity_registration_next.this,activity_homepage.class);
                startActivity(intent);
    }

}