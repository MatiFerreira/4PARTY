package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.a4party.R;

public class MostrarValores extends AppCompatActivity {

    TextView mostrar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_valores);
        Bundle bundle= getIntent().getExtras();
        String datosextra = bundle.getString("valorqr");

        mostrar = findViewById(R.id.mostrar_valor);
        mostrar.setText(datosextra);
    }
}