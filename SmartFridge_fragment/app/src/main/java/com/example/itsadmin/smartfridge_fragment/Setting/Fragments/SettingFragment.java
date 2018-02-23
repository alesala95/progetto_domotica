package com.example.itsadmin.smartfridge_fragment.Setting.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import com.example.itsadmin.smartfridge_fragment.R;
import com.example.itsadmin.smartfridge_fragment.Setting.Interfaces.FragmentsSettingListener;

public class SettingFragment extends Fragment {

    LinearLayout itemAccount;
    LinearLayout itemFrigo;
    LinearLayout itemNotification;

    LinearLayout itemContact;
    LinearLayout itemInfo;

    Spinner spinnerthemes;
    ArrayAdapter<CharSequence>adapter;

    FragmentsSettingListener listener;

    public SettingFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (FragmentsSettingListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        itemAccount=(LinearLayout)view.findViewById(R.id.itemAccount);
        itemFrigo=(LinearLayout)view.findViewById(R.id.itemInfoFridge);
        itemNotification=(LinearLayout)view.findViewById(R.id.itemNotification);

        itemContact=(LinearLayout)view.findViewById(R.id.itemContact);
        itemInfo=(LinearLayout)view.findViewById(R.id.itemInfo);

        itemAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.cambia(5);
            }
        });

        itemFrigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.cambia(3);
            }
        });

        itemNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.cambia(1);
            }
        });


        spinnerthemes=view.findViewById(R.id.spinnerThemes);
        adapter=ArrayAdapter.createFromResource(getContext(),R.array.temi,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerthemes.setAdapter(adapter);

        spinnerthemes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        itemContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.cambia(4);
            }
        });


        itemInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.cambia(2);
            }
        });

        return view;
    }
}
