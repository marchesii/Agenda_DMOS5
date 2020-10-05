package com.example.agenda_dmos5.model;

public class Contato {

    private String nome;
    private String telefone_fixo;
    private String telefone_celular;
    private String usuario;



    public Contato(String nome, String telefone_fixo, String telefone_celular, String usuario){
        this.nome = nome;
        this.telefone_fixo = telefone_fixo;
        this.telefone_celular = telefone_celular;
        this.usuario = usuario;
    }

    public Contato(String nome, String telefone_fixo, String telefone_celular) {
        this.nome = nome;
        this.telefone_fixo = telefone_fixo;
        this.telefone_celular = telefone_celular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone_fixo() {
        return telefone_fixo;
    }

    public void setTelefone_fixo(String telefone_fixo) {
        this.telefone_fixo = telefone_fixo;
    }

    public String getTelefone_celular() {
        return telefone_celular;
    }

    public void setTelefone_celular(String telefone){
        this.telefone_celular = telefone;
    }

    public String getUsuario() {return usuario;}

    public void setUsuario(String usuario) {this.usuario = usuario;}


    @Override
    public String toString() {
        return "Nome: " + getNome() + "\n" +
                "Telefone Fixo: " + getTelefone_fixo() + "\n" +
                "Telefone Celular: " + getTelefone_celular() + "\n";
    }


}
