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
import com.example.a4party.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import org.jetbrains.annotations.NotNull;


public class LogInBusiness extends AppCompatActivity {
    private AwesomeValidation validadorAwesome;
    private Button botonLogin, botonRegister;
    private EditText correoBusiness, contraseniaBusiness;
    private FirebaseAuth firebaseauthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_business);
        validadorAwesome = new AwesomeValidation(ValidationStyle.BASIC);
        contraseniaBusiness = findViewById(R.id.contraseniabusinessET);
        validadorAwesome.addValidation(LogInBusiness.this, R.id.businessemailET, Patterns.EMAIL_ADDRESS, R.string.invalid_mail);
        botonLogin = findViewById(R.id.loginbusinessBT);
        botonRegister = findViewById(R.id.registerBusinessBT);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validadorAwesome.validate();
                //recogemos valores de los campo texfield
                String correoString = correoBusiness.getText().toString();
                String contraseniaString = contraseniaBusiness.getText().toString();
                signIn(correoString, contraseniaString);
            }
        });

        botonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //METODO PARA INICIAR SESION!
    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        firebaseauthor.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser usuario = firebaseauthor.getCurrentUser();
                            updateUI(usuario);
                            //INTENT A LA PAGINA DONDE SE VERAN LAS COSAS

                            //fin del login
                            finish();
                        }
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(LogInBusiness.this, "USUARIO NO EXISTE O DATOS INCORRECTOS", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI(FirebaseUser user) {

    }


}