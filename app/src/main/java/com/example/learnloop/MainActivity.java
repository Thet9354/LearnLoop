package com.example.learnloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.learnloop.Fragments.AddTransactionFragment;
import com.example.learnloop.Fragments.DiscountFragment;
import com.example.learnloop.Fragments.LoopraryFragment;
import com.example.learnloop.Fragments.MainFragment;
import com.example.learnloop.Fragments.StatementFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;

    Intent intent;

    private String name, mPhoneNumber, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        intent = getIntent();

        initWidget();

        getIntentData();
    }

    private void getIntentData() {

        name = intent.getStringExtra("Name");
        mPhoneNumber = intent.getStringExtra("Phone Number");
        email = intent.getStringExtra("Email");
        password = intent.getStringExtra("Password");

    }

    private void initWidget() {
        chipNavigationBar = findViewById(R.id.menu);
        chipNavigationBar.setItemSelected(R.id.bottom_nav_main, true);

        bottomMenu();
    }

    private void bottomMenu() {
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch(i) {
                    case R.id.bottom_nav_main:
                        fragment = new MainFragment();
                        break;
                    case R.id.bottom_nav_addtransaction:
                        fragment = new AddTransactionFragment();
                        break;
                    case R.id.bottom_nav_learning:
                        fragment = new LoopraryFragment();
                        break;
                    case R.id.bottom_nav_statement:
                        fragment = new StatementFragment();
                        break;
                    case R.id.bottom_nav_discount:
                        fragment = new DiscountFragment();
                        break;
                }

                Bundle bundle = new Bundle();
                bundle.putString("Name", name);
                bundle.putString("Phone Number", mPhoneNumber);
                bundle.putString("Email", email);
                bundle.putString("Password", password);

                fragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }
}