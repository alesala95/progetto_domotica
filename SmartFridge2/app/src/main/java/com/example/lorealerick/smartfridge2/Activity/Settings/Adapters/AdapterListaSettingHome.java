package com.example.lorealerick.smartfridge2.Activity.Settings.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Models.SettingsItem;
import com.example.lorealerick.smartfridge2.R;

import java.util.ArrayList;

/**
 * Created by itsadmin on 13/03/2018.
 */

public class AdapterListaSettingHome extends ArrayAdapter <SettingsItem> {

    Activity context;
    ArrayList <SettingsItem> settingsItems;

    public AdapterListaSettingHome(Activity context, int resource, ArrayList <SettingsItem> settingsItems) {
        super(context, resource, settingsItems);

        this.context = context;
        this.settingsItems = settingsItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null){

            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.item_settings_home, null);

            AdapterListaSettingHome.ViewHolder viewHolder = new AdapterListaSettingHome.ViewHolder();

            viewHolder.descrizione = view.findViewById(R.id.descrizione);
            viewHolder.icona = view.findViewById(R.id.icona);

            view.setTag(viewHolder);
        }

        AdapterListaSettingHome.ViewHolder holder = (AdapterListaSettingHome.ViewHolder) view.getTag();

        holder.descrizione.setText(settingsItems.get(position).getDescrizione());
        holder.icona.setBackgroundResource(settingsItems.get(position).getIcon());

        return view;
    }

    static class ViewHolder{

        public TextView descrizione;
        public ImageView icona;
    }
}
