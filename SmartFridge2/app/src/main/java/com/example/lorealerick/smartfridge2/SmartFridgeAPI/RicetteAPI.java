package com.example.lorealerick.smartfridge2.SmartFridgeAPI;

import com.example.lorealerick.smartfridge2.Models.Ricetta;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by LoreAleRick on 10/03/2018.
 */

public interface RicetteAPI {

    @GET("/smartfridge2/ricette.php")
    Call<ArrayList<Ricetta>> getVetrinaRicette ();

    @GET("/ricette.php")
    Call<ArrayList<Ricetta>> getRicetta (@QueryMap Map<String,Object> values);
}
