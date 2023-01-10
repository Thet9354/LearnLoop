package com.example.learnloop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessAddActivty extends AppCompatActivity {

    private TextView txtView_transactionTitle, txtView_transactionType, txtView_transactionAmount;

    private ImageView imgView_transaction;

    private pl.droidsonroids.gif.GifImageView gif_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_add);
        getSupportActionBar().hide();

        initWidget();

        initUI();

        pageDirectories();
    }

    private void pageDirectories() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SuccessAddActivty.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3500);
    }

    private void initUI() {

    }

    private void initWidget() {

        //TextView
        txtView_transactionTitle = findViewById(R.id.txtView_transactionTitle);
        txtView_transactionType = findViewById(R.id.txtView_transactionType);
        txtView_transactionAmount = findViewById(R.id.txtView_transactionAmount);

        //ImageView
        imgView_transaction = findViewById(R.id.imgView_transaction);

        //pl.droidsonroids.gif.GifImageView
        gif_done = findViewById(R.id.gif_done);
    }
}