package com.example.lorealerick.smartfridge2.Activity.Main.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.OnItemClickListener;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class AdapterRicetta extends RecyclerView.Adapter <AdapterRicetta.ViewHolder>{

    public ArrayList <Ricetta> ricette;
    private Context context;
    private ListenerApriRicetta listenerApriRicetta;

    public AdapterRicetta(Context context, ArrayList<Ricetta> ricette, ListenerApriRicetta listenerApriRicetta) {

        this.ricette = ricette;
        this.context = context;
        this.listenerApriRicetta = listenerApriRicetta;
    }

    @Override
    public AdapterRicetta.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_ricetta_alimento,parent,false);

        return new AdapterRicetta.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.nome.setText(ricette.get(position).getNome());
        holder.immagine.setImageBitmap(BitmapHandle.getBitmap(ricette.get(position).getImage()));

        holder.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                listenerApriRicetta.apriRicetta(ricette.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ricette.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nome;
        public ImageView immagine;

        private OnItemClickListener onItemClickListener;

        public ViewHolder(View itemView) {

            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            immagine = itemView.findViewById(R.id.immagine);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(OnItemClickListener itemClickListener){
            this.onItemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View view) {

            onItemClickListener.onItemClick(view,getAdapterPosition());
        }
    }
}
