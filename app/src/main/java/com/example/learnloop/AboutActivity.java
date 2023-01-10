package com.example.learnloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.learnloop.Fragments.MainFragment;

import org.w3c.dom.Text;

public class AboutActivity extends AppCompatActivity {

    private TextView txtView_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().hide();

        initWidget();

        pageDirectories();

    }

    private void pageDirectories() {

        txtView_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, MainFragment.class);
                startActivity(intent);
            }
        });
    }

    private void initWidget() {

        // TextView
        txtView_getStarted = findViewById(R.id.txtView_getStarted);
    }
}