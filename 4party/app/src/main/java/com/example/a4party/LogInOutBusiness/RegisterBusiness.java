package com.example.a4party.LogInOutBusiness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.a4party.BBDD.CRUD;
import com.example.a4party.R;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterBusiness extends AppCompatActivity {

    private EditText nombre, apellido, contrasenia1, contrasenia2, dni, correo, nameEstablecimiento, codigoPostal;
    private Button botonRegistrarse, botoncancell;
    AwesomeValidation validadorAwesome;
    private String nombrestr, apellidostr, contrasenia1str, contrasenia2str, dnistr, correostr, nameestablecimientostr,
            codidopostalstr;
    private FirebaseAuth firebaseAuth;
    private CRUD crud;
    private boolean isFormart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);
        //Asociamos valore de memoria a nuestros editext
        nombre = findViewById(R.id.nombreAutonomoET);
        apellido = findViewById(R.id.apellidoAutonomoET);
        contrasenia1 = findViewById(R.id.contraseniaAutonomoET);
        contrasenia2 = findViewById(R.id.contrasenia2AutonomoET);
        dni = findViewById(R.id.dniAutonomoET);
        botoncancell=findViewById(R.id.cancellautonomobutton);
        correo = findViewById(R.id.correoAutonomoET);
        nameEstablecimiento = findViewById(R.id.nameEstablecimientoET);
        codigoPostal = findViewById(R.id.codigoPostalAutonomoET);
        botonRegistrarse = findViewById(R.id.crearcuentaAutonomoBTN);
        /*----------------------------------------------------------------------*/
        //validaciones con awesomevalidator
        botoncancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLoginB();
            }
        });
    }


    private void goLoginB() {
        Intent i = new Intent(this, LogInBusiness.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}
