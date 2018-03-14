package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import com.example.lorealerick.smartfridge2.Utils.Services;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragHome extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    DatabaseAdapter databaseAdapter;
    DownloadDati downloadDati;

    ArrayList <Alimento> listaAlimentiInScadenza;
    ArrayList <Ricetta> listaRicetteConsigliate;

    AdapterAlimentoScadenza adapterAlimentiInScadenza;
    AdapterRicetta adapterRicetteConsigliate;

    SwipeRefreshLayout swipeRefreshLayout;

    private Refresh refresh;

    ListenerRefreshUI listenerRefreshUI;
    ListenerApriRicetta listenerApriRicetta;

    Frigo frigo;

    TextView statusfrigo;
    TextView tempFrigo;
    TextView statusFreezer;
    TextView tempFreezer;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        databaseAdapter = new DatabaseAdapter(context);
        downloadDati = new DownloadDati(context);
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

        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

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

        Services.getInstance().setScaricatoAlimenti(true);


        return view;
    }

    @Override
    public void onRefresh() {

        launchRefresh();
    }

    private void launchRefresh (){

        if(refresh == null){

            refresh = new Refresh();
        }

        if (refresh.getStatus() != AsyncTask.Status.RUNNING){

            refresh = new Refresh();
            refresh.execute();
        }
    }

    class Refresh extends AsyncTask <Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            clearDataSets();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            listaAlimentiInScadenza.addAll(databaseAdapter.getAllAlimentiInScadenza());
            listaRicetteConsigliate.addAll(downloadDati.scaricaFeedRicetteConsigliate(listaAlimentiInScadenza));

            frigo = downloadDati.scaricaInfoFrigo();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            swipeRefreshLayout.setRefreshing(false);
            notifyDataChanged();
            refreshRealTimeMonitoring();
        }
    }

    private void clearDataSets (){

        listaAlimentiInScadenza.clear();
        listaRicetteConsigliate.clear();
        notifyDataChanged();
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
