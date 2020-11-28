package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class activity_forgotpassword extends AppCompatActivity {

    private TextInputLayout forgot_email;
    private TextInputLayout forgot_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);

        forgot_email = findViewById(R.id.forgoremail_layout);
        forgot_phone = findViewById(R.id.forgotphone_layout);
    }

    private boolean emailVerification()
    {
        String email = forgot_email.getEditText().getText().toString().trim();
        if(email.isEmpty())
        {
            forgot_email.setError("Email cannot be empty.");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            forgot_email.setError("Please enter a valid email address");
            return false;
        }
        else
        {
            forgot_email.setError(null);
            return true;
        }
    }

    private boolean phoneVerification()
    {
        String phone = forgot_phone.getEditText().getText().toString().trim();

        if(phone.isEmpty())
        {
            forgot_phone.setError("Phone Number cannot be empty.");
            return false;
        }
        else if (!Patterns.PHONE.matcher(phone).matches())
        {
            forgot_phone.setError("Please enter a valid phone number.");
            return false;
        }
        else
        {
            forgot_phone.setError(null);
            return true;
        }
    }

    public void forgotvalidation(View v)
    {
        if(!emailVerification() | !phoneVerification())
        {
            return;
        }
        Toast.makeText(activity_forgotpassword.this,"Reset link sent to your email.",Toast.LENGTH_LONG).show();
        Intent intent =new Intent(activity_forgotpassword.this,MainActivity.class);
        startActivity(intent);
    }


}