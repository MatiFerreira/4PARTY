package com.example.a4party.LogInOutBusiness;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.a4party.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterBusiness extends AppCompatActivity {

    private EditText nombre, apellido, contrasenia1, contrasenia2, dni, correo, nameEstablecimiento, codigoPostal;
    private Button botonRegistrarse;
    AwesomeValidation validadorAwesome;
    private String nombrestr, apellidostr, contrasenia1str, contrasenia2str, dnistr, correostr, nameestablecimientostr,
            codidopostalstr;

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
        validadorAwesome = new AwesomeValidation(ValidationStyle.BASIC);
        validadorAwesome.addValidation(RegisterBusiness.this, R.id.correoAutonomoET, Patterns.EMAIL_ADDRESS, R.string.invalid_mail);
        validadorAwesome.addValidation(RegisterBusiness.this, R.id.contrasenia2AutonomoET, R.id.contraseniaAutonomoET, R.string.invalid_passw);
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //recogemos los valores
                nombrestr = nombre.getText().toString();
                apellidostr = apellido.getText().toString();
                contrasenia1str = contrasenia1.getText().toString();
                contrasenia2str = contrasenia2.getText().toString();
                dnistr = dni.getText().toString();
                correostr = correo.getText().toString();
                nameestablecimientostr = nameEstablecimiento.getText().toString();
                codidopostalstr = codigoPostal.getText().toString();
                /*------------------------------*/

                if (validadorAwesome.validate() && isEmptyAll()) {

                }
            }
        });
    }

    public boolean isEmptyAll() {
        if (nombrestr.isEmpty() && apellidostr.isEmpty() && contrasenia1str.isEmpty() &&
                contrasenia2str.isEmpty() && dnistr.isEmpty() && correostr.isEmpty() && nameestablecimientostr.isEmpty() &&
                codidopostalstr.isEmpty()) {
            Toast.makeText(this, "LOS CAMPOS NO PUEDEN ESTAR VACIO", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
