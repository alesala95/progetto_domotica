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
import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;

import java.util.ArrayList;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class AdapterAlimentoScadenza extends RecyclerView.Adapter <AdapterAlimentoScadenza.ViewHolder>{

    public ArrayList <Alimento> alimenti;
    private Context context;

    public AdapterAlimentoScadenza(Context context, ArrayList<Alimento> alimenti) {

        this.alimenti = alimenti;
        this.context = context;
    }

    @Override
    public AdapterAlimentoScadenza.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_alimento,parent,false);

        return new AdapterAlimentoScadenza.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.nome.setText(alimenti.get(position).getNome());
        holder.stato.setText(alimenti.get(position).getStatoString());

        int aux = alimenti.get(position).giorniScaduto();

        if(aux != -1){

            if(aux == 1)
                holder.stato.append(" da un giorno");
            else
                holder.stato.append(" da "+alimenti.get(position).giorniScaduto()+" giorni");
        }

        holder.dataScadenza.setText(alimenti.get(position).getDataScadenza());
        holder.immagine.setImageBitmap(BitmapHandle.getBitmap(alimenti.get(position).getImage()));
    }

    @Override
    public int getItemCount() {
        return alimenti.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nome;
        public TextView stato;
        public TextView dataScadenza;
        public ImageView immagine;

        public ViewHolder(View itemView) {

            super(itemView);

            nome = itemView.findViewById(R.id.nome);
            stato = itemView.findViewById(R.id.quantita);
            dataScadenza= itemView.findViewById(R.id.dataScadenza);
            immagine = itemView.findViewById(R.id.immagine);

        }
    }
}
