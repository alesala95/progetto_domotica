package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;
import com.example.lorealerick.smartfridge2.Utils.UtilsAnimation;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragRicetta extends Fragment {

    private DatabaseAdapter dbAdapter;
    private TextView nomeRicetta;
    private TextView autoreRicetta;
    private TextView durataRicetta;
    private TextView difficoltaRicetta;
    private TextView ingredienti;
    private TextView procedimento;
    private ImageView immagine;

    private ListenerRefreshUI listenerRefreshUI;

    private RelativeLayout content;
    private Ricetta ricetta;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dbAdapter = new DatabaseAdapter(context);
        listenerRefreshUI = (MainActivity)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricetta, container, false);

        content = view.findViewById(R.id.content);

        nomeRicetta = view.findViewById(R.id.titoloRicetta);
        autoreRicetta = view.findViewById(R.id.autoreRicetta);
        durataRicetta = view.findViewById(R.id.durataRicetta);
        difficoltaRicetta = view.findViewById(R.id.difficoltaRicetta);
        ingredienti = view.findViewById(R.id.testoIngredienti);
        procedimento = view.findViewById(R.id.testoProcedimento);

        immagine = view.findViewById(R.id.immagine);

        new DownloadDettagliRicetta().execute(getArguments().getInt("id"));

        return view;
    }

    private class DownloadDettagliRicetta extends AsyncTask <Integer,Void,Void>{



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            content.setVisibility(View.INVISIBLE);
            listenerRefreshUI.onRefreshUI("Ricetta","Caricamento ... ");
        }

        @Override
        protected Void doInBackground(Integer... ints) {

            ricetta = new DownloadDati(getActivity()).scaricaRicetta(ints[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            aggiorna(ricetta);

            content.setVisibility(View.VISIBLE);
            UtilsAnimation.startFadeInAnimation(content,getActivity());
            Toast.makeText(getActivity(),ricetta.getNome()+"",Toast.LENGTH_SHORT).show();
        }
    }

    private void aggiorna (Ricetta ricetta){



        nomeRicetta.setText(ricetta.getNome());
        autoreRicetta.setText(ricetta.getAutore());
        durataRicetta.setText("Durata: " + ricetta.getDurata());

        switch (ricetta.getDifficolta()){

            case 1:

                difficoltaRicetta.setText("Facile");
                break;

            case 2:

                difficoltaRicetta.setText("Media");
                break;

            case 3:

                difficoltaRicetta.setText("Difficile");
                break;
        }

        ingredienti.setText(ricetta.getIngredienti());
        procedimento.setText(ricetta.getProcedimento());
        immagine.setImageBitmap(BitmapHandle.getBitmap(ricetta.getImage()));
        listenerRefreshUI.onRefreshUI("Ricetta",ricetta.getNome());
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("Ricetta","");
    }


}
