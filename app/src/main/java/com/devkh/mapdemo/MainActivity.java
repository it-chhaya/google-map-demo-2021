package com.devkh.mapdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBtnDirection;

    private Location mLocation;
    private LocationManager mLocationManager;
    private boolean mIsGPSEnabled;
    private boolean mIsNetworkEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }


        /*
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mIsGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        mIsNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (mIsGPSEnabled) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {
                        android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION
                }, 101);
            }
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    500, 0F, this);
            mLocation = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            double lat = mLocation.getLatitude();
            double lon =mLocation.getLongitude();
            Log.i("TAG", "onCreate: " + lat);
            Log.i("TAG", "onCreate: " + lon);
        }
        */

        // bind event
        getDirection();

    }

    private void getDirection() {
        mBtnDirection = findViewById(R.id.btn_direction);
        mBtnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get direction with Google Map App
                // Uri gmmIntentUri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=11.575985764067013,104.88940561703062");
                // Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // mapIntent.setPackage("com.google.android.apps.maps");
                // startActivity(mapIntent);

                Intent intent = new Intent(MainActivity.this, PitchNearByActivity.class);
                // intent.putExtra("lat", m);
                startActivity(intent);
            }
        });
    }

    /*
    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.i("TAG", "onLocationChanged: " + location.getLatitude());
        Log.i("TAG", "onLocationChanged: " + location.getLongitude());
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }*/
}