package com.example.itsadmin.smartfridge_fragment.Main.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Fragments.RecipeItemFragment;
import com.example.itsadmin.smartfridge_fragment.Models.Ricetta;
import com.example.itsadmin.smartfridge_fragment.R;
import com.example.itsadmin.smartfridge_fragment.Singleton.RetrofitService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by itsadmin on 12/02/2018.
 */

public class AdapterRicetteConsigliate extends RecyclerView.Adapter<AdapterRicetteConsigliate.AdapterRicetteConsigliateHolder> {

    ArrayList<Ricetta> lsr;
    Context context;
    FragmentManager FragManager;

    public AdapterRicetteConsigliate(ArrayList<Ricetta> lsr, Context context,android.support.v4.app.FragmentManager fragmentManager) {

        this.lsr = lsr;
        this.context=context;
        this.FragManager = fragmentManager;
    }

    @Override
    public AdapterRicetteConsigliate.AdapterRicetteConsigliateHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_list_ricette_consigliate,parent,false);

        return new AdapterRicetteConsigliate.AdapterRicetteConsigliateHolder(v);
    }


    @Override
    public void onBindViewHolder(AdapterRicetteConsigliate.AdapterRicetteConsigliateHolder holder, int position)  {

        holder.txt.setText(lsr.get(position).getNome());
        Picasso.get().load(RetrofitService.getInstance().getRetrofit().baseUrl()+""+lsr.get(position).getUrlImage()).into(holder.sfondo);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {

                Bundle b = new Bundle();
                b.putInt("id",lsr.get(position).getId());
                RecipeItemFragment recipeItemFragment=new RecipeItemFragment();
                recipeItemFragment.setArguments(b);
                android.support.v4.app.FragmentTransaction fragmentTransactionH=FragManager.beginTransaction();
                fragmentTransactionH.replace(R.id.fram,recipeItemFragment);
                fragmentTransactionH.addToBackStack(null);
                fragmentTransactionH.commit();
            }
        });
    }

    @Override
    public int getItemCount() {

        return lsr.size();
    }

    public class AdapterRicetteConsigliateHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        public TextView txt;
        public ImageView sfondo;

        private ItemClickListener itemClickListener;

        public AdapterRicetteConsigliateHolder(View itemView) {

            super(itemView);
            txt = itemView.findViewById(R.id.textP);
            sfondo = itemView.findViewById(R.id.sfondoCard);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View v) {

            itemClickListener.onClick(v,getAdapterPosition(),false);

        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);

            return true;
        }
    }
}
