package com.example.a4party.BBDD;

public interface iCrud {

    public void CreateUsuario(String name, String surname, String dni, String email);
    public void CreateAutonomo(String dni, String name, String surname, String email, String cp, String nameEstablishment);
}
