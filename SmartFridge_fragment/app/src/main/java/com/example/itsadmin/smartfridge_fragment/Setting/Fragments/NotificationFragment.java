package com.example.itsadmin.smartfridge_fragment.Setting.Fragments;


import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {

    Switch swtNotFridge;
    Switch swtNotShop;

    LinearLayout linearLayoutVibrationFridge;
    LinearLayout linearLayoutSoundFridge;
    TextView textViewTypeVibrationFridge;
    TextView textViewTypeSoundFridge;

    LinearLayout linearLayoutVibrationShop;
    LinearLayout linearLayoutSoundShop;
    TextView textViewTypeVibrationShop;
    TextView textViewTypeSoundShop;


    int flag=0;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view=inflater.inflate(R.layout.fragment_notification, container, false);

        swtNotFridge=(Switch)view.findViewById(R.id.swtEnableNot);
        linearLayoutVibrationFridge=(LinearLayout)view.findViewById(R.id.linearLayoutVibrationFridge);
        linearLayoutSoundFridge=(LinearLayout)view.findViewById(R.id.linearLayoutSoundFridge);
        textViewTypeVibrationFridge=(TextView)view.findViewById(R.id.textViewTypeVibrationFridge);
        textViewTypeSoundFridge=(TextView)view.findViewById(R.id.textViewTypeSoundFridge);

        swtNotShop=(Switch)view.findViewById(R.id.swtEnableNotShop);
        linearLayoutVibrationShop=(LinearLayout)view.findViewById(R.id.linearLayoutVibrationShop);
        linearLayoutSoundShop=(LinearLayout)view.findViewById(R.id.linearLayoutSoundShop);
        textViewTypeVibrationShop=(TextView)view.findViewById(R.id.textViewTypeVibrationShop);
        textViewTypeSoundShop=(TextView)view.findViewById(R.id.textViewTypeSoundShop);

        swtNotFridge.setChecked(true);
        swtNotShop.setChecked(true);

        swtNotFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swtNotFridge.isChecked()){
                    swtNotFridge.setText("Attive");
                }else {
                    swtNotFridge.setChecked(false);
                    swtNotFridge.setText("Disattivate");
                }
            }
        });

        swtNotShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swtNotShop.isChecked()){
                    swtNotShop.setText("Attive");
                }else {
                    swtNotShop.setChecked(false);
                    swtNotShop.setText("Disattivate");
                }
            }
        });



        final LinearLayout page=(LinearLayout) view.findViewById(R.id.page);
        final View snackBar=(View) view.findViewById(R.id.RLsnack);
        final View snackBarAudio=(View) view.findViewById(R.id.snack_bar_audio);


        linearLayoutVibrationFridge.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                Animation showPopIn=AnimationUtils.loadAnimation(getContext(),R.anim.show_popup_in);
                snackBar.startAnimation(showPopIn);
                snackBar.setVisibility(View.VISIBLE);

               page.setForeground(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.transparent)));//API 23, oscuramentoschermata
                flag=1;

            }
        });


        linearLayoutSoundFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation showPopIn=AnimationUtils.loadAnimation(getContext(),R.anim.show_popup_in);
                snackBarAudio.startAnimation(showPopIn);
                snackBarAudio.setVisibility(View.VISIBLE);

                page.setForeground(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.transparent)));//API 23, oscuramentoschermata
                flag=2;

            }
        });

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag!=0) {
                    Animation showPopOut = AnimationUtils.loadAnimation(getContext(), R.anim.show_popup_out);
                    page.setForeground(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.transparentOff)));//API 23, oscuramentoschermata
                    switch (flag){
                        case 1:
                            snackBar.startAnimation(showPopOut);
                            snackBar.setVisibility(View.GONE);
                            flag=0;
                            break;

                        case 2:
                            snackBarAudio.startAnimation(showPopOut);
                            snackBarAudio.setVisibility(View.GONE);
                            flag=0;
                            break;

                    }

                }
            }
        });



        // Inflate the layout for this fragment
        return view;
    }

}
