package com.example.ui_pupmunchmapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LaunchPage extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_page);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent launchIntent = new Intent(LaunchPage.this, PreLogin.class);
                startActivity(launchIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}