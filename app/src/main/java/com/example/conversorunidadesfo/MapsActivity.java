package com.example.conversorunidadesfo;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

        //Llamo a la funcion ubicacionFibro
        ubicacionFibro(googleMap);
        ubicacionDepo(googleMap);
    }

    public void ubicacionFibro(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in fibromarket and move the camera
        final LatLng centralFibro = new LatLng(-34.6119694,-58.505984);
        mMap.addMarker(new MarkerOptions().position(centralFibro).title("Fibromarket \n Sanabria 2937"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centralFibro, 13)); //muevo camara y le pongo parametro 8 , que es el zoom que quiero
    }

    public void ubicacionDepo(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Deposito and put color
        final LatLng depoFibro = new LatLng(-34.5939111,-58.4948112);
        mMap.addMarker(new MarkerOptions().position(depoFibro).title("Depósito \n Beiró 2733").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
    }



}