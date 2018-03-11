package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterAlimentoScadenza;
import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;
import com.example.lorealerick.smartfridge2.Utils.RecyclerDivider;
import com.example.lorealerick.smartfridge2.Utils.Services;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragHome extends Fragment implements ListenerApriRicetta{

    DatabaseAdapter databaseAdapter;
    DownloadAlimentiManager downloadAlimentiManager;
    DownloadDati downloadDati;

    ArrayList <Alimento> listaAlimentiInScadenza;
    ArrayList <Ricetta> listaRicetteConsigliate;

    AdapterAlimentoScadenza adapterAlimentiInScadenza;
    AdapterRicetta adapterRicetteConsigliate;

    ProgressBar progressBarAlimentiInScadenza;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        databaseAdapter = new DatabaseAdapter(context);
        downloadDati = new DownloadDati(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_home, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        progressBarAlimentiInScadenza = view.findViewById(R.id.progressBarAlimentiInScadenza);

        RecyclerView alimentiInScadenza = view.findViewById(R.id.alimentiInScadenza);
        RecyclerView ricetteConsigliate = view.findViewById(R.id.ricetteConsigliate);

        alimentiInScadenza.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        alimentiInScadenza.addItemDecoration(new RecyclerDivider(getActivity(),R.dimen.offset));
        ricetteConsigliate.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        ricetteConsigliate.addItemDecoration(new RecyclerDivider(getActivity(),R.dimen.offset));

        listaAlimentiInScadenza = new ArrayList<>();
        listaRicetteConsigliate = new ArrayList<>();

        adapterAlimentiInScadenza = new AdapterAlimentoScadenza(getActivity(),listaAlimentiInScadenza);
        adapterRicetteConsigliate = new AdapterRicetta(getActivity(),listaRicetteConsigliate,this);

        ricetteConsigliate.setAdapter(adapterRicetteConsigliate);
        alimentiInScadenza.setAdapter(adapterAlimentiInScadenza);

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

    private class DownloadAlimentiManager extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBarAlimentiInScadenza.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            downloadDati.scaricaAlimenti();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressBarAlimentiInScadenza.setVisibility(View.INVISIBLE);
            aggiorna();
        }
    }

    private class DownloadRicetteConsigliateManager extends AsyncTask <ArrayList<Alimento>, Void, Void> {

        ArrayList <Ricetta> ricetteConsigliate;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBarAlimentiInScadenza.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(ArrayList<Alimento>... alims) {

            ricetteConsigliate = downloadDati.scaricaFeedRicetteConsigliate(alims[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBarAlimentiInScadenza.setVisibility(View.INVISIBLE);
            feedRicetteConsigliate(ricetteConsigliate);
        }
    }

    private void aggiorna (){


        listaAlimentiInScadenza.clear();
        adapterAlimentiInScadenza.notifyDataSetChanged();

        for (Alimento a : databaseAdapter.getAllAlimenti()){

            if(a.isInScadenza()) {
                listaAlimentiInScadenza.add(a);
                System.out.println(a.toString()+" è in scadenza");
            }
        }

        new DownloadRicetteConsigliateManager().execute(listaAlimentiInScadenza);

        adapterAlimentiInScadenza.notifyDataSetChanged();
    }

    private void feedRicetteConsigliate (ArrayList<Ricetta>ricetteConsigliate){

        listaRicetteConsigliate.clear();
        adapterRicetteConsigliate.notifyDataSetChanged();

        for (Ricetta r : ricetteConsigliate){

            listaRicetteConsigliate.add(r);
        }

        adapterRicetteConsigliate.notifyDataSetChanged();
    }


    @Override
    public void apriRicetta(int idRicetta) {

        FragRicetta fragRicetta = new FragRicetta();

        Bundle bundle = new Bundle();
        bundle.putInt("id",idRicetta);

        fragRicetta.setArguments(bundle);

        cambiaRicetta(fragRicetta);
    }

    private void cambiaRicetta (FragRicetta fragRicetta){

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.contenitore,fragRicetta).commit();
    }



}
