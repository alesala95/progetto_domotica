package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterGrigliaAlimenti;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;

import java.util.ArrayList;


/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragFrigo extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private DatabaseAdapter databaseAdapter;
    private ArrayList <Alimento> alimenti;
    private AdapterGrigliaAlimenti adapterGrigliaAlimenti;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ListenerRefreshUI listenerRefreshUI;
    private DownloadAlimentiManager downloadAlimentiManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        databaseAdapter = new DatabaseAdapter(context);
        listenerRefreshUI = (MainActivity)context;
        listenerRefreshUI.onRefreshUI("Frigo",null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_frigo, container, false);

        swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        alimenti = new ArrayList<>();

        GridView grigliaAlimenti = view.findViewById(R.id.grigliaAlimenti);

        adapterGrigliaAlimenti = new AdapterGrigliaAlimenti(getActivity(),R.layout.item_alimento,alimenti);
        grigliaAlimenti.setAdapter(adapterGrigliaAlimenti);

        clearDataSet();
        aggiorna();
        notifyDataChanged();

        return view;
    }

    @Override
    public void onRefresh() {

        riscaricaAlimenti();
    }

    private void riscaricaAlimenti () {

        downloadAlimentiManager = new DownloadAlimentiManager();
        downloadAlimentiManager.execute();
    }

    private class DownloadAlimentiManager extends AsyncTask <Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            clearDataSet();
            notifyDataChanged();
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            databaseAdapter.svuotaTabellaAlimenti();

            for (Alimento a : DownloadDati.scaricaAlimenti())
                databaseAdapter.addAlimento(a);

            aggiorna();

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            notifyDataChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void aggiorna (){

        alimenti.addAll(databaseAdapter.getAllAlimenti());
    }

    private void clearDataSet (){

        alimenti.clear();
    }

    private void notifyDataChanged(){

        adapterGrigliaAlimenti.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("Frigo",null);
    }
}
