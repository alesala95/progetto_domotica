package com.example.lorealerick.smartfridge2.Activity.Settings.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lorealerick.smartfridge2.Models.SettingsItem;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Activity.Settings.Adapters.AdapterListaSettingHome;
import com.example.lorealerick.smartfridge2.Activity.Settings.Impostazioni;
import com.example.lorealerick.smartfridge2.Activity.Settings.Interfaces.ListenerImpostazioni;

import java.util.ArrayList;

/**
 * Created by itsadmin on 13/03/2018.
 */

public class FragSettingsHome extends Fragment {

    ListenerImpostazioni listenerImpostazioni;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listenerImpostazioni = (Impostazioni)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_settings_home, container, false);

        ArrayList <SettingsItem> settingsItems = new ArrayList<>();

        settingsItems.add(new SettingsItem("Account",R.mipmap.account));
        settingsItems.add(new SettingsItem("Frigo",R.mipmap.frigo));
        settingsItems.add(new SettingsItem("Domande",R.mipmap.ask));
        settingsItems.add(new SettingsItem("Info",R.mipmap.info));

        final ListView listaImpostazioni = view.findViewById(R.id.listaImpostazioni);
        AdapterListaSettingHome adapterListaSettingHome = new AdapterListaSettingHome(getActivity(),R.layout.item_settings_home,settingsItems);

        listaImpostazioni.setAdapter(adapterListaSettingHome);

        listaImpostazioni.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                listenerImpostazioni.onImpostazioneSelezionata(position);
            }
        });

        return view;
    }
}
