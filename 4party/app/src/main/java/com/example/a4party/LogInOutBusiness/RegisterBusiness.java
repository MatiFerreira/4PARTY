package com.example.a4party.LogInOutBusiness;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a4party.BBDD.CRUD;
import com.example.a4party.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterBusiness extends AppCompatActivity {

    private EditText nombre, apellido, contrasenia1, contrasenia2, dni, correo, nameEstablecimiento, direccionLocal;
    private Button botonRegistrarse, botoncancell;
    private String nombrestr, apellidostr, contrasenia1str, contrasenia2str, dnistr, correostr, nameestablecimientostr,
            direccionstr;
    private FirebaseAuth firebaseAuth;
    private CRUD crud;
    private boolean isFormart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_business);
        firebaseAuth = FirebaseAuth.getInstance();//instancia de Firebase Auth
        //Asociamos valore de memoria a nuestros editext
        nombre = findViewById(R.id.nombreAutonomoET);
        apellido = findViewById(R.id.apellidoAutonomoET);
        contrasenia1 = findViewById(R.id.contraseniaAutonomoET);
        contrasenia2 = findViewById(R.id.contrasenia2AutonomoET);
        dni = findViewById(R.id.dniAutonomoET);
        botoncancell = findViewById(R.id.cancellautonomobutton);
        correo = findViewById(R.id.correoAutonomoET);
        nameEstablecimiento = findViewById(R.id.nameEstablecimientoET);
        direccionLocal = findViewById(R.id.codigoPostalAutonomoET);
        botonRegistrarse = findViewById(R.id.crearcuentaAutonomoBTN);
        crud = new CRUD(this);
        /*----------------------------------------------------------------------*/
        botoncancell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLoginB();
            }
        });

        botonRegistrarse.setOnClickListener(view -> {
            //recogemos los datos
            nombrestr = nombre.getText().toString();
            apellidostr = apellido.getText().toString();
            contrasenia1str = contrasenia1.getText().toString();
            contrasenia2str = contrasenia2.getText().toString();
            dnistr = dni.getText().toString();
            correostr = correo.getText().toString();
            direccionstr = direccionLocal.getText().toString();
            nameestablecimientostr = nameEstablecimiento.getText().toString();
            //VALIDAMOS DATOS
            //comprobar dni
            comprobarDNI(dnistr);
            //campos vacios
            comprobarIsvacio();
            //comprobar password
            comprobarContrasenia();
            lengthContraenia();
            // si esta bien formado que ejecute lo siguiente!
            if (isFormart) {
                firebaseAuth.createUserWithEmailAndPassword(correostr, contrasenia2str).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        crud.CreateAutonomo(dnistr, nombrestr, apellidostr, correostr, direccionstr, nameestablecimientostr);
                        //is todo a salido correctamenta hará lo siguiente
                        Toast.makeText(this, "REGISTRADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        //almacenamos al Empresario.
                        goLoginB();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(RegisterBusiness.this, "ALGO A OCURRIDO MAL REINTENTELO DE NUEVO", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void goLoginB() {
        Intent i = new Intent(this, LogInBusiness.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    private void comprobarDNI(String dni) {
        Pattern pat = Pattern.compile("[0-9]{7,8}[A-Za-z]");
        Matcher mat = pat.matcher(dni);
        boolean cumplePatron = mat.matches();
        if (!cumplePatron) {
            isFormart = false;
            Toast.makeText(this, "FORMATO DNI INCORRECTO", Toast.LENGTH_SHORT).show();
        } else {
            isFormart = true;
        }
    }

    private void comprobarIsvacio() {
        //compruebo si algun campo esta vacio
        if (nombrestr.isEmpty() || apellidostr.isEmpty() || dnistr.isEmpty() || correostr.isEmpty() || direccionstr.isEmpty()) {
            Toast.makeText(this, "CAMPOS VACIOS", Toast.LENGTH_SHORT).show();
            isFormart = false;
        } else {
            isFormart = true;
        }
    }

    private void comprobarContrasenia() {
        if (!contrasenia1str.equals(contrasenia2str) || contrasenia1str.isEmpty()) {
            Toast.makeText(this, "CONTRASEÑA VACIA O NO COINCIDEN!", Toast.LENGTH_SHORT).show();
            isFormart = false;
        } else {
            isFormart = true;
        }
    }

    private void lengthContraenia() {
        if (contrasenia1str.length() < 6) {
            Toast.makeText(this, "CONTRASEÑA DEBE SER MINIMO 6 DIGITOS", Toast.LENGTH_SHORT).show();
            isFormart = false;
        } else {
            isFormart = true;
        }
    }
}
