package com.example.lorealerick.smartfridge2.Activity.Login;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Login.Fragments.FragmentBenvenuto;
import com.example.lorealerick.smartfridge2.Activity.Login.Fragments.LoginFragment;
import com.example.lorealerick.smartfridge2.Activity.Login.Fragments.RegistratiFragment;
import com.example.lorealerick.smartfridge2.Activity.Login.Interfaces.ListenerLogin;
import com.example.lorealerick.smartfridge2.Models.Utente;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Activity.Splash.SplashActivity;
import com.example.lorealerick.smartfridge2.Utils.UserControls;
import com.example.lorealerick.smartfridge2.Utils.UtenteCorrente;

public class LoginActivity extends AppCompatActivity implements ListenerLogin{

    private SharedPreferences preferences;
    private Fragment frag [];
    private FragmentManager fragmentManager;
    static final int DIALOG_ERROR_CONNECTION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        fragmentManager = getSupportFragmentManager();

        frag = new Fragment[3];
        iniizializzaFragment();

        preferences = getSharedPreferences("SmartFridge", 0);

        try{

            if(getIntent().getExtras().getInt("from") == 1)
                cambiaFragment(2);

        }catch (NullPointerException e){}

        UtenteCorrente.getInstance().seteMail(preferences.getString("email",null));
        UtenteCorrente.getInstance().setPassword(preferences.getString("password",null));

        UtenteCorrente.getInstance().setCodiceFrigo(preferences.getString("codiceFrigo",null));

        // ------------------------
        UtenteCorrente.getInstance().setCodiceFrigo("sf0001ma");
        preferences.edit().putString("codiceFrigo", UtenteCorrente.getInstance().getCodiceFrigo()).apply();
        // ------------------------

        //controllo dati inseriti per login e salvataggio tramite singleton

        if(UtenteCorrente.getInstance().geteMail()!=null&& UtenteCorrente.getInstance().getPassword()!=null){

            System.out.println("Un utente ha già effettuato l'accesso");

            if(UserControls.isUserExist(UtenteCorrente.getInstance().geteMail(),UtenteCorrente.getInstance().getPassword(),true)){

                System.out.println("I dati sono corretti");

                if(UtenteCorrente.getInstance().getCodiceFrigo() == null){

                    System.out.println("Non ho un frigo");
                    changeFragment(2,false);

                }else{

                    System.out.println("Ho già un frigo");
                    System.out.println("Eseguo l'accesso");
                    changeFragment(-1,true);
                }

            }else{

                System.out.println("I Dati sono incorretti");
                System.out.println("Vado nella login");
                changeFragment(0,false);
            }

        }else{

            System.out.println("Primo Accesso");
            changeFragment(0,false);
        }

    }

    private void iniizializzaFragment (){

        frag [0] = new LoginFragment();
        frag [1] = new RegistratiFragment();
        frag [2] = new FragmentBenvenuto();
    }

    private void changeFragment(int frg, boolean addToBack){

        if(frg == -1) {
            startActivity(new Intent(LoginActivity.this, SplashActivity.class));
        }else if(addToBack){

            fragmentManager.beginTransaction().replace(R.id.frame_login,frag[frg]).addToBackStack(null).commit();
        }else{

            fragmentManager.beginTransaction().replace(R.id.frame_login,frag[frg]).commit();
        }
    }

    @Override
    public void cambiaFragment(int index) {

        changeFragment(index,true);
    }

    @Override
    public void onBackPressed() {
        try{

            if(getIntent().getExtras().getInt("from") == 0 || getIntent().getExtras().getInt("from") == 1)
                finishAffinity();

        }catch (NullPointerException e){

            super.onBackPressed();
        }
    }


}
