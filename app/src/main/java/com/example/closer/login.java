package com.example.closer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {
    String userEmail;
    String partnerEmail;
 DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://closer-33bb6-default-rtdb.firebaseio.com/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email= findViewById(R.id.Email);
        final EditText password=findViewById(R.id.Password);
        final ImageButton loginBtn=findViewById(R.id.login);
        final ImageButton signupBtn=findViewById(R.id.signup);

            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                userEmail= null;
                partnerEmail= null;
            } else {
                userEmail= extras.getString("emailOfTheUser");

            }



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailTxt=email.getText().toString();
                final String passwordTxt=password.getText().toString();

                if(emailTxt.isEmpty()||passwordTxt.isEmpty()){
                    Log.i("empty","empty");
                }
                else{
                      databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot snapshot) {

                              if(snapshot.hasChild(emailTxt)){
                                  //email exists in database
                                  //get database and check
                                  final String getPassword=snapshot.child(emailTxt).child("Password").getValue(String.class);

                                  if(getPassword.equals(passwordTxt)){
                                      Toast.makeText(login.this,"Successfully logged in",Toast.LENGTH_SHORT).show();
                                      partnerEmail=snapshot.child(emailTxt).child("PartnerEmail").getValue(String.class);
                                      Intent intent = new Intent(getApplicationContext(), HomePage.class);
                                      intent.putExtra("emailOfTheUser", emailTxt);
                                      intent.putExtra("emailOfThePartner",partnerEmail);

                                      startActivity(intent);
                                      finish();
                                  }
                                  else{
                                      Toast.makeText(login.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                                  }
                              } else{
                                  Toast.makeText(login.this,"Wrong Password",Toast.LENGTH_SHORT).show();
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
    public void directSignUp(View view){
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
    }
}