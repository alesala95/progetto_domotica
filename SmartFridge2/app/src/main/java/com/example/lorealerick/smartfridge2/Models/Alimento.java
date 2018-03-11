package com.example.lorealerick.smartfridge2.Models;


import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.ReadableInstant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * Created by LoreAleRick on 10/03/2018.
 */

public class Alimento {

    public static final int BUONO = 0;
    public static final int SCADUTO = 1;
    public static final int IN_SCADENZA = 2;
    public static final int SCADE_OGGI = 3;

    @SerializedName("idAlimento")
    int idAlimento;
    @SerializedName("nome")
    String nome;
    @SerializedName("data_inserimento")
    String data_inserimento;
    @SerializedName("stima_scadenza")
    int stima_scadenza;
    @SerializedName("image")
    byte [] image;

    public Alimento(String nome, String data_inserimento, int stima_scadenza, int idAlimento, byte [] image) {
        this.idAlimento = idAlimento;
        this.nome = nome;
        this.data_inserimento = data_inserimento;
        this.stima_scadenza = stima_scadenza;
        this.image = image;
    }

    public Alimento (){

    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_inserimento() {
        return data_inserimento;
    }

    public void setData_inserimento(String data_inserimento) {
        this.data_inserimento = data_inserimento;
    }

    public int getStima_scadenza() {
        return stima_scadenza;
    }

    public void setStima_scadenza(int stima_scadenza) {
        this.stima_scadenza = stima_scadenza;
    }

    public String getDataScadenza (){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar calendar = new GregorianCalendar();

        String dataFormattataInserimento = "";
        dataFormattataInserimento += (data_inserimento.substring(8,10)+"-");
        dataFormattataInserimento += (data_inserimento.substring(5,7)+"-");
        dataFormattataInserimento += (data_inserimento.substring(0,4));

        /*
        System.out.println("----------------------");
        System.out.println(nome);
        String dataFormattataInserimento = "";
        dataFormattataInserimento += (data_inserimento.substring(8,10)+"-");
        dataFormattataInserimento += (data_inserimento.substring(5,7)+"-");
        dataFormattataInserimento += (data_inserimento.substring(0,4));
        System.out.println(dataFormattataInserimento);
        System.out.println();
        System.out.println("Stima scadenza "+stima_scadenza);
        System.out.println();
        */

        try {

            calendar.setTime(simpleDateFormat.parse(dataFormattataInserimento));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        calendar.add(Calendar.DAY_OF_MONTH, stima_scadenza);

        String scadenza = calendar.get(Calendar.DAY_OF_MONTH)+"-"+((calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.YEAR));

        return scadenza;
    }

    public boolean isInScadenza (){

        boolean isInScadenza = false;

        if(getStato() == Alimento.IN_SCADENZA){

            isInScadenza = true;
        }

        return isInScadenza;
    }

    public boolean isScaduto (){

        boolean scaduto = false;

        if (getStato() == Alimento.SCADUTO){

            scaduto = true;
        }

        return scaduto;
    }

    public String getStatoString (){

        String stato = "";

        switch (getStato()){

            case Alimento.BUONO:

                stato = "Buono";
                break;

            case Alimento.IN_SCADENZA:

                stato = "In Scadenza";
                break;

            case Alimento.SCADE_OGGI:

                stato = "Scade oggi";
                break;

            case Alimento.SCADUTO:

                stato = "Scaduto";
                break;
        }

        return stato;
    }

    public int giorniScaduto (){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar calendar = new GregorianCalendar();

        try {

            calendar.setTime(simpleDateFormat.parse(getDataScadenza()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateTime end = new DateTime(Calendar.getInstance().getTimeInMillis());
        DateTime start = new DateTime(calendar.getTimeInMillis());

        int diffe = Days.daysBetween(start.toLocalDate(),end.toLocalDate()).getDays();

        if(diffe > 0){

            return diffe;
        }

        return -1;
    }

    public int getStato (){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar calendar = new GregorianCalendar();

        try {

            calendar.setTime(simpleDateFormat.parse(getDataScadenza()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DateTime end = new DateTime(Calendar.getInstance().getTimeInMillis());
        DateTime start = new DateTime(calendar.getTimeInMillis());

        int diffe = Days.daysBetween(start.toLocalDate(),end.toLocalDate()).getDays();

        if(diffe >= 0){

            if(diffe == 0) {

                // scade oggi
                return 3;
            }else{

                // scaduto
                return 1;
            }

        }else if (diffe < -3){

            // buono
            return 0;
        }else{

            // in scadenza
            return 2;
        }
    }

    @Override
    public String toString(){

        return "ciao sono una "+nome;
    }
}
