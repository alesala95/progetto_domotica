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
import com.example.lorealerick.smartfridge2.R;
;
import java.util.ArrayList;


/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class FragRicettario extends Fragment{

    private DatabaseAdapter dbAdapter;
    private ArrayList <Categoria> categorie;
    private AdapterListaCategorie adapterListaCategorie;
    private DownloadRicetteManager downloadRicetteManager;
    private ProgressBar progressBarRicettario;

    private ListenerRefreshUI listenerRefreshUI;
    private ListenerApriRicetta listenerApriRicetta;

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

        adapterListaCategorie = new AdapterListaCategorie(getActivity(),R.layout.item_anteprima_categorie,categorie,listenerApriRicetta);
        ListView listaCategorie = view.findViewById(R.id.listaCategorie);
        listaCategorie.setAdapter(adapterListaCategorie);

        new DownloadRicetteManager().execute();

        return view;
    }


    private class DownloadRicetteManager extends AsyncTask <Void, Void, Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            categorie.clear();
            adapterListaCategorie.notifyDataSetChanged();
            progressBarRicettario.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for (String s : dbAdapter.getAllCategorie())

                categorie.add(new Categoria(s,dbAdapter.getAllRicetteForCategoria(s,5)));


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

        adapterListaCategorie.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("Ricettario",null);
    }
}
