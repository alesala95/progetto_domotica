package com.example.lorealerick.smartfridge2.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Login.Fragments.FragmentBenvenuto;
import com.example.lorealerick.smartfridge2.Login.Fragments.LoginFragment;
import com.example.lorealerick.smartfridge2.Login.Fragments.RegistratiFragment;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.Frigorifero;
import com.example.lorealerick.smartfridge2.Utils.Utente;


public class LoginActivity extends AppCompatActivity implements LoginFragment.FragmentLoginListener{

    SharedPreferences preferences;
    Fragment frag [];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.BlueColor));//cambia colore navigation bar

        frag = new Fragment[3];
        iniizializzaFragment();

        preferences = getSharedPreferences("Login", 0);

        //creazione dei dati login su preferences

        Utente.getInstance().seteMail(preferences.getString("eMail",null));
        Utente.getInstance().setPassword(preferences.getString("password",null));

        //controllo dati inseriti per login e salvataggio tramite singleton

        if(Utente.getInstance().geteMail()!=null&&Utente.getInstance().getPassword()!=null){//

            Frigorifero.getInstance().setNome(preferences.getString("nomeFrigo",null));
            Frigorifero.getInstance().setCodice(preferences.getString("codiceFrigo",null));

            if(Frigorifero.getInstance().getNome()!=null&& Frigorifero.getInstance().getCodice()!=null){//

                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
            }else{

                cambiaFragment(2);
            }

        }else{

            cambiaFragment(0);
        }

    }

    private void iniizializzaFragment (){

        frag [0] = new LoginFragment();
        frag [1] = new RegistratiFragment();
        frag [2] = new FragmentBenvenuto();
    }

    private void cambiaFragment(int frg){

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_login,frag[frg]).commit();
    }


    @Override
    public void cambia(int index) {

        if(index != -1){

            cambiaFragment(index);

        }else{
            Intent splash=new Intent(LoginActivity.this,MainActivity.class);
           startActivity(splash);
        }
    }
}
