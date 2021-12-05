package com.example.closer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText email= findViewById(R.id.Email);
        final EditText password=findViewById(R.id.Password);
        final ImageButton loginBtn=findViewById(R.id.login);
        final ImageButton signupBtn=findViewById(R.id.signup);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailTxt=email.getText().toString();
                final String passwordTxt=password.getText().toString();

                if(emailTxt.isEmpty()||passwordTxt.isEmpty()){
                    Log.i("empty","empty");
                }
                else{

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