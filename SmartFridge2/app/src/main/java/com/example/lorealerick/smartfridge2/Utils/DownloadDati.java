package com.example.lorealerick.smartfridge2.Utils;

import android.content.Context;

import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.AlimentiAPI;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.RicetteAPI;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by LoreAleRick on 11/03/2018.
 */

public class DownloadDati {

    DatabaseAdapter databaseAdapter;

    public DownloadDati (Context context){

        databaseAdapter = new DatabaseAdapter(context);
    }

    public void scaricaVetrinaRicette (){

        RicetteAPI ricetteAPI = Services.getInstance().getRetrofit().create(RicetteAPI.class);

        Call<ArrayList<Ricetta>> call = ricetteAPI.getVetrinaRicette();

        ArrayList <Ricetta> ricette = new ArrayList<>();

        try {
            ricette = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Ricetta r : ricette){

            try {
                r.setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/"+r.getId()+".jpg").get()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            databaseAdapter.addRicetta(r);
        }
    }

    public void scaricaAlimenti (){

        final AlimentiAPI alimentiAPI = Services.getInstance().getRetrofit().create(AlimentiAPI.class);

        Map<String, Object> map = new HashMap<>();

        map.put("codiceFrigo", Utente.getInstance().getCodiceFrigo());

        Call<ArrayList<Alimento>> call = alimentiAPI.getAlimenti(map);

        ArrayList <Alimento> alimenti = new ArrayList<>();

        try {
            alimenti = call.execute().body();

            System.out.println("Response");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Alimento a : alimenti){

            try {
                a.setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimento/"+a.getIdAlimento()+".jpg").get()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(a.toString());

            databaseAdapter.addAlimento(a);
        }

    }

    public Ricetta scaricaRicetta (int id){

        final RicetteAPI ricetteAPI = Services.getInstance().getRetrofit().create(RicetteAPI.class);
        Ricetta ricetta = null;

        Map<String, Object> map = new HashMap<>();

        map.put("id",id);

        Call <Ricetta> call = ricetteAPI.getRicetta(map);

        try {
            ricetta = call.execute().body();

            System.out.println("Response");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ricetta.setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/"+ricetta.getId()+".jpg").get()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ricetta;
    }

    public ArrayList <Ricetta> scaricaFeedRicetteConsigliate (ArrayList<Alimento>alimentiInScadenza){

        final RicetteAPI ricetteAPI = Services.getInstance().getRetrofit().create(RicetteAPI.class);

        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i<alimentiInScadenza.size(); i++){

            map.put(""+i, alimentiInScadenza.get(i).getNome().toLowerCase());
        }

        Call <ArrayList<Ricetta>> call = ricetteAPI.getRicetteConsigliate(map);

        ArrayList <Ricetta> ricette = new ArrayList<>();

        try {
            ricette = call.execute().body();

            System.out.println("Response");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Ricetta r : ricette){

            try {
                r.setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/"+r.getId()+".jpg").get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ricette;
    }
}
