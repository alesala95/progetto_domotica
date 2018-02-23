package com.example.itsadmin.smartfridge_fragment.Main.Fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.itsadmin.smartfridge_fragment.R;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateRecipeFragment extends Fragment {

    CardView card_recipe;
    EditText editTextNomeRecipe,editTextDifficolta, editTextTempo;
    EditText editTextIngredienti, editTextProcedimento;
    Button btnAggiungi;

    private int PICK_IMAGE_REQUEST = 1;

    public CreateRecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_create_recipe, container, false);


        card_recipe=(CardView)view.findViewById(R.id.card_img_recipe);
        editTextNomeRecipe=(EditText)view.findViewById(R.id.editTextNomeRecipe);
        editTextDifficolta=(EditText)view.findViewById(R.id.editTextDifficolta);
        editTextTempo=(EditText)view.findViewById(R.id.editTextTempo);
        editTextIngredienti=(EditText)view.findViewById(R.id.editTextIngredienti);
        editTextProcedimento=(EditText)view.findViewById(R.id.editTextProcedimento);
        btnAggiungi=(Button)view.findViewById(R.id.btnAggiungi);

        card_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");//mostra solo foto
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // selezione foto
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


            }
        });


        editTextNomeRecipe.getText();
        editTextTempo.getText();
        editTextDifficolta.getText();
        editTextIngredienti.getText();
        editTextProcedimento.getText();

        btnAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Ricetta creata, verrà aggiunta appena effettuati i controlli",Toast.LENGTH_LONG).show();

                RecipeFragment fragmentRecipe=new RecipeFragment();
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fram,fragmentRecipe,"CreateRecipeFragment");
                fragmentTransaction.commit();
            }
        });


        return view;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {//imposta foto nella cardView
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                Drawable drawable = new BitmapDrawable(getResources(), bitmap);
                card_recipe.setBackground(drawable);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
