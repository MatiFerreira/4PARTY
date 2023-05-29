package com.example.a4party.IntefaceAPP;

import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import com.example.a4party.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.a4party.databinding.ActivityMaps2Binding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {
    private FirebaseFirestore db;
    private GoogleMap mMap;
    private ActivityMaps2Binding binding;
    private String email;
    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMaps2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        Bundle datosEmail = getIntent().getExtras();
        String email = datosEmail.getString("email");
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        db.collection("Empresarios").document(email).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    String direccionNueva = documentSnapshot.getString("codigoPostal");
                    List<Address> direccionlist = null;
                    if (direccionNueva.length() > 0) {
                        Geocoder geocoder = new Geocoder((MapsActivity2.this));
                        try {
                            direccionlist = geocoder.getFromLocationName(direccionNueva, 1);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        Address address = direccionlist.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng).title("LOCAL"));
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 20));

                    }

                }
            }
        });
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

/*        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
