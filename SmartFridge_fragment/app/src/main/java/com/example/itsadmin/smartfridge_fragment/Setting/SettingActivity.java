package com.example.itsadmin.smartfridge_fragment.Setting;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.itsadmin.smartfridge_fragment.Main.MainActivity;
import com.example.itsadmin.smartfridge_fragment.R;
import com.example.itsadmin.smartfridge_fragment.Setting.Fragments.AccountFragment;
import com.example.itsadmin.smartfridge_fragment.Setting.Fragments.ContactFragment;
import com.example.itsadmin.smartfridge_fragment.Setting.Fragments.FrigoInformationFragment;
import com.example.itsadmin.smartfridge_fragment.Setting.Fragments.InfoFragment;
import com.example.itsadmin.smartfridge_fragment.Setting.Fragments.NotificationFragment;
import com.example.itsadmin.smartfridge_fragment.Setting.Fragments.SettingFragment;
import com.example.itsadmin.smartfridge_fragment.Setting.Interfaces.FragmentsSettingListener;

public class SettingActivity extends AppCompatActivity implements FragmentsSettingListener{

    SharedPreferences settings;
    Toolbar toolbar;

    Fragment frag [];
    String titles [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setTheme(R.style.Grifondoro);

        setUpViews();

        settings = getSharedPreferences("setting", 0);

        frag = new Fragment[6];
        titles = new String[6];

        inizializzaFragment();

        cambiaFragment(0);
    }

    private void setUpViews (){

        setContentView(R.layout.activity_setting);

        toolbar = findViewById(R.id.barra);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

                return false;
            }
        });
    }

    private void inizializzaFragment (){

        frag [0] = new SettingFragment();
        frag [1] = new NotificationFragment();
        frag [2] = new InfoFragment();
        frag [3] = new FrigoInformationFragment();
        frag [4] = new ContactFragment();
        frag [5] = new AccountFragment();

        titles [0] = "Impostazioni";
        titles [1] = "Notifiche";
        titles [2] = "Informazioni";
        titles [3] = "Il Mio Frigo";
        titles [4] = "Contatti";
        titles [5] = "Account";
    }

    private void cambiaFragment (int nFrag){

        FragmentTransaction fragmentTransactionSet = getSupportFragmentManager().beginTransaction();
        fragmentTransactionSet.replace(R.id.frame_setting,frag[nFrag]);

        if(nFrag!=0)
            fragmentTransactionSet.addToBackStack(null);

        fragmentTransactionSet.commit();
        getSupportActionBar().setTitle(titles[nFrag]);
    }

    @Override
    public void cambia(int f) {

        cambiaFragment(f);
    }
}
