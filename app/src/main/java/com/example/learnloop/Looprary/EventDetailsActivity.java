package com.example.learnloop.Looprary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class EventDetailsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        getSupportActionBar().hide();
    }
}