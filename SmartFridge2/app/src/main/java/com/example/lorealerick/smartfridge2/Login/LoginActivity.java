package com.example.lorealerick.smartfridge2.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Login.Fragments.FragmentBenvenuto;
import com.example.lorealerick.smartfridge2.Login.Fragments.LoginFragment;
import com.example.lorealerick.smartfridge2.Login.Fragments.RegistratiFragment;
import com.example.lorealerick.smartfridge2.Login.Interfaces.ListenerLogin;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Splash.SplashActivity;
import com.example.lorealerick.smartfridge2.Utils.Frigorifero;
import com.example.lorealerick.smartfridge2.Utils.UtenteCorrente;


public class LoginActivity extends AppCompatActivity implements ListenerLogin{

    private SharedPreferences preferences;
    private Fragment frag [];
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.BlueColor));//cambia colore navigation bar

        fragmentManager = getSupportFragmentManager();

        frag = new Fragment[3];
        iniizializzaFragment();

        preferences = getSharedPreferences("SmartFridge", 0);

        UtenteCorrente.getInstance().seteMail(preferences.getString("email",null));
        UtenteCorrente.getInstance().setPassword(preferences.getString("password",null));

        //controllo dati inseriti per login e salvataggio tramite singleton

        if(UtenteCorrente.getInstance().geteMail()!=null&& UtenteCorrente.getInstance().getPassword()!=null){//

            /*Frigorifero.getInstance().setNome(preferences.getString("nomeFrigo",null));
            Frigorifero.getInstance().setCodice(preferences.getString("codiceFrigo",null));

            if(Frigorifero.getInstance().getNome()!=null&& Frigorifero.getInstance().getCodice()!=null){//

                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
            }else{

                cambiaFragment(2);
            }*/

            changeFragment(-1);

        }else{

            cambiaFragment(0);
        }

    }

    private void iniizializzaFragment (){

        frag [0] = new LoginFragment();
        frag [1] = new RegistratiFragment();
        frag [2] = new FragmentBenvenuto();
    }

    private void changeFragment(int frg){

        if(frg == -1)

            startActivity(new Intent(LoginActivity.this,SplashActivity.class));
        else
            fragmentManager.beginTransaction().replace(R.id.frame_login,frag[frg]).addToBackStack(null).commit();
    }

    @Override
    public void cambiaFragment(int index) {

        changeFragment(index);
    }
}
