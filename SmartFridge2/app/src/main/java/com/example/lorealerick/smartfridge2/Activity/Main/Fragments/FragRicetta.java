package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragRicetta extends Fragment {

    DatabaseAdapter dbAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dbAdapter = new DatabaseAdapter(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricetta, container, false);

        Ricetta ricetta = dbAdapter.getRicetta(getArguments().getInt("id"));

        if(ricetta != null){

            TextView nomeRicetta = view.findViewById(R.id.titoloRicetta);
            TextView autoreRicetta = view.findViewById(R.id.autoreRicetta);
            TextView durataRicetta = view.findViewById(R.id.durataRicetta);
            TextView difficoltaRicetta = view.findViewById(R.id.difficoltaRicetta);
            TextView ingredienti = view.findViewById(R.id.testoIngredienti);
            TextView procedimento = view.findViewById(R.id.testoProcedimento);

            ImageView immagine = view.findViewById(R.id.immagine);

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
        }


        return view;
    }
}
