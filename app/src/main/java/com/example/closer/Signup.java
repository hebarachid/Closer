package com.example.closer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {
   DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference()
           ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText Name=findViewById(R.id.Name);
        final EditText Email=findViewById(R.id.EmailSignup);
        final EditText Password=findViewById(R.id.PasswordSignup);
        final ImageButton Register=findViewById(R.id.Register);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nameTxt=Name.getText().toString();
                final String emailTxt=Email.getText().toString();
                final String passwordTxt=Password.getText().toString();

                if(nameTxt.isEmpty()||emailTxt.isEmpty()||passwordTxt.isEmpty()){
                    Log.i("he","he");
                }
                else{
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(emailTxt)){
                                Log.i("reg","already");
                            }else{
                                databaseReference.child("users").child(emailTxt).child("Name").setValue(nameTxt);
                                databaseReference.child("users").child(emailTxt).child("Password").setValue(passwordTxt);

                                Log.i("success","success");
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });
    }
}