package com.example.a4party.IntefaceAPP;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.a4party.BBDD.CRUD;
import com.example.a4party.LogInOutBusiness.LogInBusiness;
import com.example.a4party.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;


public class LocalesActivity extends AppCompatActivity {
    private TextView textViewTitulo;
    private Button botonOferta, botonModificarDatos, botonImagen, botonCerrarSesion, botonEliminarcuenta;
    private ImageView imageView;
    private CRUD crud;
    private Uri urimage;
    private static final int IMG = 1;
    private String emailuser;
    private FirebaseUser user;
    private FirebaseFirestore db;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_empresario);
        user = FirebaseAuth.getInstance().getCurrentUser();
        emailuser = user.getEmail();
        crud = new CRUD(this);
        textViewTitulo = findViewById(R.id.textView4);
        crud.ReadNameEstablecimiento(emailuser, textViewTitulo);
        botonOferta = findViewById(R.id.btncrearoferta);
        imageView = findViewById(R.id.vistalocalimg);
        botonImagen = findViewById(R.id.addimagenlocal);
        botonCerrarSesion = findViewById(R.id.logoutprofileempresa);
        LogOut();
        //EliminarCuenta(emailuser, user);
        addImg();
        crearOfertas();
        /*CARGAR IMAGEN LOCAL*/
        db = FirebaseFirestore.getInstance();
        db.collection("Empresarios").document(emailuser).get().addOnSuccessListener(documentSnapshot -> {
            String imgen = documentSnapshot.getString("urlimagen");
            Picasso.get().load(imgen).fit().into(imageView);
        });
    }


    private void LogOut() {
        botonCerrarSesion.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(this, LogInBusiness.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
            finish();
        });
    }

/*    private void EliminarCuenta(String email, FirebaseUser user) {
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
    }*/

    private void AddOferta() {
        botonOferta.setOnClickListener(view -> {

        });
    }

    private void addImg() {
        botonImagen.setOnClickListener(view -> {
            intentImg();
        });
    }

    private void intentImg() { //adaptar codigo para apis inferior a 33
        // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        intent.setType("*/*");
        startActivityForResult(intent, IMG);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG && resultCode == RESULT_OK) {
            Toast.makeText(this, "SUBIENDO...", Toast.LENGTH_SHORT).show();
            urimage = data.getData();
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("img").child(emailuser);
            storageRef.putFile(urimage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    //Aqui lo meto en firestore
                    Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                    uriTask.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String url = uri.toString();
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            String emailuser = user.getEmail();
                            crud.updateImage(url, emailuser);
                            Picasso.get().load(uri).fit().into(imageView);
                        }
                    }).addOnFailureListener(e -> {
                        Toast.makeText(LocalesActivity.this, "ERROR REINTENTALO MAS TARDE", Toast.LENGTH_SHORT).show();
                    });
                }
            });
        }
    }

    private void crearOfertas() {
        botonOferta.setOnClickListener(view -> {
            Intent intent = new Intent(this, CrearOfeta.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
