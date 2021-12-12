package com.example.closer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsNight extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    String userEmail;
    String partnerEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_night);
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
    }

    public void RandomizeQuestions(View view) {
        ArrayList<String> random=new ArrayList<>();
        EditText quest1=findViewById(R.id.ques1);
        EditText quest2=findViewById(R.id.quest2);
        EditText quest3=findViewById(R.id.quest3);
        String a=quest1.getText().toString();
        String b=quest2.getText().toString();
        String c=quest3.getText().toString();
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    databaseReference.child("Questions").child(userEmail).child("1").setValue(a);
                    databaseReference.child("Questions").child(userEmail).child("2").setValue(b);
                    databaseReference.child("Questions").child(userEmail).child("3").setValue(c);
                    databaseReference.child("Questions").child(userEmail).child("partnerEmail").setValue(partnerEmail);
                    Log.i("success","success");
                quest1.setVisibility(View.INVISIBLE);
                quest2.setVisibility(View.INVISIBLE);
                quest3.setVisibility(View.INVISIBLE);
                TextView aa=findViewById(R.id.quest1txt);
                TextView bb=findViewById(R.id.quest3txt);
                TextView cc=findViewById(R.id.quest2txt);
                aa.setVisibility(View.VISIBLE);
                bb.setVisibility(View.VISIBLE);
                cc.setVisibility(View.VISIBLE);
                aa.setText(b);
                bb.setText(a);
                cc.setText(c);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}