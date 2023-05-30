package com.example.a4party.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.a4party.IntefaceAPP.ShowOffert;
import com.example.a4party.R;
import com.google.firebase.firestore.FirebaseFirestore;


public class OrderFragments extends Fragment {

    NavController navController;

    public OrderFragments() {
        // Required empty public constructor
    }

    private TextView descripciontxt, preciotxt, nombrelocal;
    private FirebaseFirestore db;
    private Button botoncomprar, botonCancelar, botoncondiciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_order, container, false);
        descripciontxt = vista.findViewById(R.id.descrpcionproducto);
        preciotxt = vista.findViewById(R.id.preciotxttotal);
        nombrelocal =vista.findViewById(R.id.nombrelocal);


        db = FirebaseFirestore.getInstance();
        db.collection("Productos").document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String precio = documentSnapshot.getString("precio");
                String decription = documentSnapshot.getString("descripcion");
                descripciontxt.setText(decription);
                preciotxt.setText(precio);
            } else {
                descripciontxt.setText("");
                preciotxt.setText("0.0");
            }
        });

        //botoncomprar.setOnClickListener(view -> {
          //  Intent intent = new Intent(this, MapsActivity.class);
           // intent.putExtra("email", email);
            //startActivity(intent);
        //});

        botonCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goofferts();
            }
        });



        // Inflate the layout for this fragment
        return vista;

    }

    private void goofferts() {
        Intent intent = new Intent(getActivity(), ShowOffert.class);
        startActivity(intent);
    }
}
