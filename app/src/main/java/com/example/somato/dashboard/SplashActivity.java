package com.example.somato.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.somato.R;
import com.example.somato.dashboard.view.MainActivity;
import com.example.somato.network.Config;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private Location location;
    private final int REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission
                .ACCESS_FINE_LOCATION))
            ActivityCompat.requestPermissions(this, new String[]{(android.Manifest.permission.ACCESS_FINE_LOCATION
            )},REQUEST_CODE);
        else
        {
            buildLocationRequest();
            buildLocationCallback();

            //Create FusedProviderClient
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

            if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission
                .ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED   &&   ActivityCompat
                .checkSelfPermission(this,android.Manifest.permission
                .ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED )
            {
                ActivityCompat.requestPermissions(this, new String[]{(android.Manifest.permission
                    .ACCESS_FINE_LOCATION)},REQUEST_CODE);
            }
            fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.myLooper());
        }
        new Handler().postDelayed(() -> {

            checkAndProceed();

        }, SPLASH_TIME_OUT);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if (grantResults.length>0){
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this,"Permission Granted" , Toast.LENGTH_SHORT).show();
                    }
                    else
                    Toast.makeText(this,"Permission Denied" , Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }

    private void checkAndProceed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    private void buildLocationCallback() {

        locationCallback = new LocationCallback(){
            @Override
            public void onLocationResult(LocationResult p0) {
                location = p0.getLocations().get(p0.getLocations().size()-1);
                Config.lattitude = String.valueOf(location.getLatitude());
                Config.longitude = String.valueOf(location.getLongitude());

            }
        };
    }

    @SuppressLint("RestrictedApi")
    private void buildLocationRequest() {

        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);
    }
}