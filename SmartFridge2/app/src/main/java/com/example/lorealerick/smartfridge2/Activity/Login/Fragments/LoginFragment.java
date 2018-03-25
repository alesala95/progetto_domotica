package com.example.lorealerick.smartfridge2.Activity.Login.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Login.Interfaces.ListenerLogin;
import com.example.lorealerick.smartfridge2.Activity.Login.LoginActivity;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.UserControls;
import com.example.lorealerick.smartfridge2.Utils.UtenteCorrente;

public class LoginFragment extends Fragment{

    private EditText editEMail;
    private EditText editPassword;
    private Button btnAccedi;
    private Button btnRegistrati;
    private CheckBox ricordami;
    private TextView dimenticatoPassword;
    private ListenerLogin listenerLogin;
    private SharedPreferences sharedPreferences;

    public LoginFragment (){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listenerLogin = (LoginActivity) context;
        sharedPreferences = context.getSharedPreferences("SmartFridge",Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        editEMail = view.findViewById(R.id.editLoginMail);
        editPassword = view.findViewById(R.id.editLoginPassword);
        btnAccedi = view.findViewById(R.id.btnAccedi);
        btnRegistrati = view.findViewById(R.id.btnRegistrati);
        ricordami = view.findViewById(R.id.ricordamiChb);
        dimenticatoPassword = view.findViewById(R.id.dimenticatoPassword);
        dimenticatoPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(),"Cambio password",Toast.LENGTH_SHORT).show();
            }
        });


        //controllo dati login
        btnAccedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((!(editEMail.getText()+"").equals(""))&&(!(editPassword.getText()+"").equals(""))){

                    if(UserControls.isUserExist(editEMail.getText().toString(),editPassword.getText().toString(),true)){ //se utente esiste

                        if(ricordami.isChecked()){//memorizzazione utente

                            sharedPreferences.edit().putString("email",UtenteCorrente.getInstance().geteMail()).apply();
                            sharedPreferences.edit().putString("password",UtenteCorrente.getInstance().getPassword()).apply();
                        }

                       if(sharedPreferences.getString("codiceFrigo",null)==null){

                            listenerLogin.cambiaFragment(2);
                       }else{

                           listenerLogin.cambiaFragment(-1);
                       }

                    }else{

                        Toast.makeText(getActivity(),"UtenteCorrente o password errati",Toast.LENGTH_LONG).show();
                    }

                }else{

                    Toast.makeText(getActivity(),"Compilare i campi",Toast.LENGTH_LONG).show();
                }
            }
        });

//apertura fragment registrati
        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listenerLogin.cambiaFragment(1);
            }
        });

        return view;
    }
}
