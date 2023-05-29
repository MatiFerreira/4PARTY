package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a4party.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class ShowOffert extends AppCompatActivity {

    private TextView descripcioEstable, precioEstable, nameLocal;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ofertas);
        descripcioEstable = findViewById(R.id.textView11);
        precioEstable = findViewById(R.id.preciotxtv);
        nameLocal = findViewById(R.id.namLocal);
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
    }
}