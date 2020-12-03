package com.example.edutrak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ScheduleScanner extends AppCompatActivity {

    private Button ChooseFile;
    private final int CHOOSE_PDF_FROM_DEVICE = 1001;
    private static final String TAG = "ScheduleScanner";
    private TextView filepath;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_scanner);

        ChooseFile=findViewById(R.id.ChooseFile);
        filepath=findViewById(R.id.filepath);
        //content_et=findViewById(R.id.file_content_et);
        ChooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callChooseFileFromDevice();

            }
        });




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.schedulescan);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tasks:
                        startActivity(new Intent(getApplicationContext()
                                , activity_homepage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.calendar:
                        startActivity(new Intent(getApplicationContext()
                                , Calendar.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.schedulescan:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext()
                                , Settings.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
    private void callChooseFileFromDevice(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent,CHOOSE_PDF_FROM_DEVICE);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent resultData){
        super.onActivityResult(requestCode,resultCode,resultData);
        if(requestCode==CHOOSE_PDF_FROM_DEVICE&&resultCode==RESULT_OK){
            //The result data contains a URI for the document or directory that
            //the user selected.
            if(resultData!=null){
                Log.d(TAG,"onActvityResult"+resultData.getData());
                filepath.setText("File Path:"+resultData.getData());
            }
        }
    }


}


