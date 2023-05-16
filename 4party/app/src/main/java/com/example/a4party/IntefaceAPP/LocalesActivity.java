package com.example.a4party.IntefaceAPP;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.a4party.BBDD.CRUD;
import com.example.a4party.LogInOutBusiness.LogInBusiness;
import com.example.a4party.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;


public class LocalesActivity extends AppCompatActivity {
    private TextView textViewTitulo;
    private FirebaseAuth firebaseAuth;
    private Button botonOferta, botonModificarDatos, botonImagen, botonCerrarSesion, botonEliminarcuenta;
    private CRUD crud;
    private Uri urimage;
    private static final int IMG = 1;

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
        addImg();
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

    private void addImg() {
        botonImagen.setOnClickListener(view -> {
            intentImg();
        });
    }

    private void intentImg() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent, IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG && resultCode == RESULT_OK) {
            urimage = data.getData();
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("img").child("Empresarios" + urimage.getLastPathSegment());
            storageRef.putFile(urimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //Aqui lo meto en firestore
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String emailuser = user.getEmail();
                    crud.updateImage(urimage, LocalesActivity.this, emailuser);
                }
            });
        }
    }
}
