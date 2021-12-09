package com.example.closer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
String userEmail;
String partnerEmail;

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
                partnerEmail= extras.getString("emailOfThePartner");
                Log.i("a",userEmail);
                Log.i("a",partnerEmail);
            }
        } else {
            userEmail= (String) savedInstanceState.getSerializable("emailOfTheUser");
            partnerEmail= (String) savedInstanceState.getSerializable("emailOfThePartner");
        }

        TextView user= findViewById(R.id.usertxt);
        TextView partner=findViewById(R.id.partnertxt);
        user.setText(userEmail);
        partner.setText(partnerEmail);



    }


    public void checkCalender(View view) {
        Intent intent = new Intent(getApplicationContext(), Calender.class);
        intent.putExtra("emailOfTheUser", userEmail);
        intent.putExtra("emailOfThePartner", partnerEmail);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}