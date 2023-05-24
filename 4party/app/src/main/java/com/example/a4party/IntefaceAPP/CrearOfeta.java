package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.a4party.R;

public class CrearOfeta extends AppCompatActivity {
    private Button botoncrearoferta, botoncancelaroferta;
    private EditText direccion, precio, descripcion;
    private String getDireccion, getDescripcion, getPrecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_ofertas_activity);
        botoncancelaroferta = findViewById(R.id.button2);
        botoncrearoferta = findViewById(R.id.button);
        direccion = findViewById(R.id.editTextText2);
        precio = findViewById(R.id.editTextNumberDecimal);
        descripcion = findViewById(R.id.editTextText);
        recogerDescripcion();
        recogerDireccion();
        recogerPrecio();
        cancelarOfrt();
    }

    private void recogerDireccion() {
        getDireccion = direccion.getText().toString();
    }

    private void recogerDescripcion() {
        getDescripcion = descripcion.getText().toString();
    }

    private void recogerPrecio() {
        getPrecio = precio.getText().toString();
    }

    private void cancelarOfrt() {
        botoncancelaroferta.setOnClickListener(view -> {
            Intent i = new Intent(this, LocalesActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }
}