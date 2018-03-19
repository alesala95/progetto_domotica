package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterListaCategorie;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Categoria;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.UtilsAnimation;
;
import java.util.ArrayList;


/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class FragRicettario extends Fragment implements View.OnClickListener {

    private DatabaseAdapter dbAdapter;
    private ArrayList <Categoria> categorie;
    private AdapterListaCategorie adapterListaCategorie;
    private DownloadRicetteManager downloadRicetteManager;
    private ProgressBar progressBarRicettario;

    private ListenerRefreshUI listenerRefreshUI;
    private ListenerApriRicetta listenerApriRicetta;

    FloatingActionButton Floatingbtn;
    FloatingActionButton FABsearch;
    FloatingActionButton FABadd;
    FloatingActionButton FABfavourite;
    EditText editTextSearch;

    final Animation fadein = new AlphaAnimation(0.0f, 1.0f);
    final Animation fadeout = new AlphaAnimation(1.0f, 0.0f);

    boolean flag=false;

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

        Floatingbtn = view.findViewById(R.id.FloatingBtn);//FBA principale
        FABsearch = view.findViewById(R.id.FBAsearch);//FBA per la ricerca
        FABadd = view.findViewById(R.id.FBAadd);//FBA per la creazione di una ricetta
        FABfavourite = view.findViewById(R.id.FBAfavourite);//FBA per visualizzare le ricette preferite
        editTextSearch = view.findViewById(R.id.editTextSearch);

        fadein.setDuration(650);
        fadeout.setDuration(500);

        Floatingbtn.setOnClickListener(this);
        FABsearch.setOnClickListener(this);
        FABadd.setOnClickListener(this);

        new DownloadRicetteManager().execute();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.FloatingBtn:
                if(!flag)
                    FABshow();
                else
                    FABhide();
                break;

            case R.id.FBAsearch:
                editTextSearch.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextSearch , InputMethodManager.SHOW_IMPLICIT);
                FABhide();
                break;

            case R.id.FBAadd:

                break;
        }
    }

    private void FABhide() {
        FABsearch.startAnimation(fadeout);
        FABsearch.setVisibility(View.GONE);


        FABadd.startAnimation(fadeout);
        FABadd.setVisibility(View.GONE);

        FABfavourite.startAnimation(fadeout);
        FABfavourite.setVisibility(View.GONE);

        Floatingbtn.setImageResource(R.mipmap.fab);

        flag=false;
    }

    private void FABshow() {
        Floatingbtn.setImageResource(R.mipmap.close);

        FABsearch.startAnimation(fadein);
        FABsearch.setVisibility(View.VISIBLE);

        FABadd.startAnimation(fadein);
        FABadd.setVisibility(View.VISIBLE);

        FABfavourite.startAnimation(fadein);
        FABfavourite.setVisibility(View.VISIBLE);

        flag=true;
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
