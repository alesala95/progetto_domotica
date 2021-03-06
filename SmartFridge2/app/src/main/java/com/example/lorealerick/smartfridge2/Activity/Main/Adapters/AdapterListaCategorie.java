package com.example.lorealerick.smartfridge2.Activity.Main.Adapters;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Models.Categoria;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.RecyclerDivider;
import com.example.lorealerick.smartfridge2.Utils.UtilsTesto;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class AdapterListaCategorie extends ArrayAdapter <Categoria>{

    private Activity c;
    private ArrayList <Categoria> categorie;
    private ListenerApriRicetta listenerApriRicetta;

    public AdapterListaCategorie(Activity context, int resource, ArrayList <Categoria> categorie, ListenerApriRicetta listenerApriRicetta) {
        super(context, resource, categorie);

        this.c = context;
        this.categorie = categorie;
        this.listenerApriRicetta = listenerApriRicetta;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = c.getLayoutInflater();
            view = inflater.inflate(R.layout.item_anteprima_categorie, null);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.listaCategorie = view.findViewById(R.id.anteprimaCategoria);
            viewHolder.nomeCategoria = view.findViewById(R.id.nomeCategoria);
            viewHolder.nomeCategoria.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listenerApriRicetta.apriCategoriaRicetta(categorie.get(position).getNome());
                }
            });

            viewHolder.listaCategorie.setLayoutManager(new LinearLayoutManager(c,LinearLayoutManager.HORIZONTAL,false));
            viewHolder.listaCategorie.addItemDecoration(new RecyclerDivider(c,R.dimen.offset));

            view.setTag(viewHolder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        holder.nomeCategoria.setText(UtilsTesto.letteraMaiuscola(categorie.get(position).getNome()));
        holder.listaCategorie.setAdapter(new AdapterRicetta(c,categorie.get(position).getRicette(),listenerApriRicetta));


        return view;
    }

    static class ViewHolder{

        public TextView nomeCategoria;
        public RecyclerView listaCategorie;
    }
}
