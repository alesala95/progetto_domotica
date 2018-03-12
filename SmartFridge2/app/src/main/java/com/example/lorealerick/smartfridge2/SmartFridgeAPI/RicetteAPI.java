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

    @GET("/smartfridge2/getRicetta.php")
    Call<Ricetta> getRicetta (@QueryMap Map<String,Object> values);

    @GET("/smartfridge2/ricetteConsigliate.php")
    Call<ArrayList<Ricetta>> getRicetteConsigliate (@QueryMap Map<String,Object> values);

    @GET("/smartfridge2/ricetteForCategoriaOffset.php")
    Call<ArrayList<Ricetta>> getRicetteForCategoryOffset (@QueryMap Map<String,Object> values);
}
