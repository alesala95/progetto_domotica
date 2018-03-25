package com.example.lorealerick.smartfridge2.Utils;

import android.content.SharedPreferences;

import com.example.lorealerick.smartfridge2.Models.Utente;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.FrigoAPI;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.UtenteAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    public static void aggiornaImpostazioniFrigo (boolean luce, boolean allarme, boolean vm){

        final FrigoAPI frigoAPI = Services.getInstance().getRetrofit().create(FrigoAPI.class);

        Map<String, Object> map = new HashMap<>();
        map.put("codiceFrigo",UtenteCorrente.getInstance().getCodiceFrigo());
        map.put("lights", UtilsBool.boolToInt(luce));
        map.put("alarm", UtilsBool.boolToInt(allarme));
        map.put("vm", UtilsBool.boolToInt(vm));

        Call <ResponseBody> call = frigoAPI.setFrigo(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                System.out.println("Frigo aggiornato");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                System.out.println("Non sono riuscito ad aggiornare");
            }
        });
    }

    public static void aggiornaPassword (final String nuovaPassword, final boolean refreshSingleton, final SharedPreferences sharedPreferences){

        final UtenteAPI utenteAPI = Services.getInstance().getRetrofit().create(UtenteAPI.class);

        Map<String, Object> map = new HashMap<>();
        map.put("email",UtenteCorrente.getInstance().geteMail());
        map.put("nuovaPassword", nuovaPassword);

        Call <ResponseBody> call = utenteAPI.cambiaPassword(map);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                System.out.println("Password aggiornata");
                if(refreshSingleton) {
                    UtenteCorrente.getInstance().setPassword(nuovaPassword);
                    sharedPreferences.edit().putString("password",nuovaPassword).apply();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                System.out.println("Non sono riuscito ad aggiornare la password");
            }
        });
    }

    public static boolean getCodiceFrigo (String ip, String pw){

        boolean conn = false;
        FrigoCodec codice = null;

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://"+ip+"/").addConverterFactory(GsonConverterFactory.create());

        final Retrofit retrofit = builder.build();
        FrigoAPI frigoAPI = retrofit.create(FrigoAPI.class);

        Map <String, Object> map = new HashMap<>();
        map.put("pw",pw);

        Call <FrigoCodec> call = frigoAPI.connettiFrigo(map);

        try {
            codice = call.execute().body();
        } catch (IOException e) {

            System.out.println("Connessione fallita");
        }

        if(codice != null)
            if (codice.getSuccess() == 1){

                conn = true;
                UtenteCorrente.getInstance().setCodiceFrigo(codice.getCodiceFrigo());
            }

        return conn;
    }
}
