package com.example.learnloop.Looprary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.learnloop.Fragments.LoopraryFragment;
import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class CourseDetailsActivity extends AppCompatActivity {

    private MaterialButton getResourcesButton;
    private TextView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_details);
        getSupportActionBar().hide();

        getResourcesButton = findViewById(R.id.courseResources);
        backButton = findViewById(R.id.course_BackButton);

        buttonFunctions();

    }

    private void buttonFunctions() {
        getResourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://collegeinfogeek.com/how-to-budget/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CourseDetailsActivity.this, LoopraryFragment.class);
                startActivity(intent);
                finish();
            }
        });
    }
}