package com.example.learnloop.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class RegisterActivity extends AppCompatActivity {

    private TextView goToLogin;
    private EditText name, email;
    private EditText passwordAttempt1, passwordAttempt2;
    private ImageView googleButton, instagramButton, linkedinButton;
    private MaterialButton goToGetBudget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        getSupportActionBar().hide();

        // Initalize Widgets
        initWidget();
        buttonFunction();

    }

    private void buttonFunction() {
        goToGetBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRegisterDetails();
                Intent intent = new Intent(RegisterActivity.this, AskInformationActivity.class);
                startActivity(intent);
                finish();
            }

            private void saveRegisterDetails() {

                // Code to save user's Name, Email and Password to Database
                // TODO: 31/12/2022 Add code to save user's name, email and password after registering
            }
        });

        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // TODO: 31/12/2022 If want, add Register with Platforms feature
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, AskInformationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, AskInformationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        linkedinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, AskInformationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initWidget() {

        // TextView
        goToLogin = findViewById(R.id.goToLogin_button);

        // EditText
        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        passwordAttempt1 = findViewById(R.id.register_password_attempt1);
        passwordAttempt2 = findViewById(R.id.register_password_attempt2);

        // Clickable ImageView -> Register with Google, Instagram and/or LinkedIn
        googleButton = findViewById(R.id.register_Google);
        instagramButton = findViewById(R.id.register_Instagram);
        linkedinButton = findViewById(R.id.register_Facebook);

        // Material Button
        goToGetBudget = findViewById(R.id.loginButton);
    }
}