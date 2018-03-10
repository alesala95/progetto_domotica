package com.example.lorealerick.smartfridge2.Activity.Main.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 10/03/2018.
 */

public class AdapterGrigliaAlimenti extends ArrayAdapter <Alimento> {

    ArrayList <Alimento> alimenti;
    Activity c;

    public AdapterGrigliaAlimenti(Activity context, int resource, ArrayList<Alimento> alimenti) {
        super(context, resource, alimenti);

        this.c = context;
        this.alimenti = alimenti;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = c.getLayoutInflater();
            view = inflater.inflate(R.layout.item_ricetta_alimento, null);

            AdapterGrigliaAlimenti.ViewHolder viewHolder = new AdapterGrigliaAlimenti.ViewHolder();

            viewHolder.nomeAlimento = view.findViewById(R.id.nome);
            viewHolder.immagine = view.findViewById(R.id.immagine);

            view.setTag(viewHolder);
        }

        AdapterGrigliaAlimenti.ViewHolder holder = (AdapterGrigliaAlimenti.ViewHolder) view.getTag();

        holder.nomeAlimento.setText(alimenti.get(position).getNome());
        holder.immagine.setImageBitmap(BitmapHandle.getBitmap(alimenti.get(position).getImage()));

        return view;
    }

    static class ViewHolder{

        public TextView nomeAlimento;
        public ImageView immagine;
    }
}
