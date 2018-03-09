package com.example.lorealerick.smartfridge2.Activity.Main;

import android.graphics.Color;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragFrigo;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragHome;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragRicettario;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerEventi;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;


public class MainActivity extends AppCompatActivity{

    private BottomNavigationView navigation;
    private Toolbar toolbar;
    private Fragment frags [];
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViews();

        fragmentManager = getSupportFragmentManager();
        inizializzaFragment();

        if(findViewById(R.id.container) != null)
            if(savedInstanceState != null)
                aggiungiFragment(0,false);


        DatabaseAdapter dbAdapter = new DatabaseAdapter(this);
        dbAdapter.addRicetta(new Ricetta(1,"Pasta",3,"3h","Lore","Spaghetti","Metti l'acqua","","primo"));
        dbAdapter.addRicetta(new Ricetta(2,"Pollo",3,"3h","Lore","Spaghetti","Metti l'acqua","","secondo"));
        dbAdapter.addRicetta(new Ricetta(3,"Lasagne",3,"3h","Lore","Spaghetti","Metti l'acqua","","terzo"));
        dbAdapter.addRicetta(new Ricetta(4,"Brasato",3,"3h","Lore","Spaghetti","Metti l'acqua","","primo"));
        dbAdapter.addRicetta(new Ricetta(5,"Ossobuco",3,"3h","Lore","Spaghetti","Metti l'acqua","","quarto"));
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
            fragmentManager.beginTransaction().add(R.id.container, frags[nFrag]).commit();
        else
            fragmentManager.beginTransaction().addToBackStack(null).add(R.id.container, frags[nFrag]).commit();
    }

    private void setUpViews (){

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setTitle("Smart Fridge");
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

    }


    public void selezionaRicetta(int idRicetta) {

        FragRicetta r = new FragRicetta();
        r.setArguments(new Bundle());


        cambiaFragment(r,true);
    }
}
