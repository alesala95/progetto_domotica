package com.example.itsadmin.smartfridge_fragment.Singleton;

/**
 * Created by itsadmin on 22/02/2018.
 */

public class Frigorifero {

    private static Frigorifero frigorifero;

    private String nome;
    private String codice;

    private Frigorifero(){

    }

    public static Frigorifero getInstance(){

        if(frigorifero==null)
            frigorifero = new Frigorifero();

        return frigorifero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }
}
