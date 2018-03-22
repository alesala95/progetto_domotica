package com.example.lorealerick.smartfridge2.Utils;

import com.example.lorealerick.smartfridge2.Models.Utente;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.RicetteAPI;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.UtenteAPI;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by itsadmin on 21/03/2018.
 */

public class UtilsRecipe {

    public static boolean aggiungiRicetta(String nome,Integer difficolta, String durata,String autore, String ingredienti, String procedimento, String categoria){
        boolean aggiunto = false;
        int ok = 0;

        final RicetteAPI ricetteAPI = Services.getInstance().getRetrofit().create(RicetteAPI.class);

        Map<String, Object> map = new HashMap<>();
        map.put("Nome", nome);
        map.put("Difficolta", difficolta);
        map.put("Durata", durata);
        map.put("autore", autore);
        map.put("ingredienti", ingredienti);
        map.put("procedimento", procedimento);
        map.put("categoria",categoria);


        Call<Integer> call = ricetteAPI.aggiungiRicetta(map);

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
