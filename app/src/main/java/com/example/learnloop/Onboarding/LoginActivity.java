package com.example.learnloop.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.MainActivity;
import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {
    private TextView goToRegister;
    private EditText email, password;
    private ImageView googleButton, instagramButton, linkedinButton;
    private MaterialButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        getSupportActionBar().hide();

        initWidget();
        buttonFunction();
    }

    private void buttonFunction() {
        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // TODO: 31/12/2022 If want, add Register with Platforms feature
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initWidget() {

        // TextView
        goToRegister = findViewById(R.id.goToRegister_Button);

        // EditText
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        // Clickable ImageView -> Register with Google, Instagram and/or LinkedIn
        googleButton = findViewById(R.id.login_Google);
        instagramButton = findViewById(R.id.login_Instagram);
        linkedinButton = findViewById(R.id.login_Facebook);

        // Material Button
        loginButton = findViewById(R.id.loginButton);
    }

}