package com.example.closer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://closer-33bb6-default-rtdb.firebaseio.com/");
        List<String> EventList = new ArrayList<String>();


        databaseReference.child("Reminder").child("heba").child("2021-11-15").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    final String getEvent=snapshot.child("Event").getValue(String.class);
                    final String getDate=snapshot.child("Date").getValue(String.class);
                String combined=getEvent+" in date "+getDate;
                Log.i("he",getDate);
                EventList.add(combined);
                ListView list = (ListView) findViewById(R.id.listViewEvents);
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,EventList);
                list.setAdapter(arr);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}