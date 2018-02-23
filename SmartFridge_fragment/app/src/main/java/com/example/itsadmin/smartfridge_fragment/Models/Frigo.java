package com.example.itsadmin.smartfridge_fragment.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itsadmin on 23/02/2018.
 */

public class Frigo {

    @SerializedName("Codice")
    String codice;
    @SerializedName("Modello")
    String modello;
    @SerializedName("Capacità_frigo")
    int capacita_frigo;
    @SerializedName("Capacità_freezer")
    int capacita_freezer;
    @SerializedName("Numero_porte")
    int numero_porte;
    @SerializedName("Tipo_raffreddamento")
    String tipo_raffreddamento;
    @SerializedName("Classe_energetica")
    String  classe_energetica;
    @SerializedName("Stato_lampadina")
    int stato_lampadina;
    @SerializedName("Stato_allarme")
    int stato_allarme;
    @SerializedName("Vacation_mode")
    int vacation_mode;

    public Frigo(String codice, String modello, int capacita_frigo, int capacita_freezer, int numero_porte, String tipo_raffreddamento,
                 String classe_energetica, int stato_lampadina, int stato_allarme, int vacation_mode) {
        this.codice = codice;
        this.modello = modello;
        this.capacita_frigo = capacita_frigo;
        this.capacita_freezer = capacita_freezer;
        this.numero_porte = numero_porte;
        this.tipo_raffreddamento = tipo_raffreddamento;
        this.classe_energetica = classe_energetica;
        this.stato_lampadina = stato_lampadina;
        this.stato_allarme = stato_allarme;
        this.vacation_mode = vacation_mode;
    }

    public Frigo(Frigo frigo) {

        this.codice = frigo.getCodice();
        this.modello = frigo.getModello();
        this.capacita_frigo = frigo.getCapacita_frigo();
        this.capacita_freezer = frigo.getCapacita_freezer();
        this.numero_porte = frigo.getNumero_porte();
        this.tipo_raffreddamento = frigo.getTipo_raffreddamento();
        this.classe_energetica = frigo.getClasse_energetica();
        this.stato_lampadina = frigo.getStato_lampadina();
        this.stato_allarme = frigo.getStato_allarme();
        this.vacation_mode = frigo.getVacation_mode();

    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getModello() {
        return modello;
    }

    public void setModello(String modello) {
        this.modello = modello;
    }

    public int getCapacita_frigo() {
        return capacita_frigo;
    }

    public void setCapacita_frigo(int capacita_frigo) {
        this.capacita_frigo = capacita_frigo;
    }

    public int getCapacita_freezer() {
        return capacita_freezer;
    }

    public void setCapacita_freezer(int capacita_freezer) {
        this.capacita_freezer = capacita_freezer;
    }

    public int getNumero_porte() {
        return numero_porte;
    }

    public void setNumero_porte(int numero_porte) {
        this.numero_porte = numero_porte;
    }

    public String getTipo_raffreddamento() {
        return tipo_raffreddamento;
    }

    public void setTipo_raffreddamento(String tipo_raffreddamento) {
        this.tipo_raffreddamento = tipo_raffreddamento;
    }

    public String getClasse_energetica() {
        return classe_energetica;
    }

    public void setClasse_energetica(String classe_energetica) {
        this.classe_energetica = classe_energetica;
    }

    public int getStato_lampadina() {
        return stato_lampadina;
    }

    public void setStato_lampadina(int stato_lampadina) {
        this.stato_lampadina = stato_lampadina;
    }

    public int getStato_allarme() {
        return stato_allarme;
    }

    public void setStato_allarme(int stato_allarme) {
        this.stato_allarme = stato_allarme;
    }

    public int getVacation_mode() {
        return vacation_mode;
    }

    public void setVacation_mode(int vacation_mode) {
        this.vacation_mode = vacation_mode;
    }
}
