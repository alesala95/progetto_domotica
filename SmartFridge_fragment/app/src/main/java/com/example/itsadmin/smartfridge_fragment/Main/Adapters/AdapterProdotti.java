package com.example.itsadmin.smartfridge_fragment.Main.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Items.ModelAlimentiScadenza;
import com.example.itsadmin.smartfridge_fragment.R;

import java.util.ArrayList;

/**
 * Created by itsadmin on 19/02/2018.
 */

public class AdapterProdotti extends RecyclerView.Adapter<AdapterProdotti.AdapterProdottiHolder> {

    ArrayList<ModelAlimentiScadenza> ls;

    public AdapterProdotti(ArrayList<ModelAlimentiScadenza> ls) {

        this.ls = ls;
    }

    @Override
    public AdapterProdotti.AdapterProdottiHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_lista_prodotto,parent,false);

        return new AdapterProdotti.AdapterProdottiHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterProdotti.AdapterProdottiHolder holder, int position) {

        holder.txt.setText(ls.get(position).getNome());
        holder.foodIcon.setImageResource(ls.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class AdapterProdottiHolder extends RecyclerView.ViewHolder {


        ImageView foodIcon;
        TextView txt;

        public AdapterProdottiHolder(View itemView) {

            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.nomeF);
            foodIcon=(ImageView) itemView.findViewById(R.id.imageF);
        }

    }
}
