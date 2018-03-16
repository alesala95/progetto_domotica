package com.example.lorealerick.smartfridge2.Settings.Fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lorealerick.smartfridge2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AskFragment extends Fragment {

    TextView scrivici;


    public AskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_ask, container, false);


        scrivici=(TextView)view.findViewById(R.id.scrivici);

        final AlertDialog dialog;

        final View alertDialogView = LayoutInflater.from(getContext()).inflate
                (R.layout.dialog_scrivici, null);
        final View titleView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_layout, null);


        dialog = new AlertDialog.Builder(getContext())
                .setView(alertDialogView)
                .setCancelable(true)
                .setPositiveButton("Invia", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();


        scrivici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        return view;
    }

}
