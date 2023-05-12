package com.example.a4party.LogInOutBusiness;

import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.a4party.IntefaceAPP.HOME_ACTIVITY;
import com.example.a4party.IntefaceAPP.LocalesActivity;
import com.example.a4party.LogInOutUser.Login_activity;
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

    TextView linkTextView;
    private FirebaseAuth firebaseauthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_business);
        validadorAwesome = new AwesomeValidation(ValidationStyle.BASIC);
        contraseniaBusiness = findViewById(R.id.contraseniabusinessET);
        validadorAwesome.addValidation(this, R.id.businessemailET, Patterns.EMAIL_ADDRESS, R.string.invalid_mail);
        botonLogin = findViewById(R.id.loginbusinessBT);
        firebaseauthor = FirebaseAuth.getInstance(); //Instanciamos El login del empresario!
        linkTextView = findViewById(R.id.link_textB);
        botonRegister = findViewById(R.id.registerBusinessBT);
        correoBusiness = findViewById(R.id.businessemailET);
        contraseniaBusiness = findViewById(R.id.contraseniabusinessET);
        botonLogin.setOnClickListener(view -> {
            if (validadorAwesome.validate()) {
                //recogemos string
                String email = correoBusiness.getText().toString();
                String password = contraseniaBusiness.getText().toString();
                //Entonces haz lo siguiente!
                if (!email.isEmpty() && !password.isEmpty()) {
                    firebaseauthor.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(this, "INICIANDO SESION!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, LocalesActivity.class);
                            intent.putExtra("correo", email);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(this, "USUARIO NO REGISTRADO O ESPACIO O CREDENCIALES MAL PUESTO", Toast.LENGTH_LONG).show();
                    });
                } else {
                    Toast.makeText(this, "ALGUN CAMPO VACIO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        linkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goLoginU();
            }
        });

        botonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goRegisterB();
            }
        });
    }

    private void goRegisterB() {
        Intent i = new Intent(this, RegisterBusiness.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    private void goLoginU() {
        Intent i = new Intent(this, Login_activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }

    private void updateUI(FirebaseUser user) {

    }

}
