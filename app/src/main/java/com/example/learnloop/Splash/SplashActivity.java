package com.example.learnloop.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.learnloop.Onboarding.RegisterActivity;
import com.example.learnloop.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        getSupportActionBar().hide();

        // Splash Page for 2 Seconds
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();

                // SharedPreferences sharedPreferences = getSharedPreferences(FirstOpenActivity.PREFS_NAME, 0);
                //boolean hasLoggedBefore = sharedPreferences.getBoolean("hasLoggedBefore", false);

                /*
                if (hasLoggedBefore) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashActivity.this, FirstOpenActivity.class);
                    startActivity(intent);
                    finish();
                }

                 */
            }
        },2000);

    }
}