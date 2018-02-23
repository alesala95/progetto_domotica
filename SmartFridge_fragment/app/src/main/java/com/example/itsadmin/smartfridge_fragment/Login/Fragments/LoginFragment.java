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
 * Created by itsadmin on 22/02/2018.
 */

public class LoginFragment extends Fragment{

    public interface FragmentLoginListener{

        public void cambia (int i);
    }

    Button btnAccedi;
    Button btnRegistrati;

    EditText eMail;
    EditText password;

    FragmentLoginListener list;

    public LoginFragment (){


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        list = (FragmentLoginListener)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_login, container, false);

        btnAccedi=(Button)view.findViewById(R.id.btnAccedi);
        btnRegistrati=(Button)view.findViewById(R.id.btnRegistrati);

        eMail = view.findViewById(R.id.editLoginMail);
        password = view.findViewById(R.id.editLoginPassword);

        btnAccedi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((!(eMail.getText()+"").equals(""))&&(!(password.getText()+"").equals(""))){

                    // query per vedere se l'utente esiste

                    if( true ){ //se utente esiste

                        list.cambia(2);
                        eMail.setText("");
                        password.setText("");
                    }else{

                        Toast.makeText(getActivity(),"Utente o password errati",Toast.LENGTH_LONG).show();
                    }
                }else{

                    Toast.makeText(getActivity(),"Compilare i campi",Toast.LENGTH_LONG).show();
                }
            }
        });


        btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                list.cambia(1);
                eMail.setText("");
                password.setText("");
            }
        });

        return view;
    }
}
