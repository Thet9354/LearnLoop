package com.example.learnloop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.learnloop.Fragments.MainFragment;

public class AboutUs_Activity extends AppCompatActivity {

    private TextView txtView_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getSupportActionBar().hide();

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        txtView_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

    private void initWidget() {

        //TextView
        txtView_getStarted = findViewById(R.id.txtView_getStarted);
    }
}