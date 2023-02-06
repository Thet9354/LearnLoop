package com.example.learnloop.Looprary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class EventDetailsActivity extends AppCompatActivity {

    private TextView txtView_back, txtView_eventTitle, txtView_eventHost, txtView_hostDesc,
            txtView_eventDesc, txtView_seeMore;

    private MaterialButton btn_addToCalendar, btn_emailOrganizer, btn_registerEvent;

    private ImageView imgView_event, imgView_eventHost;

    private String eventTitle, hostName, hostDesc, eventParticipants;
    private int eventImage, hostImage;

    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        getSupportActionBar().hide();

        intent = getIntent();

        initWidget();

        getIntentData();

        initUI();

        pageDirectories();
    }

    private void getIntentData() {

        eventTitle = intent.getStringExtra("Event Title");
        hostName = intent.getStringExtra("Host Name");
        hostDesc = intent.getStringExtra("Host Desc");
        eventParticipants = intent.getStringExtra("Event Participants");

        eventImage = intent.getIntExtra("Event Image", 0);
        hostImage = intent.getIntExtra("Host Image", 0);

        System.out.println(eventTitle);
        System.out.println(hostName);
        System.out.println(hostDesc);
        System.out.println(eventParticipants);

        System.out.println(eventImage);
        System.out.println(hostImage);

    }

    private void initUI() {

    }

    private void pageDirectories() {

    }

    private void initWidget() {

        //TextView
        txtView_back = findViewById(R.id.txtView_back);
        txtView_eventTitle = findViewById(R.id.txtView_eventTitle);
        txtView_eventHost = findViewById(R.id.txtView_eventHost);
        txtView_hostDesc = findViewById(R.id.txtView_hostDesc);
        txtView_eventDesc = findViewById(R.id.txtView_eventDesc);
        txtView_seeMore = findViewById(R.id.txtView_seeMore);

        //MaterialButton
        btn_addToCalendar = findViewById(R.id.btn_addToCalendar);
        btn_emailOrganizer = findViewById(R.id.btn_emailOrganizer);
        btn_registerEvent = findViewById(R.id.btn_registerEvent);

        //ImageView
        imgView_event = findViewById(R.id.imgView_event);
        imgView_eventHost = findViewById(R.id.imgView_eventHost);

    }
}