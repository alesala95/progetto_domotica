package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterGrigliaAlimenti;
import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.AlimentiAPI;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;
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

public class FragFrigo extends Fragment{

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

        progressBarfrigo = view.findViewById(R.id.progressFrigo);

        alimenti = new ArrayList<>();

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

            new DownloadDati(getActivity()).scaricaAlimenti();

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
