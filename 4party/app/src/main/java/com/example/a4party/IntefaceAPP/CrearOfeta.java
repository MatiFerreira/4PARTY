package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a4party.BBDD.CRUD;
import com.example.a4party.Objetos.Productos;
import com.example.a4party.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CrearOfeta extends AppCompatActivity {
    private Button botoncrearoferta, botoncancelaroferta;
    private EditText precio, descripcion;
    private String getDescripcion;
    private String getPrecio;
    private FirebaseUser user;
    private String emailuser;
    private CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = FirebaseAuth.getInstance().getCurrentUser();
        emailuser = user.getEmail();
        setContentView(R.layout.crear_ofertas_activity);
        crud = new CRUD(this);
        botoncancelaroferta = findViewById(R.id.button2);
        botoncrearoferta = findViewById(R.id.button);
        precio = findViewById(R.id.editTextNumberDecimal);
        descripcion = findViewById(R.id.editTextText);
        crearOfeta();
        cancelarOfrt();
    }

    private void cancelarOfrt() {
        botoncancelaroferta.setOnClickListener(view -> {
            Intent i = new Intent(this, LocalesActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }

    private void crearOfeta() {
        botoncrearoferta.setOnClickListener(view -> {
            if (descripcion.getText().toString().isEmpty() || precio.getText().toString().isEmpty()) {
                Toast.makeText(this, "NINGUN CAMPO PUEDE ESTAR VACIO", Toast.LENGTH_SHORT).show();
            } else {
                getDescripcion = descripcion.getText().toString();
                getPrecio = precio.getText().toString();
                crud.CreateProductos(emailuser, getPrecio, getDescripcion);
                finish();
            }
        });
    }

}