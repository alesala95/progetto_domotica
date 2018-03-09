package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.R;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragRicetta extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricetta, container, false);

        TextView nomeRicetta = view.findViewById(R.id.titoloRicetta);

        nomeRicetta.setText(getArguments().getInt("id")+"");

        return view;
    }
}
