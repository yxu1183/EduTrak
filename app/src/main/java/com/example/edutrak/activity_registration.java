package com.example.edutrak;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class activity_registration extends AppCompatActivity {

    public Button cancel_button;
    public Button next_button;
    private TextInputLayout signup_username;
    private TextInputLayout signup_email;
    private TextInputLayout signup_password;
    private RadioGroup radiogroup;
    private RadioButton radioButton;

    private static final Pattern PATTERN_PASSWORD=
            Pattern.compile("^" +
                    "(?=.*[0-9])" +  //atleast 1 digit
                    "(?=.*[a-z])" +  //atleast 1 lowercase
                    "(?=.*[A-Z])" +  //atleast 1 uppercase
                    "(?=.*[a-zA-Z])" + //anyletter
                    "(?=.*[!*@#$%^&+=])" + //atleast 1 special character
                    "(?=\\S+$)" + //no white spaces
                    ".{4,}" + //atleast 4 characters
                    "$");
    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        signup_username = findViewById(R.id.loginemail_layout);
        signup_email = findViewById(R.id.email_layout);
        signup_password = findViewById(R.id.password_layout);

        cancel_button = (Button) findViewById(R.id.canc_btn);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(activity_registration.this,"Registration Cancelled!",Toast.LENGTH_LONG).show();
            }
        });

        next_button = (Button)findViewById(R.id.next_btn);
        radiogroup = (RadioGroup) findViewById(R.id.radioGroup);
    }

    private boolean usernamevalidate()
    {
        String username = signup_username.getEditText().getText().toString().trim();
        if(username.isEmpty())
        {
            signup_username.setError("Username cannot be empty.");
            return false;
        }
        else if (username.length()>= 16)
        {
            signup_username.setError("Username too long.");
            return false;
        }
        else
        {
            signup_username.setError(null);
            return true;
        }
    }

    private boolean emailvalidate()
    {
        String email = signup_email.getEditText().getText().toString().trim();
        if(email.isEmpty())
        {
            signup_email.setError("Email cannot be empty.");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            signup_email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            signup_email.setError(null);
            return true;
        }
    }

    private boolean passwordvalidate()
    {
        String password = signup_password.getEditText().getText().toString().trim();
        if(password.isEmpty())
        {
            signup_password.setError("Password cannot be empty.");
            return false;
        }
        else if (!PATTERN_PASSWORD.matcher(password).matches())
        {
            signup_password.setError("Password too weak.");
            return false;
        }
        else
        {
            signup_password.setError(null);
            return true;
        }
    }

    public void validateinput(View v)
    {
        if (!usernamevalidate() | !passwordvalidate() | !emailvalidate())
        {
            return;
        }
        String username = signup_username.getEditText().getText().toString().trim();
        String email = signup_email.getEditText().getText().toString().trim();
        String password = signup_password.getEditText().getText().toString().trim();

        int selectedId = radiogroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedId);
        String radio_value = (String) radioButton.getText();

        Bundle bundle = new Bundle();
        bundle.putString("username",username);
        bundle.putString("email",email);
        bundle.putString("password",password);
        bundle.putString("gender",radio_value);

        Intent intent = new Intent(activity_registration.this,activity_registration_next.class);
        intent.putExtras(bundle);
        startActivity(intent);
        Toast.makeText(activity_registration.this,"",Toast.LENGTH_LONG).show();
    }
}
