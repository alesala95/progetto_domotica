package com.example.lorealerick.smartfridge2.Settings.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FrigoInfoFragment extends Fragment implements View.OnClickListener {


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


        swtLight.setOnClickListener(this);
        swtDoor.setOnClickListener(this);
        swtVacationMode.setOnClickListener(this);


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

    @Override
    public void onClick(View v) {

        int id=v.getId();
        Toast.makeText(getContext(),id,Toast.LENGTH_LONG).show();

        switch (id){
            case R.id.swtLight:
                light();
                break;

            case R.id.swtDoor:
                door();
                break;

            case R.id.swtMode:
                mode();
                break;
        }



    }

    private void mode() {

        if(swtVacationMode.isChecked()){

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("mode","attiva" );
            editor.commit();
            String mode = prefs.getString("mode", "disattiva");

            swtVacationMode.setChecked(true);
            swtVacationMode.setText(mode);

        }else{

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("mode","disattiva" );
            editor.commit();
            String mode = prefs.getString("mode", "disattiva");
            swtVacationMode.setText(mode);
            swtVacationMode.setChecked(false);


        }
    }

    private void door() {

        if(swtDoor.isChecked()){

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("alarm","attivo" );
            editor.commit();
            String alarm = prefs.getString("alarm", "attivo");
            swtDoor.setChecked(true);
            swtDoor.setText(alarm);

        }else{

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("alarm","spento" );
            editor.commit();
            String alarm = prefs.getString("alarm", "attivo");
            swtDoor.setText(alarm);
            swtDoor.setChecked(false);


        }
    }

    private void light() {

        if(swtLight.isChecked()){

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("light","accesa" );
            editor.commit();
            String light = prefs.getString("light", "accesa");

            swtLight.setChecked(true);
            swtLight.setText(light);
        }else{

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("light","spenta" );
            editor.commit();

            String light = prefs.getString("light", "accesa");
            Toast.makeText(getContext(),light,Toast.LENGTH_LONG).show();
            swtLight.setText(light);
            swtLight.setChecked(false);
        }

    }



}
