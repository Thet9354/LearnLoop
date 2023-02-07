package com.example.learnloop.Settings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.learnloop.R;
import com.example.learnloop.Settings.SettingsFragment.Accessibility;
import com.example.learnloop.Settings.SettingsFragment.AdditionalResources;
import com.example.learnloop.Settings.SettingsFragment.Notifications;
import com.example.learnloop.Settings.SettingsFragment.PrivacyAndSafety;
import com.example.learnloop.Settings.SettingsFragment.SecurityAndPrivacy;
import com.example.learnloop.Settings.SettingsFragment.YourAccount;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class Setting_Activity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar topAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initWidget();

        pageDirectories();
    }

    private void pageDirectories() {

        topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                drawerLayout.closeDrawer(GravityCompat.START);

                switch (id)
                {
                    case R.id.nav_yourAccount:
                        replaceFragment(new YourAccount());
                        break;

                    case R.id.nav_securityNAccount:
                        replaceFragment(new SecurityAndPrivacy());
                        break;

                    case R.id.nav_privacyNSafety:
                        replaceFragment(new PrivacyAndSafety());
                        break;

                    case R.id.nav_notification:
                        replaceFragment(new Notifications());
                        break;

                    case R.id.nav_accessibility:
                        replaceFragment(new Accessibility());
                        break;

                    case R.id.nav_additionalResource:
                        replaceFragment(new AdditionalResources());
                        break;

                }
                return false;
            }
        });
    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    private void initWidget() {

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        topAppBar = findViewById(R.id.topAppBar);

        initUI();
    }

    private void initUI() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new YourAccount());
        fragmentTransaction.commit();
    }
}