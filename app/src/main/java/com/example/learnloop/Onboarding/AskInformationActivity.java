package com.example.learnloop.Onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnloop.MainActivity;
import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class AskInformationActivity extends AppCompatActivity {

    private EditText editTxt_allowance, editTxt_budgetGoal;
    private TextView txtView_userName;
    private RadioGroup rg_allowanceType;
    private Button btn_register;

    Intent intent;

    //String to store input data
    private String mName, mEmail, mPassword;
    private String mAllowance, allowanceType, budgetGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ask_information);
        getSupportActionBar().hide();

        intent = getIntent();

        initWidget();

        getTransferredData();

        pageDirectories();


    }

    private void getTransferredData() {

        mName = intent.getStringExtra("Name");
        mEmail = intent.getStringExtra("Email");
        mPassword = intent.getStringExtra("Password");

        initUI();

    }

    private void initUI() {

        txtView_userName.setText("Hey " + mName + "!");

        allowanceType = "";

    }

    private void pageDirectories() {

        rg_allowanceType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                allowanceType = radioButton.getText().toString();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mAllowance = editTxt_allowance.getText().toString();
                budgetGoal = editTxt_budgetGoal.getText().toString();


                validateAllowance();
                validateAllowanceType();
                validateBudgetGoal();
                validateInput();


//                saveDetails();
//                Intent intent = new Intent(AskInformationActivity.this, MainActivity.class);
//                startActivity(intent);
            }


        });
    }

    private void validateInput() {

        if (!validateAllowance() | !validateAllowanceType() | !validateBudgetGoal())
            return;
        else
        {
            addData();
        }
    }

    private void addData() {

    }

    private boolean validateBudgetGoal() {

        if (budgetGoal.isEmpty())
        {
            editTxt_budgetGoal.setError("Required");
            return false;
        }
        else
            return true;
    }

    private boolean validateAllowanceType() {

        if (allowanceType.isEmpty())
        {
            Toast.makeText(this, "Please specify your allowance type", Toast.LENGTH_SHORT).show();
            return false;
        }
        else
            return true;
    }

    private boolean validateAllowance() {

        if (mAllowance.isEmpty())
        {
            editTxt_allowance.setError("Required");
            return false;
        }
        else
            return true;
    }

    private void initWidget() {
        // EditText
        editTxt_allowance = findViewById(R.id.editTxt_allowance);
        editTxt_budgetGoal = findViewById(R.id.editTxt_budgetGoal);

        // RadioGroup
        rg_allowanceType = findViewById(R.id.rg_allowanceType);

        // Material Button
        btn_register = findViewById(R.id.btn_register);

        //TextView
        txtView_userName = findViewById(R.id.txtView_userName);
    }
}