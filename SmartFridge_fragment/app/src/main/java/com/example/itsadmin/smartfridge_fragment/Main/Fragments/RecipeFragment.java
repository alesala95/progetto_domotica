package com.example.itsadmin.smartfridge_fragment.Main.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
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
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Adapters.AdapterRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.Main.Items.ItemListRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.R;

import java.util.ArrayList;


public class RecipeFragment extends Fragment implements View.OnClickListener{

    FloatingActionButton Floatingbtn;
    FloatingActionButton FBAsearch;
    FloatingActionButton FBAadd;
    FloatingActionButton FBAfavourite;

    RelativeLayout relativeLayout;

    Button TextFABSearch;
    Button TextFABAdd;
    Button TextFABFavourite;
    EditText editTextSearch;

    boolean flag = false;

    TextView catAntipasti,catPrimi,catSecondi,catDolci,catSpuntini;

    public RecipeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_recipe, container, false);

        catAntipasti = view.findViewById(R.id.catAntipasti);
        catPrimi = view.findViewById(R.id.catPrimi);
        catSecondi = view.findViewById(R.id.catSecondi);
        catDolci = view.findViewById(R.id.catDolci);
        catSpuntini = view.findViewById(R.id.catSpuntini);

        //gestione click delle varie categorie
        catAntipasti.setOnClickListener(this);
        catPrimi.setOnClickListener(this);
        catSecondi.setOnClickListener(this);
        catDolci.setOnClickListener(this);
        catSpuntini.setOnClickListener(this);


        ArrayList<ItemListRicetteConsigliate> cardRicette = new ArrayList<>();//creazione arraylist di ricette

        //aggiunta ricette
        cardRicette.add(new ItemListRicetteConsigliate("PASTA",R.drawable.pasta));
        cardRicette.add(new ItemListRicetteConsigliate("POLLO",R.drawable.pollo));
        cardRicette.add(new ItemListRicetteConsigliate("TIRA",R.drawable.tira));


        //setto le ricette nelle varie recycler
        RecyclerView rw = view.findViewById(R.id.rec_view);
        rw.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw.setAdapter(new AdapterRicetteConsigliate(cardRicette,getContext(),getFragmentManager()));

        RecyclerView rw2 = view.findViewById(R.id.rec_view2);
        rw2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw2.setAdapter(new AdapterRicetteConsigliate(cardRicette,getContext(), getFragmentManager()));

        RecyclerView rw3 = view.findViewById(R.id.rec_view3);
        rw3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw3.setAdapter(new AdapterRicetteConsigliate(cardRicette,getContext(), getFragmentManager()));

        RecyclerView rw4 = view.findViewById(R.id.rec_view4);
        rw4.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw4.setAdapter(new AdapterRicetteConsigliate(cardRicette,getContext(), getFragmentManager()));

        RecyclerView rw5 = view.findViewById(R.id.rec_view5);
        rw5.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw5.setAdapter(new AdapterRicetteConsigliate(cardRicette,getContext(), getFragmentManager()));

        //gestione Floating Button
        Floatingbtn = view.findViewById(R.id.FloatingBtn);//FBA principale
        FBAsearch = view.findViewById(R.id.FBAsearch);//FBA per la ricerca
        FBAadd = view.findViewById(R.id.FBAadd);//FBA per la creazione di una ricetta
        FBAfavourite = view.findViewById(R.id.FBAfavourite);//FBA per visualizzare le ricette preferite

        relativeLayout = view.findViewById(R.id.frame);//?????????????????????????????????????????????????????

        //didascalie dei FBA
        TextFABSearch = view.findViewById(R.id.TextFABSearch);
        TextFABAdd = view.findViewById(R.id.TextFABRecipe);
        TextFABFavourite = view.findViewById(R.id.TextFABFavourite);

        //animazione FAB, apparizione dei sottoFBA
        final Animation fadein = new AlphaAnimation(0.0f, 1.0f);
        fadein.setDuration(650);

        //animazione FAB, chiusura dei sottoFBA
        final Animation fadeout = new AlphaAnimation(1.0f, 0.0f);
        fadeout.setDuration(500);

        final Animation rightToleft = AnimationUtils.loadAnimation(this.getContext(), R.anim.righttoleft);//animazione didascalie


        Floatingbtn.setOnClickListener(new View.OnClickListener() {//apparizione e sparizione dei sottoFBA, al click del FBA principale
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

        //??????????????????????????????????????????????????????????????????????????????
       relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FAB(fadeout);
            }
        });


        editTextSearch = view.findViewById(R.id.editTextSearch);

        FBAsearch.setOnClickListener(new View.OnClickListener() {//FBA per ricerca
            @Override
            public void onClick(View v) {

                editTextSearch.setVisibility(View.VISIBLE);
                //apparizione tastiera
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editTextSearch , InputMethodManager.SHOW_IMPLICIT);

            }

        });

        FBAadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//FBA aggiunta ricetta

                CreateRecipeFragment fragmentCreate=new CreateRecipeFragment();//apertura fragment per creazione ricetta

                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fram,fragmentCreate,"CreateRecipeFragment");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        FBAfavourite.setOnClickListener(new View.OnClickListener() {//FBA preferiti ??????????????????????????????
            @Override
            public void onClick(View v) {

            }
        });



    return  view;

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

    @Override
    public void onClick(View v) {
        String name;
        switch (v.getId()){
            case R.id.catAntipasti:
                name="Antipasti";
                openFrag(name);
                break;

            case R.id.catPrimi:
                name="Primi";
                openFrag(name);
                break;

            case R.id.catSecondi:
                name="Secondi";
                openFrag(name);
                break;

            case R.id.catDolci:
                name="Dolci";
                openFrag(name);
                break;

            case R.id.catSpuntini:
                name="Spuntini";
                openFrag(name);
                break;
        }


    }

    private void openFrag(String id) {
        CategoryRecipeFragment fragmentCategory=new CategoryRecipeFragment();

        Bundle b = new Bundle();
        b.putString("cat",id);
        fragmentCategory.setArguments(b);

        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram,fragmentCategory,"CreateRecipeFragment");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

