package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.a4party.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qr extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
       // EditText datos = findViewById(R.id.datos);
       // Button generador = findViewById(R.id.generateqr);
        ImageView qr = findViewById(R.id.viewqr);
        Bundle bundle= getIntent().getExtras();
        String datosextra = bundle.getString("valorqr");

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
            Bitmap bitmap = barcodeEncoder.encodeBitmap(datosextra.toString(), BarcodeFormat.QR_CODE,750,750);
            qr.setImageBitmap(bitmap);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
}