package com.devkh.mapdemo;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.devkh.mapdemo.databinding.ActivityPitchesBinding;
import com.google.android.gms.tasks.Task;

public class PitchesActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private final static String TAG = PitchesActivity.class.getName();

    private GoogleMap mMap;
    private ActivityPitchesBinding binding;

    private LocationManager mLocationManager;
    private double mLat;
    private double mLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPitchesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {"android.Manifest.permission.ACCESS_FINE_LOCATION", "android.Manifest.permission.ACCESS_COARSE_LOCATION"}, 1);
        }

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        /*if (!mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //OnGPS();
            Toast.makeText(this, "Try to on the GPS", Toast.LENGTH_SHORT).show();
        } else {
            getLocation();
        }*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocation();
        // Add a marker in Sydney and move the camera
        LatLng kshrd = new LatLng(mLat, mLong);
        LatLng rooy7 = new LatLng(11.583795800432265, 104.88750895924561);
        LatLng tSoccer = new LatLng(11.586142747234472, 104.90252228770636);
        LatLng premium = new LatLng(11.589782162416592, 104.8971432920974);


        // Set type of map to display
        // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        mMap.addMarker(new MarkerOptions().position(kshrd).title("Korea Software HRD Center"));
        mMap.addMarker(new MarkerOptions().position(rooy7).title("Rooy7 Sport Club"));
        mMap.addMarker(new MarkerOptions().position(tSoccer).title("T-Soccer TK"));
        mMap.addMarker(new MarkerOptions().position(premium).title("Premium Sport Club"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kshrd));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        mMap.setTrafficEnabled(true);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mLat = location.getLatitude();
        mLong = location.getLongitude();
        Log.i(TAG, "Lat, Long = (" + mLat + ", " + mLong + ")");
    }

    private void getLocation() {
        Log.i(TAG, "getLocation: ");
        if (ActivityCompat.checkSelfPermission(
                this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "Request Permission");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        } else {
            Log.i(TAG, "Get location");
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    500,
                    0F, this);
            Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Log.i(TAG, "getLocation: " + location);
            mLat = location.getLatitude();
            mLong = location.getLongitude();
        }
    }

}