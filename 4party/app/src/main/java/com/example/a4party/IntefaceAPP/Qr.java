package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a4party.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qr extends AppCompatActivity {

    Button qrback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
       // EditText datos = findViewById(R.id.datos);
       // Button generador = findViewById(R.id.generateqr);
        ImageView qr = findViewById(R.id.viewqr);
        Bundle bundle= getIntent().getExtras();
        qrback = findViewById(R.id.backButtonqr);
        String datosextra = bundle.getString("valorqr");
        String datosextra2 = bundle.getString("valorprecio");

        String valorfinal = datosextra +"\n\nPrecio: "+datosextra2 ;
       // generador.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   try{
               //     BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                 //   Bitmap bitmap = barcodeEncoder.encodeBitmap(datos.getText().toString(), BarcodeFormat.QR_CODE,750,750);

                   // qr.setImageBitmap(bitmap);
           //     }catch (Exception e){
             //       e.printStackTrace();
               // }
           // }
       // });

        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            Bitmap bitmap = barcodeEncoder.encodeBitmap(valorfinal, BarcodeFormat.QR_CODE,750,750);
            qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }

        qrback.setOnClickListener(new View.OnClickListener() {
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