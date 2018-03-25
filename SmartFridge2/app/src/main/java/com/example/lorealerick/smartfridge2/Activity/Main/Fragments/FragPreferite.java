package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterGrigliaRicette;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 25/03/2018.
 */

public class FragPreferite extends Fragment {

    private ArrayList <Ricetta> ricette;
    private ListenerApriRicetta listenerApriRicetta;
    private AdapterGrigliaRicette adapterGrigliaRicette;
    private ListenerRefreshUI listenerRefreshUI;
    private ProgressBar progressBarPreferite;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listenerApriRicetta = (MainActivity) context;
        listenerRefreshUI = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricette_preferite,container,false);

        listenerRefreshUI.onRefreshUI("Preferite",null);

        ricette = new ArrayList<>();
        adapterGrigliaRicette = new AdapterGrigliaRicette(getActivity(),R.layout.item_ricetta,ricette);
        progressBarPreferite = view.findViewById(R.id.progressPreferite);

        GridView grigliaRicettePreferite = view.findViewById(R.id.grigliaRicettePreferite);
        grigliaRicettePreferite.setAdapter(adapterGrigliaRicette);
        grigliaRicettePreferite.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                listenerApriRicetta.apriRicetta(ricette.get(i).getId());
            }
        });

        new TaskRicettePreferite().execute();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("Preferite",null);
    }

    private class TaskRicettePreferite extends AsyncTask <Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            ricette.clear();
            adapterGrigliaRicette.notifyDataSetChanged();
            progressBarPreferite.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ricette.addAll(DownloadDati.scaricaRicettePreferite());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            adapterGrigliaRicette.notifyDataSetChanged();
            progressBarPreferite.setVisibility(View.GONE);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            progressBarPreferite.setVisibility(View.GONE);
        }
    }
}
