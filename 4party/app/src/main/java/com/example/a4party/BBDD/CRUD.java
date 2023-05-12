package com.example.a4party.BBDD;


import android.content.Context;
import android.widget.Toast;
import com.example.a4party.Objetos.Empresario;
import com.example.a4party.Objetos.Usuario;
import com.google.firebase.firestore.FirebaseFirestore;

public class CRUD implements iCrud {
    private FirebaseFirestore db;
    private Context context;

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
     * */
    public void CreateUsuario(String name, String surname, String dni, String email) {
        instanciarDatabase();
        Usuario usuario = new Usuario(name, surname, dni, email);
        db.collection("Usuarios").document(email).set(usuario).addOnFailureListener(e -> {
            Toast.makeText(context, "ERROR EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
        });
    }

    public void CreateAutonomo(String dni, String name, String surname, String email, String cp, String nameEstablishment) {
        instanciarDatabase();
        Empresario empresario = new Empresario(dni, name, surname, email, cp, nameEstablishment);
        db.collection("Empresarios").document(email).set(empresario).addOnFailureListener(e -> {
            Toast.makeText(context, "ERROR EN LA BASE DE DATOS", Toast.LENGTH_SHORT).show();
        });
    }

}
