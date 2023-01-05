package com.example.learnloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.learnloop.Fragments.MainFragment;

import org.w3c.dom.Text;

public class AboutActivity extends AppCompatActivity {

    private TextView backToHome_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        getSupportActionBar().hide();

        backToHome_Button = findViewById(R.id.backToHome_Button);

        backToHome_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AboutActivity.this, MainFragment.class);
                startActivity(intent);
            }
        });
    }
}