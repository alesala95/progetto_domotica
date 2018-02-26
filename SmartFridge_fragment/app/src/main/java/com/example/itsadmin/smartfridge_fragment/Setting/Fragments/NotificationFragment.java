package com.example.itsadmin.smartfridge_fragment.Setting.Fragments;


import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itsadmin.smartfridge_fragment.R;

import ru.whalemare.sheetmenu.SheetMenu;


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



        linearLayoutVibrationFridge.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getActivity());
                final View sheetView = getActivity().getLayoutInflater().inflate(R.layout.snack_bar, null);
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();

                RadioButton rbtnDisable=(RadioButton)sheetView.findViewById(R.id.rbtnDisable);
                RadioButton rbtnDeafault=(RadioButton)sheetView.findViewById(R.id.rbtnDeafault);
                RadioButton rbtnShort=(RadioButton)sheetView.findViewById(R.id.rbtnShort);
                RadioButton rbtnLong=(RadioButton)sheetView.findViewById(R.id.rbtnLong);

                rbtnDisable.setOnClickListener(gestore);
                rbtnDeafault.setOnClickListener(gestore);
                rbtnShort.setOnClickListener(gestore);
                rbtnLong.setOnClickListener(gestore);
            }

        });


        linearLayoutSoundFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(getActivity());
                final View sheetView = getActivity().getLayoutInflater().inflate(R.layout.snack_bar_audio, null);
                mBottomSheetDialog.setContentView(sheetView);
                mBottomSheetDialog.show();

                RadioButton rbtnDeaf=(RadioButton)sheetView.findViewById(R.id.rbtnDeaf);
                RadioButton rbtnSilence=(RadioButton)sheetView.findViewById(R.id.rbtnSilence);
                RadioButton rbtnWhisper=(RadioButton)sheetView.findViewById(R.id.rbtnWhisper);
                RadioButton rbtnRing=(RadioButton)sheetView.findViewById(R.id.rbtnRing);

                rbtnDeaf.setOnClickListener(gestore);
                rbtnSilence.setOnClickListener(gestore);
                rbtnWhisper.setOnClickListener(gestore);
                rbtnRing.setOnClickListener(gestore);

            }
        });

        return view;
    }

    View.OnClickListener gestore = new View.OnClickListener() {
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.rbtnDeafault:
                    Toast.makeText(getActivity(),"default",Toast.LENGTH_LONG).show();
                    break;
                case R.id.rbtnDisable:
                    Toast.makeText(getActivity(),"disable",Toast.LENGTH_LONG).show();
                    break;

                case R.id.rbtnShort:
                    Toast.makeText(getActivity(),"short",Toast.LENGTH_LONG).show();
                    break;

                case R.id.rbtnLong:
                    Toast.makeText(getActivity(),"long",Toast.LENGTH_LONG).show();
                    break;

                case R.id.rbtnDeaf:
                    Toast.makeText(getActivity(),"deaf",Toast.LENGTH_LONG).show();
                    break;

                case R.id.rbtnSilence:
                    Toast.makeText(getActivity(),"silence",Toast.LENGTH_LONG).show();
                    break;

                case R.id.rbtnWhisper:
                    Toast.makeText(getActivity(),"whisper",Toast.LENGTH_LONG).show();
                    break;

                case R.id.rbtnRing:
                    Toast.makeText(getActivity(),"ring",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

}
