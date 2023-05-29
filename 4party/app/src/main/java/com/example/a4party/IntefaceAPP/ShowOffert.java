package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a4party.R;

public class ShowOffert extends AppCompatActivity {

    private TextView descripcioEstable, precioEstable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_ofertas);
        descripcioEstable = findViewById(R.id.textView11);
        precioEstable = findViewById(R.id.preciotxtv);
    }
}