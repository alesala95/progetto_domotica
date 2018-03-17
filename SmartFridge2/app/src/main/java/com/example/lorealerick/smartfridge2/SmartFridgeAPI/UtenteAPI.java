package com.example.lorealerick.smartfridge2.SmartFridgeAPI;

import com.example.lorealerick.smartfridge2.Models.Utente;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by LoreAleRick on 16/03/2018.
 */

public interface UtenteAPI {

    @GET("/smartfridge2/utenteEsiste.php")
    Call <Utente> accedi (@QueryMap Map<String,Object> values);

    @GET("/smartfridge2/aggiungiUtente.php")
    Call <Integer> aggiungiUtente (@QueryMap Map <String,Object> values);

    @GET("/smartfridge2/aggiornaPassword.php")
    Call <ResponseBody> cambiaPassword (@QueryMap Map <String,Object> values);
}
