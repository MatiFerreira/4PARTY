package com.example.a4party.BBDD;


import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.a4party.Objetos.Empresario;
import com.example.a4party.Objetos.Usuario;
import com.google.firebase.database.*;
import org.jetbrains.annotations.NotNull;

public class CRUD {

    private DatabaseReference databaseReference;

    public CRUD() {

    }

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
            , String nombreEstablecimiento) {
        instanciarDatabase();
        Empresario empresario = new Empresario(DNI, nombre, apellido, email, codpostal, nombreEstablecimiento, true);
        databaseReference.child("empresas").child(DNI).setValue(empresario);
    }

    public void almacenarLocales() {
        instanciarDatabase();

    }
}
