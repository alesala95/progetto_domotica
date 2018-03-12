package com.example.itsadmin.smartfridge_fragment.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.example.itsadmin.smartfridge_fragment.Login.LoginActivity;
import com.example.itsadmin.smartfridge_fragment.R;



public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.BlueColor));//cambia colore navigation bar


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);


            }
        }, 3000);


    }
}
