package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterGrigliaAlimenti;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.AlimentiAPI;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;
import com.example.lorealerick.smartfridge2.Utils.Services;
import com.example.lorealerick.smartfridge2.Utils.Utente;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragFrigo extends Fragment {

    DatabaseAdapter databaseAdapter;
    ArrayList <Alimento> alimenti;
    AdapterGrigliaAlimenti adapterGrigliaAlimenti;
    DownloadAlimentiManager downloadAlimentiManager;
    ProgressBar progressBarfrigo;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        databaseAdapter = new DatabaseAdapter(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_frigo, container, false);

        alimenti = new ArrayList<>();
        progressBarfrigo = view.findViewById(R.id.progressFrigo);

        GridView grigliaAlimenti = view.findViewById(R.id.grigliaAlimenti);
        adapterGrigliaAlimenti = new AdapterGrigliaAlimenti(getActivity(),R.layout.item_alimento,alimenti);

        grigliaAlimenti.setAdapter(adapterGrigliaAlimenti);

        if(!Services.getInstance().isScaricatoAlimenti()){

            databaseAdapter.svuotaTabellaAlimenti();

            downloadAlimentiManager = new DownloadAlimentiManager();
            downloadAlimentiManager.execute();

            Services.getInstance().setScaricatoAlimenti(true);
        }else{

            aggiorna();
        }

        return view;
    }

    private class DownloadAlimentiManager extends AsyncTask <Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBarfrigo.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            final AlimentiAPI alimentiAPI = Services.getInstance().getRetrofit().create(AlimentiAPI.class);

            Map <String, Object> map = new HashMap<>();

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


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressBarfrigo.setVisibility(View.INVISIBLE);
            aggiorna();
        }
    }

    private void aggiorna (){

        alimenti.clear();
        adapterGrigliaAlimenti.notifyDataSetChanged();

        for (Alimento a : databaseAdapter.getAllAlimenti()){

            alimenti.add(a);
        }

        adapterGrigliaAlimenti.notifyDataSetChanged();
    }

}
