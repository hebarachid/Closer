package com.example.closer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    String partnerEmailtxt;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference()
            ;
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

    public void sendLink(View view) {
      partnerEmailtxt= partnerEmail.getText().toString();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(userEmail)){
                    Log.i("reg","already");
                }else{

                    databaseReference.child("users").child(userEmail).child("PartnerEmail").setValue(partnerEmailtxt);
                    Log.i("success","success");
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Intent intent = new Intent(getApplicationContext(), login.class);

        intent.putExtra("emailOfTheUser", userEmail);
        intent.putExtra("emailOfThePartner", partnerEmailtxt);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
}