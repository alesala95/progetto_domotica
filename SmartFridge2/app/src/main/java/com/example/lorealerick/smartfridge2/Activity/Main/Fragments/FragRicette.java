package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterListaCategorie;
import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerEventi;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.OnItemClickListener;
import com.example.lorealerick.smartfridge2.Models.Categoria;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class FragRicette extends Fragment implements ListenerEventi{


    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricette, container, false);

        ArrayList <Categoria> categorie = new ArrayList<>();
        ArrayList <Ricetta> ricette = new ArrayList<>();

        ricette.add(new Ricetta(12,"Pasta",1,null,null,null,null,null));
        ricette.add(new Ricetta(12,"Pasta",1,null,null,null,null,null));
        ricette.add(new Ricetta(12,"Pasta",1,null,null,null,null,null));
        ricette.add(new Ricetta(12,"Pasta",1,null,null,null,null,null));
        categorie.add(new Categoria("Primi",ricette));
        categorie.add(new Categoria("Secondi",ricette));
        categorie.add(new Categoria("Terzi",ricette));
        categorie.add(new Categoria("Quarti",ricette));


        AdapterListaCategorie adapterListaCategorie = new AdapterListaCategorie(getActivity(),R.layout.item_anteprima_categorie,categorie,this);
        ListView listaCategorie = view.findViewById(R.id.listaCategorie);
        listaCategorie.setAdapter(adapterListaCategorie);


        return view;
    }

    @Override
    public void selezionaRicetta(int idRicetta) {

        Toast.makeText(getActivity(),idRicetta+"",Toast.LENGTH_SHORT).show();
    }
}
