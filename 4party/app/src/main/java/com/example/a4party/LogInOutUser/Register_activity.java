package com.example.a4party.LogInOutUser;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.a4party.BBDD.CRUD;
import com.example.a4party.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register_activity extends AppCompatActivity {

    private Button Registrarse;
    private Button Cancelar;
    private EditText contrasenia1ET, contrasenia2ET, nombreET, apellidoET, emailET, dniET;
    private String password, password2, name, surname, email, dni;
    private FirebaseAuth firebaseauthor;
    private CRUD crud;

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
        /*=====================================================*/
        Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = contrasenia1ET.getText().toString();
                password2 = contrasenia2ET.getText().toString();
                email = emailET.getText().toString();
                name = nombreET.getText().toString();
                surname = apellidoET.getText().toString();
                dni = dniET.getText().toString();


                //todo sale con exito -->
                firebaseauthor.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //mensaje de exito

                            // crud.almacenarUsuario(usuario);
                        } else {

                        }
                    }

                });
            }
        });

    }

    private void errorCampoVacio() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Register_activity.this);
        builder.setTitle("Error");
        builder.setMessage("Tienes que rellenar todos los campos");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Código que se ejecuta cuando se hace clic en el botón OK

            }
        });
        builder.show();
    }

    private void errorDni() {
        Pattern pattern = Pattern.compile("[0-9]{8},[A-Z]{1}");
        Matcher matcher = pattern.matcher(dni);
        if (!matcher.matches()) {
            Toast.makeText(this, "FORMATO INCORRECTO POR FAVOR 8DIGITOS Y 1 LETRA MAYUSCULA"
                    , Toast.LENGTH_SHORT).show();
        }
    }
}
