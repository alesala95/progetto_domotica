package com.example.itsadmin.smartfridge_fragment.Main.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Items.ListItem;
import com.example.itsadmin.smartfridge_fragment.R;

import java.text.DateFormat;
import java.util.ArrayList;

/**
 * Created by itsadmin on 31/01/2018.
 */

public class AdapterList extends ArrayAdapter<ListItem> {

    Context ctx;
    ArrayList<ListItem> values;

    public AdapterList(Context ctx, ArrayList<ListItem> values) {

        super(ctx, R.layout.item_lista_prodotto, values);

        this.ctx = ctx;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;

        if(rowView==null){

            LayoutInflater inflater = ((Activity) ctx).getLayoutInflater();
            rowView = inflater.inflate(R.layout.item_lista_prodotto, parent, false);

            ViewHolder holder = new ViewHolder();

            holder.imageF = (ImageView) rowView.findViewById(R.id.imageF);
            holder.nomeF = (TextView) rowView.findViewById(R.id.nomeF);

            holder.nomeB = (TextView) rowView.findViewById(R.id.nomeB);
            holder.quantitaB = (TextView) rowView.findViewById(R.id.quantitaB);
            holder.scadenzaB = (TextView) rowView.findViewById(R.id.scadenzaB);
            holder.back = (RelativeLayout) rowView.findViewById(R.id.back);

            rowView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) rowView.getTag();

        holder.imageF.setImageResource(values.get(position).getImg());
        holder.nomeF.setText(values.get(position).getNome());

        holder.nomeB.setText(values.get(position).getNome());
        holder.quantitaB.setText("Quantita: " + values.get(position).getQuantita());
        holder.scadenzaB.setText("Scad: " + DateFormat.getDateInstance(DateFormat.SHORT).format(values.get(position).getScadenza()));
        holder.back.setVisibility(View.INVISIBLE);

        return rowView;
    }

    static class ViewHolder{

        TextView nomeB;
        TextView quantitaB;
        TextView scadenzaB;
        TextView nomeF;

        ImageView imageF;

        RelativeLayout back;
    }


}


