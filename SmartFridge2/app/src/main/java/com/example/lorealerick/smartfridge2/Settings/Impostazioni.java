package com.example.lorealerick.smartfridge2.Settings;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Settings.Fragments.AccountFragment;
import com.example.lorealerick.smartfridge2.Settings.Fragments.FragSettingsHome;
import com.example.lorealerick.smartfridge2.Settings.Fragments.FrigoInfoFragment;
import com.example.lorealerick.smartfridge2.Settings.Interfaces.ListenerImpostazioni;

public class Impostazioni extends AppCompatActivity implements ListenerImpostazioni{

    private Toolbar toolbar;
    private Button goToSettings;
    private Button goToMain;
    private TextView titoloApp;
    private FragmentManager fragmentManager;

    private Fragment frags [];
    private Fragment fragsSettings [];

    SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViews();
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        settings = getSharedPreferences("setting", 0);

        fragmentManager = getSupportFragmentManager();

        inizializzaFragment();
        aggiungiFragment(0,false);
    }

    private void setUpViews(){

        setContentView(R.layout.activity_impostazioni);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);

        goToSettings = findViewById(R.id.goToSettings);
        goToMain = findViewById(R.id.goToMain);
        goToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Impostazioni.super.onBackPressed();
            }
        });


        titoloApp = findViewById(R.id.titoloToolbar);
        setUpToolbar();
    }

    private void inizializzaFragment (){

        frags = new Fragment[2];
        frags [0] = new FragSettingsHome();

        fragsSettings = new Fragment[4];
        fragsSettings [0] = new AccountFragment();
        fragsSettings [1] = new FrigoInfoFragment();
        fragsSettings [2] = new AccountFragment();
        fragsSettings [3] = new AccountFragment();
    }

    private void cambiaFragment (Fragment fragment, boolean addToBackStack ){

        if(!addToBackStack)
            fragmentManager.beginTransaction().replace(R.id.contenitore,fragment).commit();
        else
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.contenitore,fragment).commit();
    }

    private void cambiaFragment (int nFrag, boolean addToBackStack){

        if(!addToBackStack)
            fragmentManager.beginTransaction().replace(R.id.contenitore,frags[nFrag]).commit();
        else
            fragmentManager.beginTransaction().addToBackStack(null).replace(R.id.contenitore,frags[nFrag]).commit();
    }

    private void aggiungiFragment (int nFrag, boolean addToBackStack){

        if(!addToBackStack)
            fragmentManager.beginTransaction().add(R.id.contenitore, frags[nFrag]).commit();
        else
            fragmentManager.beginTransaction().addToBackStack(null).add(R.id.container, frags[nFrag]).commit();
    }

    @Override
    protected void onResume() {

        super.onResume();
        setUpToolbar();
    }

    private void setTitleToolbar (String titolo){

        titoloApp.setText(titolo+"");
    }

    private void setUpToolbar (){

        setTitleToolbar("Impostazioni");
        goToSettings.setVisibility(View.GONE);
        goToMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void onImpostazioneSelezionata(int index) {

        cambiaFragment(fragsSettings[index],true);
    }
}
