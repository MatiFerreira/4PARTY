package com.example.a4party.LogInOutUser;

import android.content.Intent;
import android.util.Patterns;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.a4party.BBDD.CRUD;
import com.example.a4party.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register_activity extends AppCompatActivity {

    private Button Registrarse;
    private Button Cancelar;
    private EditText contrasenia1ET, contrasenia2ET, nombreET, apellidoET, emailET, dniET;
    private String password, password2, name, surname, email, dni;
    private FirebaseAuth firebaseauthor;
    private AwesomeValidation validadorAwesome;
    private CRUD crud;
    private boolean isFormart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        firebaseauthor = FirebaseAuth.getInstance();//instancia firebase auth
        /*ASIGNAR VALORES EN MEMORIA*/
        Registrarse = findViewById(R.id.BTNregistrarse);
        Cancelar = findViewById(R.id.BTNcancelar);
        contrasenia1ET = findViewById(R.id.contrasenaEditText);
        contrasenia2ET = findViewById(R.id.contrasenaEditText2);
        nombreET = findViewById(R.id.nombreEditText);
        apellidoET = findViewById(R.id.apellidosEditText);
        emailET = findViewById(R.id.correoElectronicoEditText);
        dniET = findViewById(R.id.dniEditText);


        validadorAwesome = new AwesomeValidation(ValidationStyle.BASIC);
        validadorAwesome.addValidation(Register_activity.this, R.id.correoAutonomoET, Patterns.EMAIL_ADDRESS, R.string.invalid_mail);
        validadorAwesome.addValidation(Register_activity.this, R.id.contrasenia2AutonomoET, R.id.contraseniaAutonomoET, R.string.invalid_passw);
        /*=====================================================*/
        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLoginU();
            }
        });

        //CLICK EN BOTON REGISTRARSE-->
        Registrarse.setOnClickListener(view -> {
            name = nombreET.getText().toString();
            surname = apellidoET.getText().toString();
            email = emailET.getText().toString();
            dni = dniET.getText().toString();
            password = contrasenia1ET.getText().toString();
            password2 = contrasenia2ET.getText().toString();
            //VALIDAMOS Y COMPROBAMOS DNI
            comprobarDNI(dni);
            //TAMAÑO CONTRASEÑA
            lengthContraenia();
            //IS CAMPO VACIO
            comprobarIsvacio();
            if (isFormart) {
                firebaseauthor.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(this, "REGISTRADO CORRECTAMENTE!", Toast.LENGTH_SHORT).show();
                        crud = new CRUD();
                        crud.almacenarUsuario(name, surname, dni, email);//ALMACENA AL USUARIO EN LA BBDD
                        goLoginU();
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(this, "REGISTRADO INCORRECTAMENTE COMPRUEBE LOS CAMPOS", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }

    private void goLoginU() {
        Intent i = new Intent(this, Login_activity.class);
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

    //COMPROBAR CAMPO VACIO
    private void comprobarIsvacio() {
        //compruebo si algun campo esta vacio
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "CAMPOS VACIOS", Toast.LENGTH_SHORT).show();
            isFormart = false;
        } else {
            isFormart = true;
        }
    }

    //comprobar Tamaño contraseña
    private void lengthContraenia() {
        if (password.length() < 6) {
            Toast.makeText(this, "CONTRASEÑA DEBE SER MINIMO 6 DIGITOS", Toast.LENGTH_SHORT).show();
            isFormart = false;
        } else {
            isFormart = true;
        }
    }
}
