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
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class activity_registration extends AppCompatActivity {
    public Button picture_button;
    public ImageView picture_view;
    public Button cancel_button;
    public Button next_button;
    private TextInputLayout signup_username;
    private TextInputLayout signup_email;
    private TextInputLayout signup_password;
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

        signup_username = findViewById(R.id.username_layout);
        signup_email = findViewById(R.id.email_layout);
        signup_password = findViewById(R.id.password_layout);

        picture_button = (Button) findViewById(R.id.chose_image_btn);
        picture_view = (ImageView) findViewById(R.id.chose_image_view);

        picture_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(activity_registration.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(activity_registration.this, "Permission is already granted!",
                            Toast.LENGTH_SHORT).show();
                    pickImageFromGallery();
                } else {
                    storagePermissionRequest();
                }
            }
        });

        cancel_button = (Button) findViewById(R.id.canc_btn);
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Toast.makeText(activity_registration.this,"Registration Cancelled!",Toast.LENGTH_LONG).show();
            }
        });

           next_button = (Button)findViewById(R.id.next_btn);
    }
    private void storagePermissionRequest()
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                new AlertDialog.Builder(this)
                        .setTitle("Permission Required")
                        .setMessage("This permission is required to access media and files.")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(activity_registration.this,
                                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
            else
                {
                ActivityCompat.requestPermissions(this,
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
            }

        }

    private void pickImageFromGallery()
    {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE)  {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                pickImageFromGallery();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode ==IMAGE_PICK_CODE)
        {
            picture_view.setImageURI(data.getData());
        }
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
        if(!usernamevalidate() | !passwordvalidate() | !emailvalidate())
        {
            return;
        }

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_registration.this,activity_registration_next.class);
                startActivity(intent);
                Toast.makeText(activity_registration.this,"",Toast.LENGTH_LONG).show();
            }
        });

    }
}
