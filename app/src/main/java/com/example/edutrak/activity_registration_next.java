package com.example.edutrak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class activity_registration_next extends AppCompatActivity {
    public static final String TAG = "TAG";
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    public Button cancl_button;
    public Button register_button;
    public TextInputLayout firstname;
    public TextInputLayout lastname;
    public TextInputLayout phone_num;
    public TextInputLayout middle_init;
    public TextInputLayout university;
    public TextInputLayout classification;
    String userID = "";
    String username = "";
    String password = "";
    String email = "";
    String gender = "";

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
        university = findViewById(R.id.university_layout);
        classification = findViewById(R.id.category_layout);

        cancl_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity_registration_next.this,"Registration Cancelled!",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(activity_registration_next.this,MainActivity.class);
                startActivity(intent);
            }
        });

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();
        if (fAuth.getCurrentUser() != null) {
            Intent intent = new Intent(activity_registration_next.this, activity_homepage.class);
        }
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

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            username = bundle.getString("username");
            password = bundle.getString("password");
            email = bundle.getString("email");
            gender = bundle.getString("gender");
        }
        fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(activity_registration_next.this,"Registration Complete!",Toast.LENGTH_LONG).show();
                    userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fstore.collection("users").document(userID);

                    String first_name = firstname.getEditText().getText().toString().trim();
                    String middle = middle_init.getEditText().getText().toString().trim();
                    String last_name = lastname.getEditText().getText().toString().trim();
                    String phone = phone_num.getEditText().getText().toString().trim();
                    String university_value =university.getEditText().getText().toString().trim();
                    String category =classification.getEditText().getText().toString().trim();

                    Map<String,Object> user = new HashMap<>();
                    user.put("username",username);
                    user.put("email",email);
                    user.put("firstName",first_name);
                    user.put("middleIntial",middle);
                    user.put("lastName",last_name);
                    user.put("gender",gender);
                    user.put("phone",phone);
                    user.put("university",university_value);
                    user.put("classification",category);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "onSuccess: User Profile is created for"+userID);
                        }
                    });

                    Intent intent =new Intent(activity_registration_next.this,ScheduleScanner.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(activity_registration_next.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}