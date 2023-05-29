package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.a4party.Fragment.PartySearchFragment;
import com.example.a4party.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowOffert extends AppCompatActivity {

    private TextView descripcioEstable, precioEstable, nameLocal;
    private FirebaseFirestore db;
    private Button botonQR, botonCancelar, botonAbrirgooglemaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ofertas);
        descripcioEstable = findViewById(R.id.textView11);
        precioEstable = findViewById(R.id.preciotxtv);
        nameLocal = findViewById(R.id.namLocal);
        botonQR = findViewById(R.id.botonqr);
        botonCancelar = findViewById(R.id.botoncancel);
        botonAbrirgooglemaps = findViewById(R.id.botongooglemaps);
        Bundle datosEmail = getIntent().getExtras();
        String email = datosEmail.getString("email");
        db = FirebaseFirestore.getInstance();
        db.collection("Productos").document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String precio = documentSnapshot.getString("precio");
                String decription = documentSnapshot.getString("descripcion");
                descripcioEstable.setText(decription);
                precioEstable.setText(precio);
            } else {
                descripcioEstable.setText("");
                precioEstable.setText("0.0");
            }
        });
        db.collection("Empresarios").document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String nameEstablecimiento = documentSnapshot.getString("nombreEstablecimiento");
                nameLocal.setText(nameEstablecimiento);
            } else {
                nameLocal.setText("");
            }
        });

        botonAbrirgooglemaps.setOnClickListener(view -> {
            Intent intent = new Intent(this, MapsActivity2.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });

        botonCancelar.setOnClickListener(view -> {
            Intent i = new Intent(this, PartySearchFragment.class);
            startActivity(i);
            finish();
        });

    }
}