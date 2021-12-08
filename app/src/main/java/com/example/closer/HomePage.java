package com.example.closer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userEmail= null;

            } else {
                userEmail= extras.getString("emailOfTheUser");

            }
        } else {
            userEmail= (String) savedInstanceState.getSerializable("emailOfTheUser");

        }

        TextView user= findViewById(R.id.usertxt);
        TextView partner=findViewById(R.id.partnertxt);
        user.setText(userEmail);

    }



}