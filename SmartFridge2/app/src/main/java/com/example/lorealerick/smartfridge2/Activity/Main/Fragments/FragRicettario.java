package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterListaCategorie;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Categoria;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.SmartFridgeAPI.RicetteAPI;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;
import com.example.lorealerick.smartfridge2.Utils.Services;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class FragRicettario extends Fragment{

    DatabaseAdapter dbAdapter;
    ArrayList <Categoria> categorie;
    AdapterListaCategorie adapterListaCategorie;
    DownloadRicetteManager downloadRicetteManager;
    ProgressBar progressBarRicettario;

    ListenerRefreshUI listenerRefreshUI;
    ListenerApriRicetta listenerApriRicetta;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dbAdapter = new DatabaseAdapter(context);
        listenerRefreshUI = (MainActivity)context;
        listenerRefreshUI.onRefreshUI("Ricettario",null);
        listenerApriRicetta = (MainActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricettario, container, false);

        categorie = new ArrayList<>();
        progressBarRicettario = view.findViewById(R.id.progressRicettario);

        if(!Services.getInstance().isScaricatoAnteprimaRicette()){

            dbAdapter.svuotaTabellaRicette();

            downloadRicetteManager = new DownloadRicetteManager();
            downloadRicetteManager.execute();

            Services.getInstance().setScaricatoAnteprimaRicette(true);
        }else{

            aggiornaDati();
        }

        adapterListaCategorie = new AdapterListaCategorie(getActivity(),R.layout.item_anteprima_categorie,categorie,listenerApriRicetta);
        ListView listaCategorie = view.findViewById(R.id.listaCategorie);
        listaCategorie.setAdapter(adapterListaCategorie);

        return view;
    }


    private class DownloadRicetteManager extends AsyncTask <Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBarRicettario.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            new DownloadDati(getActivity()).scaricaVetrinaRicette();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            aggiornaDati();
            progressBarRicettario.setVisibility(View.INVISIBLE);
        }
    }

    private void aggiornaDati (){

        categorie.clear();
        adapterListaCategorie.notifyDataSetChanged();

        for (String s : dbAdapter.getAllCategorie()){

            categorie.add(new Categoria(s,dbAdapter.getAllRicetteForCategoria(s,5)));
        }

        adapterListaCategorie.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("Ricettario",null);
    }
}
