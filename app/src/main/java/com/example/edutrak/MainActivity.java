package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public TextView textview;
    public Button loginbtn;
    private TextInputLayout textinputusername;
    private  TextInputLayout textinputpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textinputusername =findViewById(R.id.username_layout);
        textinputpassword =findViewById(R.id.password_layout);

        textview = (TextView) findViewById(R.id.ediview_signup);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,activity_registration.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateUserName()
    {
        String input_username = textinputusername.getEditText().getText().toString().trim();

        if(input_username.isEmpty())
        {
            textinputusername.setError("Username cannot be empty.");
            return false;
        }
        else
        {
            textinputusername.setError(null);
            return true;
        }
    }

    private boolean validatePassword()
    {
        String input_password = textinputpassword.getEditText().getText().toString().trim();

        if(input_password.isEmpty())
        {
            textinputpassword.setError("Password cannot be empty.");
            return false;
        }
        else
        {
            textinputpassword.setError(null);
            return true;
        }
    }

    public void inputconfirm(View v)
    {
        if(!validateUserName() |!validatePassword())
        {
            return;
        }

        String input_message = "Username: " + textinputusername.getEditText().getText().toString();
        input_message += "\n";
        input_message += "Password: " + textinputusername.getEditText().getText().toString();
        Toast.makeText(this, input_message, Toast.LENGTH_SHORT).show();
    }
}
