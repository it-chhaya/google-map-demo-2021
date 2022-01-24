package com.devkh.mapdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class PitchNearByActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {

    private final static String TAG = PitchNearByActivity.class.getName();

    private RecyclerView mRcvPitchCards;
    private LinearLayoutManager mLayoutManager;

    private GoogleMap mGoogleMap;
    private LocationManager mLocationManager;

    private double mLat;
    private double mLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pitch_near_by);

        // initialize location manager
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.nearby_map);
        mapFragment.getMapAsync(this);

        // Start implementing recycler view items
        setupRecyclerView();
        // End implementing recycler view items

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        mLat = location.getLatitude();
        mLong = location.getLongitude();
        Log.i(TAG, "Lat, Long = (" + mLat + ", " + mLong + ")");
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap;

        getLocation();
        // 11.575968506342962, 104.88942917488465 = KSHRD location

        // Add a marker in Sydney and move the camera
        LatLng currentLocation = new LatLng(mLat, mLong);
        LatLng rooy7 = new LatLng(11.583795800432265, 104.88750895924561);
        LatLng tSoccer = new LatLng(11.586142747234472, 104.90252228770636);
        LatLng premium = new LatLng(11.589782162416592, 104.8971432920974);

        // Set type of map to display
        // mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        Marker kshrdMarker = mGoogleMap.addMarker(new MarkerOptions()
                .position(currentLocation)
                .title("Korea Software HRD Center")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder)));
        Marker rooy7Marker = mGoogleMap.addMarker(new MarkerOptions().position(rooy7).title("Rooy7 Sport Club"));
        Marker tSoccerMarker = mGoogleMap.addMarker(new MarkerOptions().position(tSoccer).title("T-Soccer TK"));
        Marker premiumMarker = mGoogleMap.addMarker(new MarkerOptions().position(premium).title("Premium Sport Club"));

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
        mGoogleMap.moveCamera(CameraUpdateFactory.zoomTo(15));
        mGoogleMap.setTrafficEnabled(true);

    }

    private void getLocation() {
        Log.i(TAG, "getLocation: ");
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
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


    /**
     * Set up recycler view
     */
    private void setupRecyclerView() {
        mRcvPitchCards = findViewById(R.id.rcv_pitch_card);

        ArrayList<PitchCard> items = new ArrayList<>();

        items.add(new PitchCard(R.drawable.cover, "Rooy7 Sport Club"));
        items.add(new PitchCard(R.drawable.cover, "Premium Sport Club"));
        items.add(new PitchCard(R.drawable.cover, "T-Soccer"));
        items.add(new PitchCard(R.drawable.cover, "White Sport"));
        items.add(new PitchCard(R.drawable.cover, "Big Star Sport Club"));
        items.add(new PitchCard(R.drawable.cover, "AEON 2 Premium Sport"));
        items.add(new PitchCard(R.drawable.cover, "Green Land"));

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        PitchNearByAdapter adapter = new PitchNearByAdapter(this, items);

        mRcvPitchCards.setLayoutManager(mLayoutManager);
        mRcvPitchCards.setAdapter(adapter);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRcvPitchCards);

        mRcvPitchCards.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    /*int activePosition = 0;
                    int firstVisiblePosition = mLayoutManager.findFirstVisibleItemPosition();
                    int lastVisiblePosition = mLayoutManager.findLastVisibleItemPosition();
                    if (lastVisiblePosition > 1)
                        activePosition = firstVisiblePosition + 1;*/
                    int activePosition = mLayoutManager.findFirstCompletelyVisibleItemPosition();
                    Log.i(TAG, "onScrollStateChanged: " + activePosition);
                    mGoogleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(11.589782162416592, 104.8971432920974)));
                }
            }
        });
    }

}