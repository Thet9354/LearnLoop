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

    private ImageView imgView_course;

    private MaterialButton btn_seeResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);
        getSupportActionBar().hide();

        initWidget();

        initUI();

        pageDirectories();

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
                Intent intent = new Intent(CourseDetailsActivity.this, LoopraryFragment.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initUI() {

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

        //Button
        btn_seeResource = findViewById(R.id.btn_seeResource);

    }
}