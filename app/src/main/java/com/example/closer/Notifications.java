package com.example.closer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Notifications extends AppCompatActivity {
    String userEmail;
    String getDate;
    String partnerEmail;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://closer-33bb6-default-rtdb.firebaseio.com/");
    List<String> EventList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userEmail= null;
                partnerEmail= null;
            } else {
                userEmail= extras.getString("userEmail");
                partnerEmail= extras.getString("partnerEmail");
            }
        } else {
            userEmail= (String) savedInstanceState.getSerializable("userEmail");
            partnerEmail= (String) savedInstanceState.getSerializable("partnerEmail");
        }
        databaseReference.child("Notifications").child("heart").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String getDate=snapshot.child("Date").getValue(String.class);
                String combined=userEmail+" sent a heart to "+partnerEmail +"on "+getDate;
                EventList.add(combined);
                ListView list = (ListView) findViewById(R.id.notificationListView);
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,EventList);
                list.setAdapter(arr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Notifications").child("hug").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                getDate=snapshot.child("Date").getValue(String.class);
                String combined=userEmail+" sent a hug to "+partnerEmail +" on "+getDate;
                EventList.add(combined);
                ListView list = (ListView) findViewById(R.id.notificationListView);
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,EventList);
                list.setAdapter(arr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Notifications").child("Kiss").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                getDate=snapshot.child("Date").getValue(String.class);

                String combined=userEmail+" sent a Kiss to "+partnerEmail +" on "+getDate;
                EventList.add(combined);
                ListView list = (ListView) findViewById(R.id.notificationListView);
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,EventList);
                list.setAdapter(arr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Notifications").child("Poke").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                getDate=snapshot.child("Date").getValue(String.class);
                String combined=userEmail+" sent a Poke to "+partnerEmail +" on "+getDate;
                EventList.add(combined);
                ListView list = (ListView) findViewById(R.id.notificationListView);
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,EventList);
                list.setAdapter(arr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}