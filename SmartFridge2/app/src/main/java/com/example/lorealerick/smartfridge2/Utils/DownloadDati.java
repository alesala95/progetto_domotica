package com.example.lorealerick.smartfridge2.Utils;

import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Frigo;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.AlimentiAPI;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.FrigoAPI;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.RicetteAPI;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;



public class DownloadDati {

    private static final int height = 175;
    private static final int width = 175;

    public static Frigo scaricaInfoFrigo (){


        final FrigoAPI ricetteAPI = Services.getInstance().getRetrofit().create(FrigoAPI.class);
        Frigo frigo = null;

        Map <String, Object> map = new HashMap<>();

        map.put("codice", UtenteCorrente.getInstance().getCodiceFrigo());

        Call <Frigo> call = ricetteAPI.getInfoFrigo(map);

        try {
            frigo = call.execute().body();

            System.out.println("Response");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return frigo;
    }

    public static ArrayList<Ricetta> scaricaVetrinaRicette (){

        RicetteAPI ricetteAPI = Services.getInstance().getRetrofit().create(RicetteAPI.class);
        Ricetta ricetta=null;

        Call<ArrayList<Ricetta>> call = ricetteAPI.getVetrinaRicette();

        ArrayList <Ricetta> ricette = new ArrayList<>();


        try {
            ricette = call.execute().body();

        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ricette.size(); i++){

            try {
                if (!ricette.get(i).getAutore().equals("SmartFridge"))
                    ricette.get(i).setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/not.jpg").resize(height,width).get()));
                else
                    ricette.get(i).setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/"+ricette.get(i).getId()+".jpg").resize(height,width).get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ricette;
    }

    public static ArrayList<Alimento> scaricaAlimenti (){

        final AlimentiAPI alimentiAPI = Services.getInstance().getRetrofit().create(AlimentiAPI.class);

        Map<String, Object> map = new HashMap<>();

        map.put("codiceFrigo", UtenteCorrente.getInstance().getCodiceFrigo());

        Call<ArrayList<Alimento>> call = alimentiAPI.getAlimenti(map);

        ArrayList <Alimento> alimenti = new ArrayList<>();

        try {
            alimenti = call.execute().body();

            System.out.println("Response");
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (int i = 0; i < alimenti.size(); i++){

            try {
                alimenti.get(i).setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimento/"+alimenti.get(i).getIdAlimento()+".jpg").resize(height,width).get()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(alimenti.get(i).toString());
        }

        return alimenti;
    }

    public static ArrayList <Ricetta> getRicetteForCategoryOffset (String categoria, int nRis, int page){

        final RicetteAPI ricetteAPI = Services.getInstance().getRetrofit().create(RicetteAPI.class);

        Map<String, Object> map = new HashMap<>();

        map.put("categoria",categoria);
        map.put("nRes",nRis);
        map.put("page",page);

        Call <ArrayList<Ricetta>> call = ricetteAPI.getRicetteForCategoryOffset(map);

        ArrayList <Ricetta> ricette = new ArrayList<>();

        try {
            ricette = call.execute().body();

            System.out.println("Response");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ricette.size(); i++){

            try {
                if (!ricette.get(i).getAutore().equals("SmartFridge"))
                    ricette.get(i).setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/not.jpg").resize(height,width).get()));
                else
                    ricette.get(i).setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/"+ricette.get(i).getId()+".jpg").resize(height,width).get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ricette;
    }

    public static Ricetta scaricaRicetta (int id){

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
            if (!ricetta.getAutore().equals("SmartFridge"))
                ricetta.setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/not.jpg").resize(height,width).get()));
            else
                ricetta.setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/"+ricetta.getId()+".jpg").resize(height,width).get()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ricetta;
    }

    public static ArrayList <Ricetta> scaricaFeedRicetteConsigliate (ArrayList<Alimento>alimentiInScadenza){

        final RicetteAPI ricetteAPI = Services.getInstance().getRetrofit().create(RicetteAPI.class);
        Ricetta ricetta = null;

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

        for (int i = 0; i < ricette.size(); i++){

            try {
                if (!ricette.get(i).getAutore().equals("SmartFridge"))
                    ricette.get(i).setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/not.jpg").resize(height,width).get()));
                else
                    ricette.get(i).setImage(BitmapHandle.getBytes(Picasso.get().load(Services.getInstance().getRetrofit().baseUrl()+"/img_alimenti/"+ricette.get(i).getId()+".jpg").resize(height,width).get()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return ricette;
    }
}
