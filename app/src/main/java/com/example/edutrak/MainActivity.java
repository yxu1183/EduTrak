package com.example.edutrak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth fAuth;
    public TextView textview;
    public TextView forgot_paswword;
    public Button loginbtn;
    private TextInputLayout textemail;
    private TextInputLayout textinputpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textemail = findViewById(R.id.loginemail_layout);
        textinputpassword = findViewById(R.id.password_layout);

        textview = (TextView) findViewById(R.id.ediview_signup);
        String text = "Sign Up";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, activity_registration
                        .class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.RED);
            }
        };
        ss.setSpan(clickableSpan, 0, 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textview.setText(ss);
        textview.setMovementMethod(LinkMovementMethod.getInstance());

        forgot_paswword = (TextView) findViewById(R.id.textview_forgotpassword);
        String password = "Forgot Password?";
        SpannableString newss = new SpannableString(password);
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, activity_forgotpassword.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLACK);
            }
        };

        newss.setSpan(clickableSpan1, 0, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        forgot_paswword.setText(newss);
        forgot_paswword.setMovementMethod(LinkMovementMethod.getInstance());

        loginbtn = (Button) findViewById(R.id.button_login);

        fAuth = FirebaseAuth.getInstance();
    }

    private boolean validateEmail() {
        String input_email = textemail.getEditText().getText().toString().trim();

        if (input_email.isEmpty()) {
            textemail.setError("Email cannot be empty.");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(input_email).matches()) {
            textemail.setError("Please enter a valid email address");
            return false;
        } else {
            textemail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String input_password = textinputpassword.getEditText().getText().toString().trim();

        if (input_password.isEmpty()) {
            textinputpassword.setError("Password cannot be empty.");
            return false;
        } else {
            textinputpassword.setError(null);
            return true;
        }
    }

    public void inputconfirm(View v) {
        if (!validateEmail() | !validatePassword()) {
            return;
        } else {
            String input_email = textemail.getEditText().getText().toString().trim();
            String input_password = textinputpassword.getEditText().getText().toString().trim();
                fAuth.signInWithEmailAndPassword(input_email,input_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Intent intent = new Intent(MainActivity.this, activity_homepage.class);
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "Login Successful.", Toast.LENGTH_LONG).show();
                        }
                        else
                            Toast.makeText(MainActivity.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        }
    }


