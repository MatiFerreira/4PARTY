package com.example.a4party.BBDD;


import com.example.a4party.Objetos.Empresario;
import com.example.a4party.Objetos.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CRUD {

    private DatabaseReference databaseReference;


    private void instanciarDatabase() {
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    //crear usuario en la base de datos
    public void almacenarUsuario(String nombre, String apellido, String DNI, String email) {
        instanciarDatabase();
        Usuario usuario = new Usuario(nombre, apellido, DNI, email);
        databaseReference.child("usuarios").child(DNI).setValue(usuario);
    }

    //metodo para insertar un nuevo empresario
    public void almacenarEmpresario(String nombre, String apellido, String DNI, String codpostal, String email
            , String contrasenia, String nombreEstablecimiento) {
        instanciarDatabase();
        Empresario empresario = new Empresario(DNI, nombre, apellido, email, codpostal, nombreEstablecimiento, true);
        databaseReference.child("empresas").child(DNI).setValue(empresario);
    }

}
