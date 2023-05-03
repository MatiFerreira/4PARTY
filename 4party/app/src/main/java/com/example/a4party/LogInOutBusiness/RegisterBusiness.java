package com.example.a4party.LogInOutBusiness;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.a4party.BBDD.CRUD;
import com.example.a4party.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.jetbrains.annotations.NotNull;

public class RegisterBusiness extends AppCompatActivity {

    private EditText nombre, apellido, contrasenia1, contrasenia2, dni, correo, nameEstablecimiento, codigoPostal;
    private Button botonRegistrarse;
    AwesomeValidation validadorAwesome;
    private String nombrestr, apellidostr, contrasenia1str, contrasenia2str, dnistr, correostr, nameestablecimientostr,
            codidopostalstr;
    private FirebaseAuth firebaseAuth;
    private CRUD crud;
    private boolean isFormart;

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
        correo = findViewById(R.id.correoAutonomoET);
        nameEstablecimiento = findViewById(R.id.nameEstablecimientoET);
        codigoPostal = findViewById(R.id.codigoPostalAutonomoET);
        botonRegistrarse = findViewById(R.id.crearcuentaAutonomoBTN);
        /*----------------------------------------------------------------------*/
        //validaciones con awesomevalidator

    }
}
