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

    public static boolean aggiungiRicetta(String titolo,String durata, String difficolta, String ingredienti, String procedimento){
        boolean aggiunto = false;
        int ok = 0;

        final RicetteAPI ricetteAPI = Services.getInstance().getRetrofit().create(RicetteAPI.class);

        Map<String, Object> map = new HashMap<>();
        map.put("Nome", titolo);
        map.put("Difficolta", difficolta);
        map.put("Durata", durata);
        map.put("autore", UtenteCorrente.getInstance().getNomeUtente());
        map.put("ingredienti", ingredienti);
        map.put("procedimento", procedimento);


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
