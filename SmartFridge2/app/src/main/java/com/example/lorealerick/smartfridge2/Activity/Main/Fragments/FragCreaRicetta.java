package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.UtilsPermission;

import java.util.ArrayList;

/**
 * Created by itsadmin on 19/03/2018.
 */

public class FragCreaRicetta extends Fragment implements View.OnClickListener {

    private EditText durata;
    private EditText ingredienti;
    private EditText procedimento;
    private Spinner difficolta;
    private ImageView iconRicetta;
    private ArrayAdapter <String> stringArrayAdapter;
    private int PICK_IMAGE_REQUEST = 1;
    private int REQUEST_CAMERA=0, SELECT_FILE=1;
    private String userChoose;
    ListenerRefreshUI listenerRefreshUI;

    FloatingActionButton upRecipe;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listenerRefreshUI = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_aggiungi_ricetta,container,false);

        listenerRefreshUI.onRefreshUI("CreaRicetta",null);

        durata = view.findViewById(R.id.durata);
        ingredienti = view.findViewById(R.id.ingredienti);
        procedimento = view.findViewById(R.id.procedimento);
        difficolta = view.findViewById(R.id.difficolta);
        iconRicetta=view.findViewById(R.id.iconRicetta);
        upRecipe=view.findViewById(R.id.upRecipe);

        ArrayList <String> difficulties = new ArrayList<>();
        difficulties.add("Facile");
        difficulties.add("Medio");
        difficulties.add("Difficile");

        stringArrayAdapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,difficulties);
        difficolta.setAdapter(stringArrayAdapter);

        iconRicetta.setOnClickListener(this);
        upRecipe.setOnClickListener(this);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("CreaRicetta",null);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.iconRicetta:
                selectImage();
                break;

            case R.id.upRecipe:
                check();
                break;
        }


    }

    private void check() {
        if(durata.getText().length()>0&&ingredienti.getText().length()>0&&procedimento.getText().length()>0&&difficolta.isSelected()){
            Toast.makeText(getContext(),"Grazie per aver creato la tua ricetta!",Toast.LENGTH_LONG).show();
            
        }
        else
            Toast.makeText(getContext(),"Non hai completato tutti i campi",Toast.LENGTH_LONG).show();
    }

    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void cameraIntent() {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CAMERA);
    }

    private void selectImage(){
        final CharSequence[] items= {"Scatta una foto","Scegli dalla galleria","Annulla"};

        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle("Aggiungi una foto");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                boolean result= UtilsPermission.checkPermission(getContext());

                if(items[which].equals("Scatta una foto")){
                    userChoose="Scatta una foto";
                    if(result)
                        cameraIntent();
                }else if(items[which].equals("Scegli dalla galleria")){
                    userChoose="Scegli dalla galleria";
                    if (result)
                        galleryIntent();
                }else if (items[which].equals("Annulla"))
                    dialog.dismiss();

            }
        });
        builder.show();
    }



}
