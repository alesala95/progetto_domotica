package com.example.lorealerick.smartfridge2.Activity.Settings.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Activity.Login.LoginActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Frigo;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;
import com.example.lorealerick.smartfridge2.Utils.UserControls;
import com.example.lorealerick.smartfridge2.Utils.UtenteCorrente;

public class FrigoInfoFragment extends Fragment {

    private TextView modelloFrigo;
    private TextView capacitaFrigo;
    private TextView capacitaFreezer;
    private TextView nPorte;
    private TextView tipoRaffreddamento;
    private TextView classeEnergetica;
    private Button btnDisconnetti;

    private Switch swtLight;
    private Switch swtDoor;
    private Switch swtVacationMode;

    private DatabaseAdapter databaseAdapter;
    private SharedPreferences sharedPreferences;

    private Frigo frigo;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        databaseAdapter = new DatabaseAdapter(context);
        sharedPreferences = context.getSharedPreferences("SmartFridge",Context.MODE_PRIVATE);
    }

    public FrigoInfoFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frigo_info, container, false);

        frigo = databaseAdapter.getFrigo(UtenteCorrente.getInstance().getCodiceFrigo());

        modelloFrigo = view.findViewById(R.id.modelloFrigo);
        modelloFrigo.setText(frigo.getModello());
        capacitaFrigo = view.findViewById(R.id.capacitaFrigo);
        capacitaFrigo.setText(frigo.getCapacitaFrigo()+"");
        capacitaFreezer = view.findViewById(R.id.capacitaFreezer);
        capacitaFreezer.setText(frigo.getCapacitaFreezer()+"");
        nPorte = view.findViewById(R.id.numPorte);
        nPorte.setText(frigo.getnPorte()+"");
        tipoRaffreddamento = view.findViewById(R.id.tipoRaff);
        tipoRaffreddamento.setText(frigo.getTipoRaffreddamento());
        classeEnergetica = view.findViewById(R.id.classeEn);
        classeEnergetica.setText(frigo.getClasseEnergetica());

        swtLight = view.findViewById(R.id.swtLight);
        swtLight.setChecked(frigo.getLampadinaAccesa());
        swtLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                aggiorna();
            }
        });

        swtDoor = view.findViewById(R.id.swtDoor);
        swtDoor.setChecked(frigo.getAllarmeAttivo());
        swtDoor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                aggiorna();
            }
        });

        swtVacationMode = view.findViewById(R.id.swtMode);
        swtVacationMode.setChecked(frigo.getVacationMode());
        swtVacationMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                aggiorna();
            }
        });

        btnDisconnetti = view.findViewById(R.id.btnDisconnetti);
        btnDisconnetti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UtenteCorrente.getInstance().setCodiceFrigo(null);
                sharedPreferences.edit().remove("codiceFrigo").apply();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("from",1);
                startActivity(intent);
            }
        });

        return view;
    }

    private void aggiorna (){

        UserControls.aggiornaImpostazioniFrigo(swtLight.isChecked(),swtDoor.isChecked(),swtVacationMode.isChecked());

        databaseAdapter.svuotaTabellaFrigo();
        databaseAdapter.addFrigo(DownloadDati.scaricaInfoFrigo());

        frigo = databaseAdapter.getFrigo(UtenteCorrente.getInstance().getCodiceFrigo());
    }
}
