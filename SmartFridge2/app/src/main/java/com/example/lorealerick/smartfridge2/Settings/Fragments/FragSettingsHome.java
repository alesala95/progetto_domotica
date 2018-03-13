package com.example.lorealerick.smartfridge2.Settings.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.lorealerick.smartfridge2.Models.SettingsItem;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Settings.AdapterListaSettingHome;

import java.util.ArrayList;

/**
 * Created by itsadmin on 13/03/2018.
 */

public class FragSettingsHome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_settings_home, container, false);

        ArrayList <SettingsItem> settingsItems = new ArrayList<>();

        settingsItems.add(new SettingsItem("Account",R.drawable.home));
        settingsItems.add(new SettingsItem("Frigo",R.drawable.frigo));
        settingsItems.add(new SettingsItem("Domande",R.drawable.home));
        settingsItems.add(new SettingsItem("Info",R.drawable.frigo));

        ListView listaImpostazioni = view.findViewById(R.id.listaImpostazioni);
        AdapterListaSettingHome adapterListaSettingHome = new AdapterListaSettingHome(getActivity(),R.layout.item_settings_home,settingsItems);

        listaImpostazioni.setAdapter(adapterListaSettingHome);

        return view;
    }
}
