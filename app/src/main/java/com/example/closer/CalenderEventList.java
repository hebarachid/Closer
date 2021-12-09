package com.example.closer;

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

public class CalenderEventList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_event_list);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference allBooksRef = rootRef.child("Reminder").child("heba");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> EventList = new ArrayList<String>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String event = ds.child("Event").getValue(String.class);
                    String Date = ds.child("Date").getValue(String.class);
                    String combined=event+" in date "+Date;
                    Log.i("he",combined);
                    EventList.add(combined);
                }
                ListView list = (ListView) findViewById(R.id.listViewEvents);
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,EventList);
                list.setAdapter(arr);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        allBooksRef.addListenerForSingleValueEvent(valueEventListener);
    }
}