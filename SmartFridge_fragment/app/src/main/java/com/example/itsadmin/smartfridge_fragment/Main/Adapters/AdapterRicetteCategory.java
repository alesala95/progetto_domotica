package com.example.itsadmin.smartfridge_fragment.Main.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Items.ItemListRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.R;

import java.util.ArrayList;

/**
 * Created by itsadmin on 23/02/2018.
 */

public class AdapterRicetteCategory extends ArrayAdapter <ItemListRicetteConsigliate> {

    Context context;
    ArrayList <ItemListRicetteConsigliate> lista;

    public AdapterRicetteCategory(Context context, ArrayList <ItemListRicetteConsigliate> lista) {
        super(context, R.layout.item_list_ricette_consigliate, lista);

        this.lista = lista;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            view = inflater.inflate(R.layout.item_list_ricette_consigliate, parent, false);

            ViewHolder holder = new ViewHolder();

            holder.txt = view.findViewById(R.id.textP);
            holder.foodIcon = view.findViewById(R.id.card);

            view.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        holder.txt.setText(lista.get(position).getTesto());
        holder.foodIcon.setBackgroundResource(lista.get(position).getImg());

       return view;
    }

    private static class ViewHolder{

        TextView txt;
        CardView foodIcon;
    }

}
