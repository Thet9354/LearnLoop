package com.example.learnloop.Onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AskInformationActivity extends AppCompatActivity {

    private EditText editTxt_allowance, editTxt_budgetGoal;
    private TextView txtView_userName;
    private RadioGroup rg_allowanceType;
    private Button btn_register;

    Intent intent;

    //String to store input data
    private String mName, mPhoneNumber, mEmail, mPassword;
    private String mAllowance, allowanceType, budgetGoal;
    private String expenditure = "0";

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://learnloop-1673224439925-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

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
        mPhoneNumber = intent.getStringExtra("Phone Number");
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

            }


        });
    }

    private void validateInput() {

        if (!validateAllowance() | !validateAllowanceType() | !validateBudgetGoal())
        {
            System.out.println("Rip");
        }
        else
        {
            addData();
        }
    }

    private void addData() {

        //Adding data into google realtime database
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Checking if phone number is not registered before

                if (snapshot.hasChild(mPhoneNumber))
                {
                    //--->Asking user if he/she wants ti log in to existing account
                    AlertDialog.Builder builder = new AlertDialog.Builder(AskInformationActivity.this);
                    builder.setTitle("Mechanica");
                    builder.setMessage("Hey there, it seems like there's an existing account with the same name.");
                    builder.setNegativeButton("Register a new account", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                        }
                    });
                    builder.setPositiveButton("Log in to existing account", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        }
                    });
                    builder.create().show();
                }
                else
                {
                    //--->Adding user's personal information to firebase
                    databaseReference.child(mPhoneNumber).child("User's Information").child("Full Name").setValue(mName);
                    databaseReference.child(mPhoneNumber).child("User's Information").child("Phone Number").setValue(mPhoneNumber);
                    databaseReference.child(mPhoneNumber).child("User's Information").child("Email").setValue(mEmail);
                    databaseReference.child(mPhoneNumber).child("User's Information").child("Password").setValue(mPassword);

                    databaseReference.child(mPhoneNumber).child("User's Information").child("Allowance").setValue(mAllowance);
                    databaseReference.child(mPhoneNumber).child("User's Information").child("Allowance Type").setValue(allowanceType);
                    databaseReference.child(mPhoneNumber).child("User's Information").child("Budget Goal").setValue(budgetGoal);
                    databaseReference.child(mPhoneNumber).child("User's Information").child("Expenditure").setValue(expenditure);


                    Toast.makeText(AskInformationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("Name", mName);
                    intent.putExtra("Phone Number", mPhoneNumber);
                    intent.putExtra("Email", mEmail);
                    intent.putExtra("Password", mPassword);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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