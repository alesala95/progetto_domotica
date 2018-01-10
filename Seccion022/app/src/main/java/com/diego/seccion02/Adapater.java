package com.diego.seccion02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class Adapater extends BaseAdapter {

    private Context context;
    private int layout;
    private List<String> names;

    public Adapater(Context context, int layout, List<String> names){
        this.context = context;
        this.layout = layout;
        this.names = names;
    }

    @Override
    //Le dice al adaptador cuantas veces va a interar
    public int getCount() {
        return this.names.size();
    }

    @Override
    //Es para obtener un item a la que le doy una posicion
    public Object getItem(int position) {
        return this.names.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    //Coje cada Itemy dibujamos lo que queremos
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        // Copiamos la vista
        // View v = convertView;
        ViewHolder holder;

        if (convertView == null) {
            //Inflamos la vista que nos ha llegado con nuestro layout personalizado
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            convertView = layoutInflater.inflate(this.layout, null);

        holder = new ViewHolder();
        //Referenciamos el elemento o modificar y lo rellenamos
        holder.nameTextView = (TextView) convertView.findViewById(R.id.textView);
        convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //Nos traemos el valor actual dependiente de la posicion
        String currentName = names.get(position);
        //currentName = (String) getItem(position);

        //Referenciamos el elemento a modificar y lo rellenamos
        holder.nameTextView.setText(currentName);

        //Devolvemos la vista inflada  y modificada con nuestros datos
        return convertView;
    }

    static class ViewHolder{
        private TextView nameTextView;
    }
}
