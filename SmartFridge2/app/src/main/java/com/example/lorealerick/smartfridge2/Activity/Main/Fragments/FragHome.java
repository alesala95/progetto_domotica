package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterAlimentoScadenza;
import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Frigo;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;
import com.example.lorealerick.smartfridge2.Utils.RecyclerDivider;

import java.util.ArrayList;

import io.rmiri.skeleton.SkeletonGroup;
import io.rmiri.skeleton.SkeletonView;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragHome extends Fragment{

    private DatabaseAdapter databaseAdapter;

    private ArrayList <Alimento> listaAlimentiInScadenza;
    private ArrayList <Ricetta> listaRicetteConsigliate;

    private AdapterAlimentoScadenza adapterAlimentiInScadenza;
    private AdapterRicetta adapterRicetteConsigliate;

    private Button refreshRtm;

    private Refresh refresh;

    private ListenerRefreshUI listenerRefreshUI;
    private ListenerApriRicetta listenerApriRicetta;

    private Frigo frigo;

    private TextView statusfrigo;
    private TextView tempFrigo;
    private TextView statusFreezer;
    private TextView tempFreezer;
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        databaseAdapter = new DatabaseAdapter(context);
        listenerRefreshUI = (MainActivity)context;
        listenerRefreshUI.onRefreshUI("Home",null);
        listenerApriRicetta = (MainActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_home, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        statusfrigo = view.findViewById(R.id.statusFrigo);
        statusFreezer = view.findViewById(R.id.statusFreezer);
        tempFrigo = view.findViewById(R.id.tempFrigo);
        tempFreezer = view.findViewById(R.id.tempFreezer);

        refreshRtm = view.findViewById(R.id.refreshRTM);
        refreshRtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadRTM();
                refreshRealTimeMonitoring();

            }
        });

        RecyclerView alimentiInScadenza = view.findViewById(R.id.alimentiInScadenza);
        RecyclerView ricetteConsigliate = view.findViewById(R.id.ricetteConsigliate);

        alimentiInScadenza.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        alimentiInScadenza.addItemDecoration(new RecyclerDivider(getActivity(),R.dimen.offset));
        ricetteConsigliate.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        ricetteConsigliate.addItemDecoration(new RecyclerDivider(getActivity(),R.dimen.offset));

        listaAlimentiInScadenza = new ArrayList<>();
        listaRicetteConsigliate = new ArrayList<>();

        adapterAlimentiInScadenza = new AdapterAlimentoScadenza(getActivity(),listaAlimentiInScadenza);
        adapterRicetteConsigliate = new AdapterRicetta(getActivity(),listaRicetteConsigliate,listenerApriRicetta);

        ricetteConsigliate.setAdapter(adapterRicetteConsigliate);
        alimentiInScadenza.setAdapter(adapterAlimentiInScadenza);

        launchRefresh();

        return view;
    }

    private void launchRefresh (){

        if(refresh == null){

            refresh = new Refresh();
            refresh.execute();
        }

        if (refresh.getStatus() != AsyncTask.Status.RUNNING){

            refresh = new Refresh();
            refresh.execute();
        }
    }

    private class Refresh extends AsyncTask <Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            clearDataSets();
            notifyDataChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            listaAlimentiInScadenza.addAll(databaseAdapter.getAllAlimentiInScadenza());
            listaAlimentiInScadenza.addAll(databaseAdapter.getAllAlimentiScadonoOggi());

            ArrayList <Alimento> feedRicetteConsigliate = databaseAdapter.getAlimentiScadenzaEBuoni(true);
            for(int i = 6;i < feedRicetteConsigliate.size();i++){

                feedRicetteConsigliate.remove(i);
            }

            listaRicetteConsigliate.addAll(DownloadDati.scaricaFeedRicetteConsigliate(feedRicetteConsigliate));
            downloadRTM();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            notifyDataChanged();
            refreshRealTimeMonitoring();
        }
    }

    private void downloadRTM (){

        frigo = DownloadDati.scaricaInfoFrigo();
        databaseAdapter.svuotaTabellaFrigo();
        databaseAdapter.addFrigo(frigo);
    }

    private void clearDataSets (){

        listaAlimentiInScadenza.clear();
        listaRicetteConsigliate.clear();
    }

    private void notifyDataChanged (){

        adapterAlimentiInScadenza.notifyDataSetChanged();
        adapterRicetteConsigliate.notifyDataSetChanged();
    }

    private void refreshRealTimeMonitoring (){

        System.out.println(frigo.toString());

        if(frigo.getFrigoAcceso())

            statusfrigo.setText("Il frigo è acceso");
        else
            statusfrigo.setText("Il frigo è spento");

        if(frigo.getFreezerAcceso())

            statusFreezer.setText("Il freezer è acceso");
        else
            statusFreezer.setText("Il freezer è spento");

        tempFrigo.setText("Temperatura: "+frigo.getFrigoTemp()+"°");
        tempFreezer.setText("Temperatura: "+frigo.getFreezerTemp()+"°");
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("Home",null);
    }
}
