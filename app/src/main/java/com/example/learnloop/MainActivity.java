package com.example.learnloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnloop.FinanceBot.ChatBot_Activity;
import com.example.learnloop.Fragments.AddTransactionFragment;
import com.example.learnloop.Fragments.DiscountFragment;
import com.example.learnloop.Fragments.LoopraryFragment;
import com.example.learnloop.Fragments.MainFragment;
import com.example.learnloop.Fragments.StatementFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    private com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton fab_parent;
    private com.google.android.material.floatingactionbutton.FloatingActionButton fab_setting, fab_FA_AI;
    private TextView txtView_ai, txtView_setting;

    private Boolean isAllFABVisible;

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

        //FAB
        fab_parent = findViewById(R.id.fab_parent);
        fab_setting = findViewById(R.id.fab_setting);
        fab_FA_AI = findViewById(R.id.fab_FA_AI);

        //TextView
        txtView_ai = findViewById(R.id.txtView_ai);
        txtView_setting = findViewById(R.id.txtView_setting);

        chipNavigationBar.setItemSelected(R.id.bottom_nav_main, true);

        initUI();

        bottomMenu();
    }

    private void initUI() {

        fab_setting.setVisibility(View.GONE);
        fab_FA_AI.setVisibility(View.GONE);
        txtView_setting.setVisibility(View.GONE);
        txtView_ai.setVisibility(View.GONE);

        isAllFABVisible = false;

        fab_parent.shrink();

        pageDirectories();

    }

    private void pageDirectories() {

        fab_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isAllFABVisible)
                {
                    fab_setting.show();
                    fab_FA_AI.show();
                    txtView_setting.setVisibility(View.VISIBLE);
                    txtView_ai.setVisibility(View.VISIBLE);

                    fab_parent.extend();

                    isAllFABVisible = true;
                }
                else
                {
                    fab_setting.hide();
                    fab_FA_AI.hide();
                    txtView_setting.setVisibility(View.GONE);
                    txtView_ai.setVisibility(View.GONE);

                    fab_parent.shrink();

                    isAllFABVisible = false;
                }
            }
        });

        fab_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Create Settings page
            }
        });

        fab_FA_AI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ChatBot_Activity.class));
            }
        });
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