package com.example.lorealerick.smartfridge2.Activity.Main.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;

import java.util.ArrayList;

/**
 * Created by itsadmin on 12/03/2018.
 */

public class AdapterGrigliaRicette extends ArrayAdapter <Ricetta> {

    ArrayList <Ricetta> ricette;
    Activity c;

    public AdapterGrigliaRicette (Activity context, int resource, ArrayList <Ricetta> ricette) {
        super(context, resource, ricette);

        this.c = context;
        this.ricette = ricette;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = c.getLayoutInflater();
            view = inflater.inflate(R.layout.item_ricetta, null);

            AdapterGrigliaRicette.ViewHolder viewHolder = new AdapterGrigliaRicette.ViewHolder();

            viewHolder.nomeRicetta = view.findViewById(R.id.nome);
            viewHolder.immagine = view.findViewById(R.id.immagine);

            view.setTag(viewHolder);
        }

        AdapterGrigliaRicette.ViewHolder holder = (AdapterGrigliaRicette.ViewHolder) view.getTag();

        holder.nomeRicetta.setText(ricette.get(position).getNome());
        holder.immagine.setImageBitmap(BitmapHandle.getBitmap(ricette.get(position).getImage()));

        return view;
    }

    static class ViewHolder{

        public TextView nomeRicetta;
        public ImageView immagine;
    }
}
