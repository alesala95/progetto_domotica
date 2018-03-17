package com.example.lorealerick.smartfridge2.Activity.Settings.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Login.LoginActivity;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.UserControls;
import com.example.lorealerick.smartfridge2.Utils.UtenteCorrente;

public class AccountFragment extends Fragment {

    private TextView nome;
    private TextView cognome;
    private TextView email;
    private TextView password;

    private Button btnLogout;
    private SharedPreferences sharedPreferences;

    EditText vecchiaPassword;
    EditText nuovaPassword;
    EditText confermaPassword;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        sharedPreferences = context.getSharedPreferences("SmartFridge", Context.MODE_PRIVATE);
    }

    public AccountFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_account, container, false);

        nome = view.findViewById(R.id.nomeAccount);
        nome.setText(UtenteCorrente.getInstance().getNomeUtente());
        cognome = view.findViewById(R.id.cognomeAccount);
        cognome.setText(UtenteCorrente.getInstance().getCognomeUtente());
        email = view.findViewById(R.id.emailAccount);
        email.setText(UtenteCorrente.getInstance().geteMail());
        password = view.findViewById(R.id.changePass);

        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sharedPreferences.edit().clear().apply();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.putExtra("from",0);
                startActivity(intent);
            }
        });

        final AlertDialog dialog;

        final View alertDialogView = LayoutInflater.from(getContext()).inflate(R.layout.changepass_dialog, null);
        final View titleView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout, null);


        dialog = new AlertDialog.Builder(getContext())
                .setView(alertDialogView)
                .setCancelable(true)
                .setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String vp = vecchiaPassword.getText().toString();
                        String np = nuovaPassword.getText().toString();
                        String cp = confermaPassword.getText().toString();

                        if (vp.equals("")||np.equals("")||cp.equals("")){

                            Toast.makeText(getActivity(),"Compilare tutti i campi",Toast.LENGTH_SHORT).show();

                        }else if(vp.equals(UtenteCorrente.getInstance().getPassword())){

                            if (np.equals(cp)){

                                UserControls.aggiornaPassword(np, true, sharedPreferences);
                                Toast.makeText(getActivity(),"Password cambiata correttamente",Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else{

                                Toast.makeText(getActivity(),"Le password non corrispondono",Toast.LENGTH_SHORT).show();
                            }

                        }else{

                            Toast.makeText(getActivity(),"Password errata",Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                .setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                })

                .create();

        vecchiaPassword = alertDialogView.findViewById(R.id.currentPass);
        nuovaPassword = alertDialogView.findViewById(R.id.newPass);
        confermaPassword = alertDialogView.findViewById(R.id.confirmPass);

        password.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               dialog.show();
           }
       });


        return view;
    }
}
