package com.example.agenda_dmos5.model;

public class Usuario {

    private String email;
    private String senha;
    private int logado = 0;


    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    @Override
    public String toString() {
        return "Nome: " + getEmail() +"\n";
    }
}
