package com.example.lorealerick.smartfridge2.Activity.Main;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragCategoria;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragCreaRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragFrigo;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragHome;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragPreferite;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragRicerca;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragRicettario;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerAggiungiRimuoviRicettaPreferita;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Activity.Settings.Impostazioni;
import com.example.lorealerick.smartfridge2.Utils.UtilsAnimation;

import net.danlew.android.joda.JodaTimeAndroid;


public class MainActivity extends AppCompatActivity implements ListenerRefreshUI,ListenerApriRicetta,ListenerAggiungiRimuoviRicettaPreferita{

    private BottomNavigationView navigation;
    private Toolbar toolbar;
    private Fragment frags [];
    private FragmentManager fragmentManager;
    private TextView titoloApp;
    private Button goToSettings;
    private Button goToMain;
    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);
        setUpViews();

        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        fragmentManager = getSupportFragmentManager();

        databaseAdapter = new DatabaseAdapter(this);

        inizializzaFragment();

        aggiungiFragment(0,false);
    }

    private void inizializzaFragment (){

        frags = new Fragment[4];

        frags [0] = new FragHome();
        frags [1] = new FragFrigo();
        frags [2] = new FragRicettario();
        frags [3] = new FragCreaRicetta();
    }

    private void cambiaFragment (Fragment fragment, boolean addToBackStack){

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

    private void setUpViews (){

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        toolbar.setTitleTextColor(Color.WHITE);

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { //gestione bottom navigation menu

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        cambiaFragment(0,true);
                        return true;
                    case R.id.navigation_frigo:

                        cambiaFragment(1,true);
                        return true;
                    case R.id.navigation_recipe:

                        cambiaFragment(2,true);
                        return true;
                }

                return false;
            }
        });

        titoloApp = findViewById(R.id.titoloToolbar);
        goToSettings = findViewById(R.id.goToSettings);
        goToSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, Impostazioni.class));
            }
        });
        goToMain = findViewById(R.id.goToMain);

        setUpToolbar();
    }

    private void setTitleToolbar (String titolo){

        titoloApp.setText(titolo);
    }

    @Override
    public void onRefreshUI(String tipoFrag, String dett) {

        Menu menu = navigation.getMenu();

        switch (tipoFrag){

            case "Categoria":

                setTitleToolbar(dett);
                menu.getItem(2).setChecked(true);
                break;

            case "Home":

                setTitleToolbar("SmartFridge");
                menu.getItem(0).setChecked(true);
                break;

            case "Frigo":

                setTitleToolbar("I Miei Alimenti");
                menu.getItem(1).setChecked(true);
                break;

            case "Ricettario":

                setTitleToolbar("Ricettario");
                menu.getItem(2).setChecked(true);
                break;

            case "Ricetta":

                UtilsAnimation.startFadeInAnimation(titoloApp,this);
                setTitleToolbar(dett);
                menu.getItem(2).setChecked(true);
                break;

            case "CreaRicetta":

                setTitleToolbar("Crea Ricetta");
                menu.getItem(2).setChecked(true);

                if (dett != null)
                    if (dett.equals("End"))
                        onBackPressed();

                break;

            case "Ricerca":

                setTitleToolbar("Ricerca");
                menu.getItem(2).setChecked(true);
                break;

            case "Preferite":

                setTitleToolbar("Preferite");
                menu.getItem(2).setChecked(true);
                break;
        }
    }

    @Override
    public void apriRicetta(int idRicetta) {

        FragRicetta fragRicetta = new FragRicetta();

        Bundle bundle = new Bundle();
        bundle.putInt("id",idRicetta);

        fragRicetta.setArguments(bundle);

        cambiaFragment(fragRicetta,true);
    }

    @Override
    public void apriCategoriaRicetta(String category) {

        FragCategoria fragCategoria = new FragCategoria();

        Bundle bundle = new Bundle();
        bundle.putString("category",category);

        fragCategoria.setArguments(bundle);

        cambiaFragment(fragCategoria,true);
    }


    public void apriCreateRicetta(){
        FragCreaRicetta fragCreaRicetta = new FragCreaRicetta();
        cambiaFragment(fragCreaRicetta,true);
    }

    @Override
    public void apriRicerca() {

        FragRicerca fragRicerca = new FragRicerca();
        cambiaFragment(fragRicerca, true);
    }


    @Override
    protected void onResume() {

        super.onResume();
        setUpToolbar();
    }

    private void setUpToolbar(){

        goToSettings.setVisibility(View.VISIBLE);
        goToMain.setVisibility(View.GONE);
    }

    @Override
    public void rimuoviRicettaPreferita(int id) {

        databaseAdapter.rimuoviRicettaPreferita(id);
    }

    @Override
    public void aggiungiRicettaPreferita(int id) {

        databaseAdapter.addRicettaPreferita(id);
    }

    @Override
    public void apriPreferite() {

        FragPreferite fragPreferite = new FragPreferite();
        cambiaFragment(fragPreferite, true);
    }
}
