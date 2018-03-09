package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterListaCategorie;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerEventi;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.OnItemClickListener;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Categoria;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class FragRicettario extends Fragment implements ListenerApriRicetta{

    DatabaseAdapter dbAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dbAdapter = new DatabaseAdapter(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricettario, container, false);

        ArrayList <Categoria> categorie = new ArrayList<>();
        ArrayList <Ricetta> ricette = new ArrayList<>();

        ricette = dbAdapter.getAllRicette();

        categorie.add(new Categoria("Primi",dbAdapter.getAllRicetteForCategoria("primo")));
        categorie.add(new Categoria("Secondi",dbAdapter.getAllRicetteForCategoria("secondo")));
        categorie.add(new Categoria("Terzi",dbAdapter.getAllRicetteForCategoria("terzo")));
        categorie.add(new Categoria("Quarti",dbAdapter.getAllRicetteForCategoria("quarto")));

        AdapterListaCategorie adapterListaCategorie = new AdapterListaCategorie(getActivity(),R.layout.item_anteprima_categorie,categorie,this);
        ListView listaCategorie = view.findViewById(R.id.listaCategorie);
        listaCategorie.setAdapter(adapterListaCategorie);

        return view;
    }

    @Override
    public void apriRicetta(int idRicetta) {

        FragRicetta fragRicetta = new FragRicetta();

        Bundle bundle = new Bundle();
        bundle.putInt("id",idRicetta);

        fragRicetta.setArguments(bundle);

        cambiaRicetta(fragRicetta);
    }

    private void cambiaRicetta (FragRicetta fragRicetta){

        getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.contenitore,fragRicetta).commit();
    }
}
