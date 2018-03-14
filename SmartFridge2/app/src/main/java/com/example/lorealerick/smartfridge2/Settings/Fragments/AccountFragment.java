package com.example.lorealerick.smartfridge2.Settings.Fragments;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    TextView nome,cognome, email, password;
    Button confirm;

    boolean flag=false;

    public AccountFragment() {
        // Required empty public constructor
    }



    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_account, container, false);

        nome=(TextView) view.findViewById(R.id.nomeAccount);
        cognome=(TextView) view.findViewById(R.id.cognomeAccount);
        email=(TextView) view.findViewById(R.id.emailAccount);
        password=(TextView) view.findViewById(R.id.changePass);

        final AlertDialog dialog;

        final View alertDialogView = LayoutInflater.from(getContext()).inflate
                (R.layout.changepass_dialog, null);
        final View titleView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout, null);

        dialog = new AlertDialog.Builder(getContext())
                .setView(alertDialogView)
                .setCancelable(true)
                .create();

        confirm=(Button)dialog.findViewById(R.id.confirm);




        password.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               dialog.show();


           }
       });


        return view;
    }




}
