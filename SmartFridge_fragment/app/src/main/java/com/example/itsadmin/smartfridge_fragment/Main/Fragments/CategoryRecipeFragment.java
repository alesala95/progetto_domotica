package com.example.itsadmin.smartfridge_fragment.Main.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Adapters.AdapterRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.Main.Items.ItemListRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryRecipeFragment extends Fragment {
    FloatingActionButton Floatingbtn;
    FloatingActionButton FBAsearch;
    FloatingActionButton FBAadd;
    FloatingActionButton FBAfavourite;
    static final int RICETTE_CAT = 1;
    static final int RICETTE_PREF = 2;

    int modalita;

    RelativeLayout relativeLayout;

    Button TextFABSearch;
    Button TextFABAdd;
    Button TextFABFavourite;
    EditText editTextSearch;

    TextView catName;

    boolean flag = false;

    ArrayList<ItemListRicetteConsigliate>dati;

    public CategoryRecipeFragment() {
        // Required empty public constructor

    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);

        modalita = args.getInt("mod");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_category_recipe, container, false);

        Bundle b= getArguments();
        String nome= b.getString("cat");

        catName=(TextView)view.findViewById(R.id.catName);
        catName.setText(nome);


        if(modalita==1){

            //query per categoria di ricette
        }else if(modalita==2){

            //query per ricette preferite
        }

        ArrayList<ItemListRicetteConsigliate> cardRicette = new ArrayList<>();
        RecyclerView rw = (RecyclerView) view.findViewById(R.id.rw);

        rw.setLayoutManager(new GridLayoutManager(getContext(),2));

        cardRicette.add(new ItemListRicetteConsigliate("PASTA",R.drawable.pasta));
        cardRicette.add(new ItemListRicetteConsigliate("POLLO",R.drawable.pollo));
        cardRicette.add(new ItemListRicetteConsigliate("TIRA",R.drawable.tira));
        cardRicette.add(new ItemListRicetteConsigliate("PASTA",R.drawable.pasta));
        cardRicette.add(new ItemListRicetteConsigliate("POLLO",R.drawable.pollo));
        cardRicette.add(new ItemListRicetteConsigliate("TIRA",R.drawable.tira));

        rw.setAdapter(new AdapterRicetteConsigliate(cardRicette,getContext(),getFragmentManager()));


        Floatingbtn = (FloatingActionButton) view.findViewById(R.id.FloatingBtn);
        FBAsearch = (FloatingActionButton) view.findViewById(R.id.FBAsearch);
        FBAadd = (FloatingActionButton) view.findViewById(R.id.FBAadd);
        FBAfavourite = (FloatingActionButton) view.findViewById(R.id.FBAfavourite);


        TextFABSearch = (Button) view.findViewById(R.id.TextFABSearch);
        TextFABAdd = (Button) view.findViewById(R.id.TextFABRecipe);
        TextFABFavourite = (Button) view.findViewById(R.id.TextFABFavourite);



        final Animation fadein = new AlphaAnimation(0.0f, 1.0f);
        fadein.setDuration(650);

        final Animation fadeout = new AlphaAnimation(1.0f, 0.0f);
        fadeout.setDuration(500);

        final Animation rightToleft = AnimationUtils.loadAnimation(this.getContext(), R.anim.righttoleft);


        Floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flag) {

                    Floatingbtn.setImageResource(R.mipmap.close);

                    FBAsearch.startAnimation(fadein);
                    FBAsearch.setVisibility(View.VISIBLE);
                    TextFABSearch.startAnimation(rightToleft);
                    TextFABSearch.setVisibility(View.VISIBLE);

                    FBAadd.startAnimation(fadein);
                    FBAadd.setVisibility(View.VISIBLE);
                    TextFABAdd.startAnimation(rightToleft);
                    TextFABAdd.setVisibility(View.VISIBLE);

                    FBAfavourite.startAnimation(fadein);
                    FBAfavourite.setVisibility(View.VISIBLE);
                    TextFABFavourite.startAnimation(rightToleft);
                    TextFABFavourite.setVisibility(View.VISIBLE);


                    flag = true;
                } else {

                    FAB(fadeout);
                }

            }
        });


        editTextSearch = (EditText) view.findViewById(R.id.editTextSearch);

        FBAsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextSearch.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextSearch , InputMethodManager.SHOW_IMPLICIT);

            }

        });

        FBAadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CreateRecipeFragment fragmentCreate=new CreateRecipeFragment();

                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fram,fragmentCreate,"CreateRecipeFragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        FBAfavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }


    private void FAB(Animation fadeout) {
        FBAsearch.startAnimation(fadeout);
        FBAsearch.setVisibility(View.GONE);
        TextFABSearch.startAnimation(fadeout);
        TextFABSearch.setVisibility(View.GONE);

        FBAadd.startAnimation(fadeout);
        FBAadd.setVisibility(View.GONE);
        TextFABAdd.startAnimation(fadeout);
        TextFABAdd.setVisibility(View.GONE);

        FBAfavourite.startAnimation(fadeout);
        FBAfavourite.setVisibility(View.GONE);
        TextFABFavourite.startAnimation(fadeout);
        TextFABFavourite.setVisibility(View.GONE);


        Floatingbtn.setImageResource(R.mipmap.fab);

        editTextSearch.setVisibility(View.GONE);
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editTextSearch.getWindowToken(), 0);
        flag = false;
    }

}
