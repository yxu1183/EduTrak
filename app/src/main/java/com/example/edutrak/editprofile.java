package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class editprofile extends AppCompatActivity {
    Button CancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);
        CancelButton = (Button) findViewById(R.id.new_button);
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editprofile.this, activity_homepage
                        .class);
                startActivity(intent);
                Toast.makeText(editprofile.this, "Cancelled!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}