package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
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
import com.example.lorealerick.smartfridge2.Utils.UtenteCorrente;
import com.example.lorealerick.smartfridge2.Utils.UtilsPermission;
import com.example.lorealerick.smartfridge2.Utils.UtilsRecipe;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by itsadmin on 19/03/2018.
 */

public class FragCreaRicetta extends Fragment implements View.OnClickListener {

    private EditText durata;
    private EditText ingredienti;
    private EditText procedimento;
    private EditText nome;
    private EditText categoria;
    private Spinner difficolta;
    private ImageView iconRicetta;
    private ArrayAdapter <String> stringArrayAdapter;
    private int PICK_IMAGE_REQUEST = 1;
    private int REQUEST_CAMERA=0, SELECT_FILE=1;
    private String userChoose;
    ListenerRefreshUI listenerRefreshUI;
    int dif;

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

        nome=view.findViewById(R.id.nomeRicettaAggiungi);
        durata = view.findViewById(R.id.durata);
        ingredienti = view.findViewById(R.id.ingredienti);
        procedimento = view.findViewById(R.id.procedimento);
        difficolta = view.findViewById(R.id.difficolta);
        categoria=view.findViewById(R.id.categoria);
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
        setDifficolta();

        if(nome.getText().length()>0&&durata.getText().length()>0&&ingredienti.getText().length()>0&&procedimento.getText().length()>0&&categoria.getText().length()>0){
            Toast.makeText(getContext(),"Grazie per aver creato la tua ricetta!",Toast.LENGTH_LONG).show();
            UtilsRecipe.aggiungiRicetta(nome.getText().toString(),dif,durata.getText().toString(), UtenteCorrente.getInstance().getNomeUtente(),ingredienti.getText().toString(),procedimento.getText().toString(), categoria.getText().toString());
            listenerRefreshUI.onRefreshUI("CreaRicetta","End");
        }
        else
            Toast.makeText(getContext(),"Non hai completato tutti i campi",Toast.LENGTH_LONG).show();
    }

    private void setDifficolta() {
        String val=difficolta.getSelectedItem().toString();
        switch (val){
            case "Facile":
                dif=1;
                break;

            case "Medio":
                dif=2;
                break;

            case "Difficile":
                dif=3;
                break;
        }
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case UtilsPermission.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(userChoose.equals("Take Photo"))
                        cameraIntent();
                    else if(userChoose.equals("Choose from Library"))
                        galleryIntent();
                } else {
                    //code for deny
                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        iconRicetta.setImageBitmap(thumbnail);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        iconRicetta.setImageBitmap(bm);
    }

}
