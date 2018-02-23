package com.example.itsadmin.smartfridge_fragment.Setting.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itsadmin.smartfridge_fragment.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    LinearLayout changePsw;
    LinearLayout dimension;
    EditText editTextPsw;
    EditText editTextSetPsw;
    EditText editTextSetPsw1;
    Button btnConferma;


    boolean flag=false;

    public AccountFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_account, container, false);

        changePsw=(LinearLayout) view.findViewById(R.id.accountSetPsw);
        dimension=(LinearLayout) view.findViewById(R.id.dimension);
        editTextPsw=(EditText)view.findViewById(R.id.editTextPsw);
        editTextSetPsw=(EditText)view.findViewById(R.id.editTextSetPsw);
        editTextSetPsw1=(EditText)view.findViewById(R.id.editTextSetPsw1);
        btnConferma=(Button)view.findViewById(R.id.btnConferma);

        final ViewGroup.LayoutParams params = dimension.getLayoutParams();


        changePsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {                                //gestione riclick
                    params.height = params.height + 420;
                    dimension.setLayoutParams(params);
                    editTextPsw.setVisibility(View.VISIBLE);
                    editTextSetPsw.setVisibility(View.VISIBLE);
                    editTextSetPsw1.setVisibility(View.VISIBLE);
                    btnConferma.setVisibility(View.VISIBLE);
                    flag=true;
                }else {
                    params.height = params.height - 430;
                    dimension.setLayoutParams(params);
                    editTextPsw.setVisibility(View.GONE);
                    editTextSetPsw.setVisibility(View.GONE);
                    editTextSetPsw1.setVisibility(View.GONE);
                    btnConferma.setVisibility(View.GONE);
                    flag=false;
                }

            }
        });

        btnConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"password cambiata",Toast.LENGTH_LONG).show();
                params.height = params.height - 410;
                dimension.setLayoutParams(params);
                editTextPsw.setVisibility(View.GONE);
                editTextSetPsw.setVisibility(View.GONE);
                editTextSetPsw1.setVisibility(View.GONE);
                btnConferma.setVisibility(View.GONE);
            }
        });



        return view;
    }




}
