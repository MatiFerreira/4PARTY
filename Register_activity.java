package com.example.a4party;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Date;
import java.util.GregorianCalendar;

public class Register_activity extends AppCompatActivity {

    String nombre;
    String apellidos;
    String correoElectronico;
    String contrasena;

    private Button Registrarse;
    private Button Cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText nombreEditText = findViewById(R.id.nombreEditText);
        nombre = nombreEditText.getText().toString();

        EditText apellidosEditText = findViewById(R.id.apellidosEditText);
        apellidos = apellidosEditText.getText().toString();

        EditText correoElectronicoEditText = findViewById(R.id.correoElectronicoEditText);
        correoElectronico = correoElectronicoEditText.getText().toString();

        EditText contrasenaEditText = findViewById(R.id.contrasenaEditText);
        contrasena = contrasenaEditText.getText().toString();

        Registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.isEmpty() || apellidos.isEmpty() ||  correoElectronico.isEmpty() || contrasena.isEmpty()) {
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

                } else {
                    // guardar los datos del usuario en una base de datos o enviarlos a un servidor
                    CrearUsuario(apellidos, contrasena, correoElectronico, nombre);

                }


            }
        });



    }
}