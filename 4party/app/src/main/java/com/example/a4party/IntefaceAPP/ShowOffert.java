package com.example.a4party.IntefaceAPP;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4party.Fragment.PartySearchFragment;
import com.example.a4party.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowOffert extends AppCompatActivity {

    private TextView descripcioEstable, precioEstable, nameLocal;
    private FirebaseFirestore db;
    private Button botonQR, botonCancelar, botonAbrirgooglemaps;

    private String email;


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
        email = datosEmail.getString("email");
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
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        });

        botonCancelar.setOnClickListener(view -> {
            Intent i = new Intent(this, PartySearchFragment.class);
            startActivity(i);
            finish();
        });

        botonQR.setOnClickListener(view -> {
            goCarrito();
        });



    }
    private void goCarrito() {
        Intent intent = new Intent(this, HOME_ACTIVITY.class);
        intent.putExtra("fragment", "carrito");
        intent.putExtra("hola", "hola");// Envía un identificador del fragmento "carrito"
        startActivity(intent);
    }

    public String getEmail() {
        return email;
    }
}