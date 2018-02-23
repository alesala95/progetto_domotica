package com.example.itsadmin.smartfridge_fragment.Main.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itsadmin.smartfridge_fragment.Main.Fragments.RecipeItemFragment;
import com.example.itsadmin.smartfridge_fragment.Main.Items.ItemListRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.R;

import java.util.ArrayList;

/**
 * Created by itsadmin on 12/02/2018.
 */

public class AdapterRicetteConsigliate extends RecyclerView.Adapter<AdapterRicetteConsigliate.AdapterRicetteConsigliateHolder> {

    ArrayList<ItemListRicetteConsigliate> ls;
    Context context;
    FragmentManager FragManager;

    public AdapterRicetteConsigliate(ArrayList<ItemListRicetteConsigliate> ls, Context context, android.support.v4.app.FragmentManager fragmentManager) {

        this.ls = ls;
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

        holder.txt.setText(ls.get(position).getTesto());
        holder.foodIcon.setBackgroundResource(ls.get(position).getImg());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if(isLongClick){
                    Toast.makeText(context,"long click"+ls.get(position).getTesto(),Toast.LENGTH_LONG).show();
                }else {


                    RecipeItemFragment recipeItemFragment=new RecipeItemFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransactionH=FragManager.beginTransaction();
                    fragmentTransactionH.replace(R.id.fram,recipeItemFragment,"RecipeItemFragment");
                    fragmentTransactionH.addToBackStack(null);
                    fragmentTransactionH.commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class AdapterRicetteConsigliateHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        public TextView txt;
        public CardView foodIcon;

        private ItemClickListener itemClickListener;

        public AdapterRicetteConsigliateHolder(View itemView) {

            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.textP);
            foodIcon=(CardView) itemView.findViewById(R.id.card);

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
