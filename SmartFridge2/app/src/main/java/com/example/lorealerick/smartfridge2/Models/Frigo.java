package com.example.lorealerick.smartfridge2.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by itsadmin on 13/03/2018.
 */

public class Frigo {

    @SerializedName("codice")
    private String codice;
    @SerializedName("modello")
    private String modello;
    @SerializedName("frigoAcceso")
    private int frigoAcceso;
    @SerializedName("freezerAcceso")
    private int freezerAcceso;
    @SerializedName("frigoTemp")
    private double frigoTemp;
    @SerializedName("freezerTemp")
    private double freezerTemp;
    @SerializedName("capacitaFrigo")
    private int capacitaFrigo;
    @SerializedName("capacitaFreezer")
    private int capacitaFreezer;
    @SerializedName("nPorte")
    private int nPorte;
    @SerializedName("tipoRaffreddamento")
    private String tipoRaffreddamento;
    @SerializedName("classeEnergetica")
    private String classeEnergetica;
    @SerializedName("lampadinaAccesa")
    private int lampadinaAccesa;
    @SerializedName("allarmeAttivo")
    private int allarmeAttivo;
    @SerializedName("vacationMode")
    private int vacationMode;

    public Frigo(String codice, String modello, int frigoAcceso, int freezerAcceso, double frigoTemp, double freeezerTemp, int capacitaFrigo, int capacitaFreezer, int nPorte, String tipoRaffreddamento, String classeEnergetica, int lampadinaAccesa, int allarmeAttivo, int vacationMode) {
        this.codice = codice;
        this.modello = modello;
        this.frigoAcceso = frigoAcceso;
        this.freezerAcceso = freezerAcceso;
        this.frigoTemp = frigoTemp;
        this.freezerTemp = freeezerTemp;
        this.capacitaFrigo = capacitaFrigo;
        this.capacitaFreezer = capacitaFreezer;
        this.nPorte = nPorte;
        this.tipoRaffreddamento = tipoRaffreddamento;
        this.classeEnergetica = classeEnergetica;
        this.lampadinaAccesa = lampadinaAccesa;
        this.allarmeAttivo = allarmeAttivo;
        this.vacationMode = vacationMode;
    }

    public Frigo(){

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

    public boolean getFrigoAcceso() {

        boolean isAcceso = false;

        if(frigoAcceso==1)

            isAcceso = true;
        else
            isAcceso = false;

        return isAcceso;
    }

    public void setFrigoAcceso(boolean acceso) {

        if (acceso==true)

            frigoAcceso = 1;
        else
            frigoAcceso = 0;
    }

    public boolean getFreezerAcceso() {

        boolean isAcceso = false;

        if(freezerAcceso==1)

            isAcceso = true;
        else
            isAcceso = false;

        return isAcceso;
    }

    public void setFreezerAcceso(boolean acceso) {

        if (acceso==true)

            freezerAcceso = 1;
        else
            freezerAcceso = 0;
    }

    public double getFrigoTemp() {
        return frigoTemp;
    }

    public void setFrigoTemp(double frigoTemp) {
        this.frigoTemp = frigoTemp;
    }

    public double getFreezerTemp() {
        return freezerTemp;
    }

    public void setFreezerTemp(double freezerTemp) {
        this.freezerTemp = freezerTemp;
    }

    public int getCapacitaFrigo() {
        return capacitaFrigo;
    }

    public void setCapacitaFrigo(int capacitaFrigo) {
        this.capacitaFrigo = capacitaFrigo;
    }

    public int getCapacitaFreezer() {
        return capacitaFreezer;
    }

    public void setCapacitaFreezer(int capacitaFreezer) {
        this.capacitaFreezer = capacitaFreezer;
    }

    public int getnPorte() {
        return nPorte;
    }

    public void setnPorte(int nPorte) {
        this.nPorte = nPorte;
    }

    public String getTipoRaffreddamento() {
        return tipoRaffreddamento;
    }

    public void setTipoRaffreddamento(String tipoRaffreddamento) {
        this.tipoRaffreddamento = tipoRaffreddamento;
    }

    public String getClasseEnergetica() {
        return classeEnergetica;
    }

    public void setClasseEnergetica(String classeEnergetica) {
        this.classeEnergetica = classeEnergetica;
    }

    public boolean getLampadinaAccesa() {

        boolean isAccesa = false;

        if(lampadinaAccesa == 1)

            isAccesa = true;
        else
            isAccesa = false;

        return isAccesa;
    }

    public void setLampadinaAccesa(boolean accesa) {

        if (accesa==true)

            lampadinaAccesa = 1;
        else
            lampadinaAccesa = 0;
    }

    public boolean getAllarmeAttivo() {

        boolean isAcceso = false;

        if(allarmeAttivo == 1)

            isAcceso = true;
        else
            isAcceso = false;

        return isAcceso;
    }

    public void setAllarmeAttivo(boolean acceso) {

        if (acceso==true)

            allarmeAttivo = 1;
        else
            allarmeAttivo = 0;
    }

    public boolean getVacationMode() {

        boolean isAttiva = false;

        if(vacationMode == 1)

            isAttiva = true;
        else
            isAttiva = false;

        return isAttiva;
    }

    public void setVacationMode(boolean attiva) {

        if (attiva==true)

            vacationMode = 1;
        else
            vacationMode = 0;
    }

    @Override
    public String toString (){

        StringBuilder builder = new StringBuilder();

        builder.append("-----------------------"+"\n");
        builder.append("Codice frigo "+codice+"\n");
        builder.append("Modello  "+modello+"\n");
        builder.append("Il frigo è "+frigoAcceso+"\n");
        builder.append("Il freezer è "+freezerAcceso+"\n");
        builder.append("La temperatura del frigo è "+frigoTemp+"°\n");
        builder.append("La temperatura del freezer è "+ freezerTemp +"°\n");
        builder.append("Capacità frigo "+capacitaFrigo+"\n");
        builder.append("Capacità freezer "+capacitaFreezer+"\n");
        builder.append("Numero porte "+nPorte+"\n");
        builder.append("Tipo raffreddamento "+tipoRaffreddamento+"\n");
        builder.append("Classe energetica "+classeEnergetica+"\n");
        builder.append("Lampadina "+lampadinaAccesa+"\n");
        builder.append("Allarme "+allarmeAttivo+"\n");
        builder.append("Vacation mode "+vacationMode+"\n");
        builder.append("-----------------------"+"\n");

        return builder.toString();
    }
}
