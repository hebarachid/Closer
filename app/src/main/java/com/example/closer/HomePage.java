package com.example.closer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HomePage extends AppCompatActivity {
String userEmail;
String partnerEmail;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
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
String currentDate=formatter.format(date);
    public void heartTap(View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    final String partnerEmailTxt="A";
                    databaseReference.child("Notifications").child("heart").child(userEmail).setValue("sends");
                    databaseReference.child("Notifications").child("heart").child(partnerEmail).setValue("receives");
                    databaseReference.child("Notifications").child("heart").child("Date").setValue(currentDate);
                    Log.i("success","success");
                    finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void hugTap(View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String partnerEmailTxt="A";
                databaseReference.child("Notifications").child("hug").child(userEmail).setValue("sends");
                databaseReference.child("Notifications").child("hug").child(partnerEmail).setValue("receives");
                databaseReference.child("Notifications").child("hug").child("Date").setValue(currentDate);
                Log.i("success","success");
                finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void pokeTap(View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String partnerEmailTxt="A";
                databaseReference.child("Notifications").child("Poke").child(userEmail).setValue("sends");
                databaseReference.child("Notifications").child("Poke").child(partnerEmail).setValue("receives");
                databaseReference.child("Notifications").child("Poke").child("Date").setValue(currentDate);
                Log.i("success","success");
                finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void kissTap(View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String partnerEmailTxt="A";
                databaseReference.child("Notifications").child("Kiss").child(userEmail).setValue("sends");
                databaseReference.child("Notifications").child("Kiss").child(partnerEmail).setValue("receives");
                databaseReference.child("Notifications").child("Kiss").child("Date").setValue(currentDate);
                Log.i("success","success");
                finish();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void MovieRecommendations(View view) {
        Intent intent = new Intent(getApplicationContext(), MovieNight.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

    }

    public void CountdownTime(View view) {
        Intent intent = new Intent(getApplicationContext(), Countdown.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}