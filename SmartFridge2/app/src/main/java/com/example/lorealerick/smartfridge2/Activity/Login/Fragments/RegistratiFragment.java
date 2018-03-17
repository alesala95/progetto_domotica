package com.example.lorealerick.smartfridge2.Activity.Login.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Login.Interfaces.ListenerLogin;
import com.example.lorealerick.smartfridge2.Activity.Login.LoginActivity;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.UserControls;

public class RegistratiFragment extends Fragment {

    private Button btnRegistrati;

    private EditText regNome;
    private EditText regCognome;
    private EditText regMail;
    private EditText regPassword;

    private ListenerLogin listenerLogin;

    public RegistratiFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listenerLogin = (LoginActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_registrati, container, false);

        regNome = view.findViewById(R.id.registratiNome);
        regCognome = view.findViewById(R.id.registratiCognome);
        regMail = view.findViewById(R.id.registratiMail);
        regPassword = view.findViewById(R.id.registratiPassword);

        btnRegistrati= view.findViewById(R.id.btnRegistrati);

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((!(regNome.getText()+"").equals(""))&&(!(regCognome.getText()+"").equals(""))&&(!(regMail.getText()+"").equals(""))&&(!(regPassword.getText()+"").equals(""))){

                    //listenerLogin.cambiaFragment(0);
                    if(UserControls.aggiungiUtente(regMail.getText().toString(),regPassword.getText().toString(),regNome.getText().toString(),regCognome.getText().toString())){

                        Toast.makeText(getActivity(),"Registrato",Toast.LENGTH_LONG).show();
                        listenerLogin.cambiaFragment(0);
                    }else{

                        Toast.makeText(getActivity(),"Registrazione Fallita",Toast.LENGTH_LONG).show();
                    }

                }else{

                    Toast.makeText(getActivity(),"Compilare tutti i campi",Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

}
