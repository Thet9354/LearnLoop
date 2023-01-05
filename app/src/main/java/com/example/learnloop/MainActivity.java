package com.example.learnloop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.learnloop.Fragments.AddTransactionFragment;
import com.example.learnloop.Fragments.DiscountFragment;
import com.example.learnloop.Fragments.LoopraryFragment;
import com.example.learnloop.Fragments.MainFragment;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    ChipNavigationBar chipNavigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        initWidget();
    }

    private void initWidget() {
        chipNavigationBar = findViewById(R.id.menu);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainFragment()).commit();

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
                    case R.id.bottom_nav_discount:
                        fragment = new DiscountFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            }
        });
    }
}