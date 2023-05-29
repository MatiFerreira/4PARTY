package com.example.a4party.BBDD;

import android.content.Context;
import android.net.Uri;
import android.widget.TextView;

import com.example.a4party.Objetos.Productos;

public interface iCrud {
    /*
     *
     * CREATE
     *
     */

    public void CreateUsuario(String name, String surname, String dni, String email);

    public void CreateAutonomo(String dni, String name, String surname, String email, String cp, String nameEstablishment);

    public void CreateProductos(String email, String precio, String descripcion);

    /*
     * DELETE
     */
    public void DeleteEmpresario(String email);

    public void DeleteUsuario(String email);

    /*
     * UPDATE
     */
    public void UpdateNameEstablishent(String email, String nameEstablishent);

    public void UpdateCP(String email, String cp);

    public void updateImage(String uri, String email);

    /*
     * READ
     */
    public void ReadNameEstablecimiento(String email, TextView textView);
}
