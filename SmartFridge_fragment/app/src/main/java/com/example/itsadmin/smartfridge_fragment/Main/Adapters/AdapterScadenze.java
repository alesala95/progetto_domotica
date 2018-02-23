package com.example.itsadmin.smartfridge_fragment.Main.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.itsadmin.smartfridge_fragment.Main.Items.ItemListaScadenze;
import com.example.itsadmin.smartfridge_fragment.R;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 07/02/2018.
 */

public class AdapterScadenze extends RecyclerView.Adapter<AdapterScadenze.AdapterScadenzeHolder>{

    ArrayList<ItemListaScadenze> ls;

    public AdapterScadenze(ArrayList<ItemListaScadenze> ls) {

        this.ls = ls;
    }

    @Override
    public AdapterScadenzeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_lista_scadenze,parent,false);



        return new AdapterScadenzeHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterScadenzeHolder holder, int position) {

        holder.txt.setText(ls.get(position).getTesto());
        holder.foodIcon.setImageResource(ls.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class AdapterScadenzeHolder extends RecyclerView.ViewHolder {

        TextView txt;
        ImageView foodIcon;

        public AdapterScadenzeHolder(View itemView) {

            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.textP);
            foodIcon=(ImageView) itemView.findViewById(R.id.img);
        }

    }
}
