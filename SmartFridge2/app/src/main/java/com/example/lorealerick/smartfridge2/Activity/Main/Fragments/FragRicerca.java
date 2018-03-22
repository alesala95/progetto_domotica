package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterGrigliaRicette;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 22/03/2018.
 */

public class FragRicerca extends Fragment {

    private ArrayList <Ricetta> ricette;
    private AdapterGrigliaRicette adapterGrigliaRicette;
    private SearchView ricerca;
    private ListenerRefreshUI listenerRefreshUI;
    private ProgressBar progressBarRicerca;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listenerRefreshUI = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricerca, container, false);

        listenerRefreshUI.onRefreshUI("Ricerca",null);
        progressBarRicerca = view.findViewById(R.id.progressRicerca);
        ricette = new ArrayList<>();
        GridView grigliaRicerca = view.findViewById(R.id.grigliaRicerca);
        adapterGrigliaRicette = new AdapterGrigliaRicette(getActivity(),R.layout.item_ricetta,ricette);

        grigliaRicerca.setAdapter(adapterGrigliaRicette);

        ricerca = view.findViewById(R.id.search_view);
        ricerca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                new ricercaManager().execute(s.toLowerCase());

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return view;
    }

    private class ricercaManager extends AsyncTask <String, Void, Void> {

        @Override
        protected void onPreExecute() {
            ricette.clear();
            adapterGrigliaRicette.notifyDataSetChanged();
            progressBarRicerca.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {

            ricette.addAll(DownloadDati.scaricaRicercaRicette(strings[0]));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBarRicerca.setVisibility(View.GONE);
            adapterGrigliaRicette.notifyDataSetChanged();
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("Ricerca",null);
    }
}
