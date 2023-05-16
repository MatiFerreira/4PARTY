package com.example.a4party.BBDD;


import android.content.Context;
import android.net.Uri;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.a4party.Objetos.Empresario;
import com.example.a4party.Objetos.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class CRUD implements iCrud {
    private FirebaseFirestore db;
    private Context context;
    private DocumentReference documentReference;

    public CRUD() {

    }

    public CRUD(Context context) {
        this.context = context;
    }

    private void instanciarDatabase() {
        db = FirebaseFirestore.getInstance();
    }

    /*
     * METOODS CRUD (CREATE)----->
     *
     */
    @Override
    public void CreateUsuario(String name, String surname, String dni, String email) {
        instanciarDatabase();
        Usuario usuario = new Usuario(name, surname, dni, email);
        db.collection("Usuarios").document(email).set(usuario).addOnFailureListener(e -> {
            Toast.makeText(context, "ERROR EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void CreateAutonomo(String dni, String name, String surname, String email, String cp, String nameEstablishment) {
        instanciarDatabase();
        Empresario empresario = new Empresario(dni, name, surname, email, cp, nameEstablishment);
        db.collection("Empresarios").document(email).set(empresario).addOnFailureListener(e -> {
            Toast.makeText(context, "ERROR EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
        });
    }

    //METODO CRUD (UPDATE)--->
    //ACTUALIZA DATOS//
    @Override
    public void UpdateNameEstablishent(String email, String nameEstablishent) {
        instanciarDatabase();
        documentReference = db.collection("Empresarios").document(email);
        documentReference.update("nombreEstablecimiento", nameEstablishent).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "CAMBIO DE NOMBRE CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(context, "NO SE HA PODIDO COMPLETAR LA ACCION", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void UpdateCP(String email, String cp) {
        instanciarDatabase();
        documentReference = db.collection("Empresario").document(email);
        documentReference.update("codigoPostal", cp).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "CAMBIO EL CODIGO POSTAL CORRECTAMENTE", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Toast.makeText(context, "NO SE HA PODIDO COMPLETAR LA ACCION", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateImage(Uri uri, Context context, String email) {
        instanciarDatabase();
        db.collection("Empresarios").document(email).update("imgUrl", uri).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "SUBIDO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //METODO CRUD (DELETE)---->
    @Override
    public void DeleteEmpresario(String email) {
        instanciarDatabase();
        db.collection("Empresarios").document(email).delete();
    }

    @Override
    public void DeleteUsuario(String email) {
        instanciarDatabase();
        db.collection("Usuarios").document(email).delete();
    }


    /*
     * CRUD(READ)SSS
     * */

    @Override
    public void ReadNameEstablecimiento(String email, TextView textView) {
        instanciarDatabase();
        db.collection("Empresarios").document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String nombre = documentSnapshot.getString("nombreEstablecimiento");
                textView.setText("BIENVENIDO ".concat(nombre.toUpperCase()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                textView.setText("BIENVENIDO ANONIMO!");
            }
        });
    }

    public void ReadNameUsuario(String email) {
        instanciarDatabase();
        db.collection("Usuarios").document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                //AQUI HACEIS LO QUE QUERAIS AL OBTENER EL NOMBRE
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                //EN CASO DE NO ENCONTRAR HACER ALGO
            }
        });
    }
}
