package com.example.lorealerick.smartfridge2.Activity.Main;

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
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragCategoria;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragFrigo;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragHome;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragRicettario;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.R;

import net.danlew.android.joda.JodaTimeAndroid;


public class MainActivity extends AppCompatActivity implements ListenerRefreshUI,ListenerApriRicetta{

    private BottomNavigationView navigation;
    private Toolbar toolbar;
    private Fragment frags [];
    private FragmentManager fragmentManager;
    private TextView titoloApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JodaTimeAndroid.init(this);
        setUpViews();

        fragmentManager = getSupportFragmentManager();
        inizializzaFragment();


        aggiungiFragment(0,false);
    }

    private void inizializzaFragment (){

        frags = new Fragment[3];

        frags [0] = new FragHome();
        frags [1] = new FragFrigo();
        frags [2] = new FragRicettario();
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

                setTitleToolbar(dett);
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
}
