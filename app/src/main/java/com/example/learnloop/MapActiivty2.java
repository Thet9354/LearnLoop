package com.example.learnloop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.learnloop.Fragments.AddTransactionFragment;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapActiivty2 extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    PackageManager getPackageManager;
    GoogleMap map;

    private RelativeLayout parent;

    String phoneNumber;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_actiivty2);
        intent = getIntent();

        parent = findViewById(R.id.parent);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.googleMaps);

        client = LocationServices.getFusedLocationProviderClient(this);
        supportMapFragment.getMapAsync(this);

        getIntentData();

        checkPermission();
    }

    private void getIntentData() {

        phoneNumber = intent.getStringExtra("Phone Number");
    }

    private Boolean checkPermission() {
        if (ActivityCompat.checkSelfPermission(MapActiivty2.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
            return true;
        } else {
            ActivityCompat.requestPermissions(MapActiivty2.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            return false;
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {

                    String usrlat = String.valueOf(location.getLatitude());
                    String usrlong = String.valueOf(location.getLongitude());

                    Bundle bundle = new Bundle();
                    bundle.putString("Phone Number",phoneNumber);
                    bundle.putString("latitude",usrlat);
                    bundle.putString("longitude",usrlong);


                    Fragment fragment = new AddTransactionFragment();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.parent, fragment);
                    fragmentTransaction.commit();

//                    Intent intent =new Intent(MapActivity2.this, AddVisit.class);
//                    intent.putExtra("latitude",usrlat);
//                    intent.putExtra("longitude",usrlong);
//                    startActivity(intent);
                }
                else if(location == null ){
                    Toast.makeText(MapActiivty2.this, "Unable to get your location.\nMake sure your location and internet is turned on", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        if(checkPermission())
            googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

    }
}