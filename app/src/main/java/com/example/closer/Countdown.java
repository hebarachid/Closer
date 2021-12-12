package com.example.closer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.iwgang.countdownview.CountdownView;

public class Countdown extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);

        CountdownView myCountdownView = findViewById(R.id.countdown);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String countdown = "01-01-2022 00:00:00";

        Date now = new Date();

        try {
            Date date = sdf.parse(countdown);
            long currentTime = now.getTime();
            long yearDate = date.getTime();
            long countDownToDate = yearDate - currentTime;
            myCountdownView.start(countDownToDate);
        } catch (ParseException e) {
             e.printStackTrace();
        }
    }
}