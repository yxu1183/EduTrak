package com.example.edutrak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewEvent extends AppCompatActivity {
    private Button cancelbtn;
    private Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        cancelbtn = (Button) findViewById(R.id.cancel_button);
        savebtn = (Button) findViewById(R.id.save_button);

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                cancelEvent();
            }
        });
    }

    public void cancelEvent(){
        Intent intent = new Intent(this, Calendar.class);
        startActivity(intent);
    }
}