package com.example.closer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Calender extends AppCompatActivity {
private CalendarView calendarView;
private EditText event;
String userEmail;
String partnerEmail;
String selectedDate;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference()
            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calendarView=findViewById(R.id.calendarView);
        event=findViewById(R.id.event);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userEmail= null;
                partnerEmail=null;
            } else {
                userEmail= extras.getString("emailOfTheUser");
                partnerEmail= extras.getString("emailOfThePartner");
            }
        } else {
            userEmail= (String) savedInstanceState.getSerializable("emailOfTheUser");
            partnerEmail= (String) savedInstanceState.getSerializable("emailOfThePartner");
        }
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
            selectedDate=Integer.toString(i)+"-"+Integer.toString(i1)+"-"+Integer.toString(i2);
            }
        });


    }

    public void addEvent(View view) {
        event=findViewById(R.id.event);
        String eventDetails=event.getText().toString();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    databaseReference.child("Reminder").child(userEmail).child(selectedDate).child("userEmail").setValue(userEmail);
                    databaseReference.child("Reminder").child(userEmail).child(selectedDate).setValue(selectedDate);
                    databaseReference.child("Reminder").child(userEmail).child(selectedDate).child("Event").setValue(eventDetails);
                    databaseReference.child("Reminder").child(userEmail).child(selectedDate).child("PartnerEmail").setValue(partnerEmail);
                databaseReference.child("Reminder").child(userEmail).child(selectedDate).child("Date").setValue(selectedDate);
                databaseReference.child("Reminder").child(userEmail).child(selectedDate).child("userEmail").setValue(userEmail);
                    Log.i("success","success");
                    finish();
                }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void checkEvents(View view) {
        Intent intent = new Intent(getApplicationContext(), CalenderEventList.class);
        String message = userEmail;
        intent.putExtra("emailOfTheUser", message);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}