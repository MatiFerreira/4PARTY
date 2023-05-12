package com.example.a4party.IntefaceAPP;

import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a4party.BBDD.GetData;
import com.example.a4party.LogInOutBusiness.LogInBusiness;
import com.example.a4party.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LocalesActivity extends AppCompatActivity {
    private TextView textViewTitulo;
    private FirebaseAuth firebaseAuth;
    private Button botonOferta, botonModificarDatos, botonImagen, botonCerrarSesion;
    private GetData getData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_empresario);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String emailuser = user.getEmail();
        getData = new GetData();
        textViewTitulo = findViewById(R.id.textviewtitulohomeAutonomo);
        getData.setNameEstablecimiento(emailuser, textViewTitulo);
        botonOferta = findViewById(R.id.botoncrearoferta);
        botonModificarDatos = findViewById(R.id.modificardatosAutonomo);
        botonImagen = findViewById(R.id.cambiarimagenboton);
        botonCerrarSesion = findViewById(R.id.botoncerrarsesionAutonomo);
        LogOut();
    }


    private void LogOut() {
        botonCerrarSesion.setOnClickListener(view -> {
            Intent i = new Intent(this, LogInBusiness.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }

    private void AddOferta() {
        botonOferta.setOnClickListener(view -> {

        });
    }

}
