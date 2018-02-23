package com.example.itsadmin.smartfridge_fragment.Login.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itsadmin.smartfridge_fragment.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistratiFragment extends Fragment {



    Button btnRegistrati;

    EditText regNome;
    EditText regCognome;
    EditText regMail;
    EditText regPassword;

    LoginFragment.FragmentLoginListener listener;

    public RegistratiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (LoginFragment.FragmentLoginListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_registrati, container, false);

        regNome = view.findViewById(R.id.registratiNome);
        regCognome = view.findViewById(R.id.registratiCognome);
        regMail = view.findViewById(R.id.registratiMail);
        regPassword = view.findViewById(R.id.registratiPassword);

        btnRegistrati= view.findViewById(R.id.btnRegistrati);

        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((!(regNome.getText()+"").equals(""))&&(!(regCognome.getText()+"").equals(""))&&(!(regMail.getText()+"").equals(""))&&(!(regPassword.getText()+"").equals(""))){

                    // Aggungi utente al DB
                    listener.cambia(0);
                    regNome.setText("");
                    regCognome.setText("");
                    regPassword.setText("");
                    regMail.setText("");
                }else{

                    Toast.makeText(getActivity(),"Compilare tutti i campi",Toast.LENGTH_LONG).show();
                }

            }
        });

        return view;
    }

}
