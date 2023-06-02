package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a4party.R;

public class CarritoEmpty extends AppCompatActivity {

    Button back;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_empty);
        back=findViewById(R.id.backButtoncarrito);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goback();

            }
        });
    }

    private void goback() {
        finish();
    }
}