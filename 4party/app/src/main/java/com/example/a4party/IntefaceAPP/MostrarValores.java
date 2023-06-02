package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a4party.R;

public class MostrarValores extends AppCompatActivity {

    TextView mostrar;
    Button back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_valores);
        Bundle bundle= getIntent().getExtras();
        back = findViewById(R.id.backButtondetail);
        String datosextra = bundle.getString("valorqr");

        mostrar = findViewById(R.id.mostrar_valor);
        mostrar.setText(datosextra);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goback();
            }
        });
    }

    private void goback() {
        Intent intent = new Intent(this, LocalesActivity.class);
        startActivity(intent);
        finish();
    }
}