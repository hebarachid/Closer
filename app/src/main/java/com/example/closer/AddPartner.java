package com.example.closer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddPartner extends AppCompatActivity {
    EditText partnerEmail;
    String userEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_partner);

        partnerEmail=findViewById(R.id.partnerEmail);

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



    }

    public void sendLink(View view){
        final String partnerEmailtxt=partnerEmail.getText().toString();

        FirebaseDatabase.getInstance().getReference().child("users").child(userEmail).child("PartnerEmail")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                           snapshot.getRef().setValue(partnerEmailtxt);

                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }
}