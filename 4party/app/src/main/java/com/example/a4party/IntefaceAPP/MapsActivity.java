package com.example.a4party.IntefaceAPP;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;

import com.example.a4party.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.a4party.databinding.ActivityMapsBinding;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private Button botonVolver;
    private FirebaseFirestore db;
    String direccionLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle datosEmail = getIntent().getExtras();
        String email = datosEmail.getString("email");
        db = FirebaseFirestore.getInstance();
        botonVolver = findViewById(R.id.botonvolverLista);
        botonVolver.setOnClickListener(view -> {
            Intent intent = new Intent(this, HOME_ACTIVITY.class);
            startActivity(intent);
            finish();
        });
        db.collection("Empresarios").document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String direccionEstable = documentSnapshot.getString("codigoPostal");
                direccionLocal = direccionEstable;
            }
        });

        /*====================================================================================*/
        List<Address> direccionlist = new ArrayList<>();

        if (!direccionLocal.isEmpty()) {
            Geocoder geocoder = new Geocoder((MapsActivity.this));
            try {
                direccionlist = geocoder.getFromLocationName(direccionLocal, 1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Address address = direccionlist.get(0);
            LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);
        /*====================================================================================*/
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }

}