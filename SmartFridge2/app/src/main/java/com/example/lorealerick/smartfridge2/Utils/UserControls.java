package com.example.lorealerick.smartfridge2.Utils;

import com.example.lorealerick.smartfridge2.Models.Utente;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.UtenteAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by LoreAleRick on 16/03/2018.
 */

public class UserControls {

    public static boolean isUserExist (String email, String password, boolean ifExistRefreshSingleton) {

        boolean esiste = false;
        Utente utente = null;

        final UtenteAPI utenteAPI = Services.getInstance().getRetrofit().create(UtenteAPI.class);

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        Call<Utente> call = utenteAPI.accedi(map);

        try {

            utente = call.execute().body();
            System.out.println("Response");
        } catch (IOException e) {

            e.printStackTrace();
        }

        System.out.println(utente.toString());

        if (utente.geteMail() != null && utente.getPassword() != null){

            if(ifExistRefreshSingleton){

                UtenteCorrente.getInstance().setNomeUtente(utente.getNome());
                UtenteCorrente.getInstance().seteMail(utente.geteMail());
                UtenteCorrente.getInstance().setPassword(utente.getPassword());
                UtenteCorrente.getInstance().setCognomeUtente(utente.getCognome());
            }

            esiste = true;
        }

        return esiste;
    }

    public static boolean aggiungiUtente (String email, String password, String nome, String cognome){

        boolean aggiunto = false;
        int ok = 0;

        final UtenteAPI utenteAPI = Services.getInstance().getRetrofit().create(UtenteAPI.class);

        Map<String, Object> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        map.put("nome", nome);
        map.put("cognome", cognome);

        Call <Integer> call = utenteAPI.aggiungiUtente(map);

        try {

            ok = call.execute().body();
            System.out.println("Response");
        } catch (IOException e) {

            e.printStackTrace();
        }

        if (ok == 1)
            aggiunto = true;
        else
            aggiunto = false;

        return aggiunto;
    }
}
