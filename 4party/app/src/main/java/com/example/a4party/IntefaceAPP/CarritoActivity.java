package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a4party.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class CarritoActivity extends AppCompatActivity {
    String email;
    Bundle datosEmail;
    private FirebaseFirestore db;
    private TextView descripciontxt, preciotxt, titulotxt;
    private Button botonback, condicion, compra;
    String decription="";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        datosEmail = getIntent().getExtras();
        descripciontxt = findViewById(R.id.decripcionCarrito);
        preciotxt = findViewById(R.id.precioCarrito);
        titulotxt = findViewById(R.id.nombrelocal);
        botonback = findViewById(R.id.botoncancelar);
        compra = findViewById(R.id.botonpagar);
        condicion = findViewById(R.id.botoncondiciones);
        db = FirebaseFirestore.getInstance();
        if (!(datosEmail == null)) {
            email = datosEmail.getString("email");
            db.collection("Productos").document(email).get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String precio = documentSnapshot.getString("precio");
                    decription = documentSnapshot.getString("descripcion");
                    descripciontxt.setText(decription);
                    preciotxt.setText(precio);
                } else {
                    descripciontxt.setText("");
                    preciotxt.setText("0.0");
                }
            });

            db.collection("Empresarios").document(email).get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    String title = documentSnapshot.getString("nombreEstablecimiento");
                    titulotxt.setText(title);
                } else {
                    titulotxt.setText("");
                }
            });
        } else {
            Intent intent = new Intent(this, CarritoEmpty.class);
            startActivity(intent);
            finish();
        }
        botonback.setOnClickListener(view -> {
            finish();
        });

        compra.setOnClickListener(view -> {
            goQr();
        });


        condicion.setOnClickListener(view -> {
            Intent intent = new Intent(this, Condiciones.class);
            startActivity(intent);
        });
    }

    private void goQr() {

        if (decription.equals("")){
            Toast.makeText(this, "Compra no disponible", Toast.LENGTH_SHORT).show();
            goCarrito();

        }else {
            Intent intent = new Intent(this, Qr.class);
            intent.putExtra("valorqr",decription);
            startActivity(intent);
        }
    }
    private void goCarrito() {
        Intent intent = new Intent(this, CarritoActivity.class);
        startActivity(intent);
    }
}