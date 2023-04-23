package com.example.a4party;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CRUD {

    private DatabaseReference databaseReference;


    private void instanciarDatabase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    //crear usuario en la base de datos
    public void almacenarUsuario(String nombre, String apellido, String DNI, String email, String contrasenia) {
        instanciarDatabase();
        Usuario usuario = new Usuario(nombre, apellido, DNI, email, contrasenia);
        databaseReference.child("usuarios").child(DNI).setValue(usuario);
    }

}
