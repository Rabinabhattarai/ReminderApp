package com.example.hp.myapplication.Water.Dialouge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.hp.myapplication.R;
import com.example.hp.myapplication.Water.Dialouge.mainwindow.MainActivity;

import static java.lang.Thread.sleep;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread myThread = new Thread(new Runnable() {
            public void run() {

                try {
                    sleep(3000);
                    Intent splashIntent = new Intent (getApplicationContext(),MainActivity.class);
                    startActivity(splashIntent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        myThread.start();



    }}