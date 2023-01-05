package com.example.learnloop.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.example.learnloop.MainActivity;
import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class AskInformationActivity extends AppCompatActivity {

    private EditText allowance, budget;
    private RadioGroup allowanceTypes;
    private MaterialButton completeRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_information);
        getSupportActionBar().hide();

        initWidget();
         buttonFunctions();


    }

    private void buttonFunctions() {
        completeRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
                Intent intent = new Intent(AskInformationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            private void saveDetails() {

                // Code to save details
            }
        });
    }

    private void initWidget() {
        // EditText
        allowance = findViewById(R.id.allowance);
        budget = findViewById(R.id.budget);

        // RadioGroup
        allowanceTypes = findViewById(R.id.allowanceType);

        // Material Button
        completeRegister = findViewById(R.id.completeRegister_Button);
    }
}