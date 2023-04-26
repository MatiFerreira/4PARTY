package com.example.a4party.LogInOutBusiness;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.a4party.R;


public class LogInBusiness extends AppCompatActivity {
    private AwesomeValidation validadorAwesome;
    private Button botonLogin, botonRegister;
    private EditText correoBusiness, contraseniaBusiness;

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

            }
        });

        botonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
