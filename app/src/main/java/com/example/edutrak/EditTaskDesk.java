package com.example.edutrak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    EditText edit_title, edit_date, edit_desc, edit_course;
    Button btn_save, btn_delete;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        edit_title = findViewById(R.id.edit_title);
        edit_desc = findViewById(R.id.edit_desc);
        edit_date = findViewById(R.id.edit_date);
        edit_course = findViewById(R.id.edit_course);

        btn_save = findViewById(R.id.saveupdate_btn);
        btn_delete = findViewById(R.id.delete_btn);

        edit_title.setText(getIntent().getStringExtra("title"));
        edit_desc.setText(getIntent().getStringExtra("description"));
        edit_date.setText(getIntent().getStringExtra("date"));
        edit_course.setText(getIntent().getStringExtra("course"));
        final String key_event = getIntent().getStringExtra("key_event");


        //get reference from database
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference().child("Events").child(key_event);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Intent a = new Intent(EditTaskDesk.this, activity_homepage.class);
                            startActivity(a);
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Delete Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().child("title").setValue(edit_title.getText().toString());
                        snapshot.getRef().child("date").setValue(edit_date.getText().toString());
                        snapshot.getRef().child("description").setValue(edit_desc.getText().toString());
                        snapshot.getRef().child("course").setValue(edit_course.getText().toString());

                        Intent a = new Intent(EditTaskDesk.this, activity_homepage.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

        });
    }
}
