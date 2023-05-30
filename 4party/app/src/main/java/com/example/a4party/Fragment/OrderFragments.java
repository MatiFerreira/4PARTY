package com.example.a4party.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

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
        String texto = getArguments().getString("email");
        if (texto.isEmpty()) {
            texto = "";
        }
        View vista = inflater.inflate(R.layout.activity_order, container, false);

        return vista;

    }

}
