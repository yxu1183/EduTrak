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
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    public TextView textview;
    public TextView forgot_paswword;
    public Button loginbtn;
    private TextInputLayout textinputusername;
    private TextInputLayout textinputpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textinputusername = findViewById(R.id.username_layout);
        textinputpassword = findViewById(R.id.password_layout);

        textview = (TextView) findViewById(R.id.ediview_signup);
        String text = "Sign Up";
        SpannableString ss = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, activity_registration_next.class);
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
    }

    private boolean validateUserName() {
        String input_username = textinputusername.getEditText().getText().toString().trim();

        System.out.printf("gfhghgfhfgh.....%s, ", input_username);

        if (input_username.isEmpty()) {
            textinputusername.setError("Username cannot be empty.");
            return false;
        } else {
            textinputusername.setError(null);
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
        if (!validateUserName() | !validatePassword()) {
            return;
        }
        else {
            Intent intent = new Intent(MainActivity.this, activity_homepage.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_LONG).show();
                }
        }
    }

