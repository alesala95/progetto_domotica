package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.R;

import java.util.ArrayList;

/**
 * Created by itsadmin on 19/03/2018.
 */

public class FragCreaRicetta extends Fragment implements View.OnClickListener {

    private EditText durata;
    private EditText ingredienti;
    private EditText procedimento;
    private Spinner difficolta;
    private ImageView iconRicetta;
    private ArrayAdapter <String> stringArrayAdapter;
    private int PICK_IMAGE_REQUEST = 1;
    ListenerRefreshUI listenerRefreshUI;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listenerRefreshUI = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_aggiungi_ricetta,container,false);

        listenerRefreshUI.onRefreshUI("CreaRicetta",null);

        durata = view.findViewById(R.id.durata);
        ingredienti = view.findViewById(R.id.ingredienti);
        procedimento = view.findViewById(R.id.procedimento);
        difficolta = view.findViewById(R.id.difficolta);
        iconRicetta=view.findViewById(R.id.iconRicetta);

        ArrayList <String> difficulties = new ArrayList<>();
        difficulties.add("Facile");
        difficulties.add("Medio");
        difficulties.add("Difficile");

        stringArrayAdapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,difficulties);
        difficolta.setAdapter(stringArrayAdapter);

        iconRicetta.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("CreaRicetta",null);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    
}
