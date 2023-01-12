package com.example.learnloop.Looprary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.Fragments.LoopraryFragment;
import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class CourseDetailsActivity extends AppCompatActivity {

    private TextView txtView_back, txtView_courseTitle, txtView_courseStats, txtView_courseAttendee,
            txtView_attendeeDetail, txtView_lessonsProgress, txtView_courseHost, txtView_hostDesc,
            txtView_courseDesc, txtView_seeMore;

    private ImageView imgView_course, imgView_courseAttendee;

    private MaterialButton btn_seeResource;

    private String courseTitle, hostName, hostDesc, courseDuration, courseLesson, courseDesc, courseUniversity, universityDesc, publishedDates;
    private int img_course, img_host;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);
        getSupportActionBar().hide();

        intent = getIntent();

        initWidget();

        getIntentData();

        pageDirectories();

    }

    private void getIntentData() {

        courseTitle = intent.getStringExtra("Course Title");
        hostName = intent.getStringExtra("Host Name");
        hostDesc = intent.getStringExtra("Host Desc");
        courseDuration = intent.getStringExtra("Course Duration");
        courseLesson = intent.getStringExtra("Course Lessons");

        courseDesc = intent.getStringExtra("Course Desc");
        courseUniversity = intent.getStringExtra("Course University");
        universityDesc = intent.getStringExtra("University Desc");
        publishedDates = intent.getStringExtra("Published Dates");

        img_course = intent.getIntExtra("Course Image", 0);
        img_host = intent.getIntExtra("Host Image", 0);

        initUI();

    }

    private void pageDirectories() {

        btn_seeResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://collegeinfogeek.com/how-to-budget/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        txtView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initUI() {

        txtView_courseTitle.setText(courseTitle);
        txtView_courseAttendee.setText(hostName);
        txtView_attendeeDetail.setText(hostDesc);
        txtView_lessonsProgress.setText("Remaining: " + courseLesson);

        txtView_courseStats.setText("58,320 views - " + publishedDates);
        txtView_courseHost.setText(courseUniversity);
        txtView_hostDesc.setText(universityDesc);
        txtView_courseDesc.setText(courseDesc);

        imgView_course.setImageResource(img_course);
        imgView_courseAttendee.setImageResource(img_host);

    }

    private void initWidget() {

        //TextView
        txtView_back = findViewById(R.id.txtView_back);
        txtView_courseTitle = findViewById(R.id.txtView_courseTitle);
        txtView_courseStats = findViewById(R.id.txtView_courseStats);
        txtView_courseAttendee = findViewById(R.id.txtView_courseAttendee);
        txtView_attendeeDetail = findViewById(R.id.txtView_attendeeDetail);
        txtView_lessonsProgress = findViewById(R.id.txtView_lessonsProgress);
        txtView_courseHost = findViewById(R.id.txtView_courseHost);
        txtView_hostDesc = findViewById(R.id.txtView_hostDesc);
        txtView_courseDesc = findViewById(R.id.txtView_courseDesc);
        txtView_seeMore = findViewById(R.id.txtView_seeMore);

        //Imageview
        imgView_course = findViewById(R.id.imgView_course);
        imgView_courseAttendee = findViewById(R.id.imgView_courseAttendee);

        //Button
        btn_seeResource = findViewById(R.id.btn_seeResource);

    }
}