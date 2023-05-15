package com.example.a4party.IntefaceAPP;

import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a4party.BBDD.CRUD;
import com.example.a4party.LogInOutBusiness.LogInBusiness;
import com.example.a4party.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LocalesActivity extends AppCompatActivity {
    private TextView textViewTitulo;
    private FirebaseAuth firebaseAuth;
    private Button botonOferta, botonModificarDatos, botonImagen, botonCerrarSesion, botonEliminarcuenta;
    private CRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_empresario);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String emailuser = user.getEmail();
        crud = new CRUD(this);
        textViewTitulo = findViewById(R.id.textviewtitulohomeAutonomo);
        crud.ReadNameEstablecimiento(emailuser, textViewTitulo);
        botonOferta = findViewById(R.id.botoncrearoferta);
        botonModificarDatos = findViewById(R.id.modificardatosAutonomo);
        botonImagen = findViewById(R.id.cambiarimagenboton);
        botonCerrarSesion = findViewById(R.id.botoncerrarsesionAutonomo);
        botonEliminarcuenta = findViewById(R.id.botonteliminarcuenta);
        LogOut();
        EliminarCuenta(emailuser, user);
    }


    private void LogOut() {
        botonCerrarSesion.setOnClickListener(view -> {
            Intent i = new Intent(this, LogInBusiness.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }

    private void EliminarCuenta(String email, FirebaseUser user) {
        botonEliminarcuenta.setOnClickListener(view -> {
            AlertDialog.Builder build = new AlertDialog.Builder(LocalesActivity.this);
            build.setTitle("ADVERTENCIA!");
            build.setMessage("ESTAS SEGURO DE ELIMINAR TU CUENTA?").setPositiveButton(R.string.alertyes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    crud.DeleteEmpresario(email);
                    user.delete().addOnCompleteListener(task -> {
                        Toast.makeText(LocalesActivity.this, "ELIMINANDO..", Toast.LENGTH_SHORT).show();
                        Intent di = new Intent(LocalesActivity.this, LogInBusiness.class);
                        di.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(di);
                        finish();
                    });
                }
            }).setNegativeButton(R.string.alertno, (dialogInterface, i) -> {
                dialogInterface.dismiss();
            }).setCancelable(false).show();
        });
    }

    private void AddOferta() {
        botonOferta.setOnClickListener(view -> {

        });
    }

    private void getImage() {

    }
}
