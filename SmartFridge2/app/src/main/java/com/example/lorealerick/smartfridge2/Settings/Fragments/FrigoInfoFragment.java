package com.example.lorealerick.smartfridge2.Settings.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.example.lorealerick.smartfridge2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrigoInfoFragment extends Fragment {


    Switch swtLight;
    Switch swtDoor;
    Switch swtVacationMode;

    SharedPreferences prefs;

    public FrigoInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_frigo_info, container, false);

        swtLight=(Switch)view.findViewById(R.id.swtLight);
        swtDoor=(Switch)view.findViewById(R.id.swtDoor);
        swtVacationMode=(Switch)view.findViewById(R.id.swtMode);

        prefs= getActivity().getSharedPreferences("settings",0);
        initialize();




        return view;
    }





    private void initialize() {//inizializzazione degli switch
        String setSwitch = prefs.getString("light", "accesa");


        if (setSwitch.equals("accesa")){
            swtLight.setChecked(true);
            swtLight.setText(setSwitch);

        }else{
            swtLight.setChecked(false);
            swtLight.setText(setSwitch);

        }

        setSwitch = prefs.getString("alarm", "attivo");

        if (setSwitch.equals("attivo")){
            swtDoor.setChecked(true);
            swtDoor.setText(setSwitch);


        }else{
            swtDoor.setChecked(false);
            swtDoor.setText(setSwitch);

        }

        setSwitch = prefs.getString("mode", "disattiva");

        if (setSwitch.equals("attiva")){
            swtVacationMode.setChecked(true);
            swtVacationMode.setText(setSwitch);

        }else{
            swtVacationMode.setChecked(false);
            swtVacationMode.setText(setSwitch);

        }

    }

}
