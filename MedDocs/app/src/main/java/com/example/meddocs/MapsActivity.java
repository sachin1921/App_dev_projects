package com.example.meddocs;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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
        UiSettings mapUiSettings = mMap.getUiSettings();
        mapUiSettings.setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng clinic1 = new LatLng(43.689550, -79.296990);
        mMap.addMarker(new MarkerOptions().position(clinic1).title("Dawes Family Practice And Walk-In"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(clinic1));

        LatLng clinic2 = new LatLng(43.651930, -79.378840);
        mMap.addMarker(new MarkerOptions().position(clinic2).title("Patient Networks Family Medicine Walk In Clinic"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(clinic2));

        LatLng clinic3 = new LatLng(43.670340, -79.390140);
        mMap.addMarker(new MarkerOptions().position(clinic3).title("Wellpoint Family Practice"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(clinic3));

        LatLng clinic4 = new LatLng(43.671360, -79.292690);
        mMap.addMarker(new MarkerOptions().position(clinic4).title("Beaches Family Practice"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(clinic4));

        LatLng clinic5 = new LatLng(43.697730, -79.396060);
        mMap.addMarker(new MarkerOptions().position(clinic5).title("Davisville Family Practice"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(clinic5));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));


    }
}
