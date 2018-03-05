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

import com.example.itsadmin.smartfridge_fragment.Main.Adapters.AdapterList;
import com.example.itsadmin.smartfridge_fragment.Main.Adapters.AdapterRicetteCategory;
import com.example.itsadmin.smartfridge_fragment.Main.Adapters.AdapterRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.Main.Items.ItemListRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.Main.Items.ListItem;
import com.example.itsadmin.smartfridge_fragment.Main.MainActivity;
import com.example.itsadmin.smartfridge_fragment.Models.Alimento;
import com.example.itsadmin.smartfridge_fragment.Models.Ricetta;
import com.example.itsadmin.smartfridge_fragment.R;
import com.example.itsadmin.smartfridge_fragment.Singleton.RetrofitService;
import com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI.AlimentiAPI;
import com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI.RicetteAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


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

    ArrayList<Ricetta> listRicette;
    ArrayList<ItemListRicetteConsigliate> cardRicette = new ArrayList<>();
    AdapterRicetteConsigliate adapterRicette;

    ArrayList <Ricetta> antipasti;
    ArrayList <Ricetta> primi;
    ArrayList <Ricetta> secondi;
    ArrayList <Ricetta> dolci;
    ArrayList <Ricetta> spuntini;

    AdapterRicetteConsigliate adapterAntipasti;
    AdapterRicetteConsigliate adapterPrimi;
    AdapterRicetteConsigliate adapterSecondi;
    AdapterRicetteConsigliate adapterDolci;
    AdapterRicetteConsigliate adapterSpuntini;

    ArrayList <Ricetta> aus;

    public RecipeFragment() {}//costruttore

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

        antipasti = new ArrayList<>();
        primi = new ArrayList<>();
        secondi = new ArrayList<>();
        dolci = new ArrayList<>();
        spuntini = new ArrayList<>();

        adapterAntipasti = new AdapterRicetteConsigliate(antipasti, getActivity(),getFragmentManager());
        adapterPrimi = new AdapterRicetteConsigliate(primi, getActivity(),getFragmentManager());
        adapterSecondi = new AdapterRicetteConsigliate(secondi, getActivity(),getFragmentManager());
        adapterDolci = new AdapterRicetteConsigliate(dolci, getActivity(),getFragmentManager());
        adapterSpuntini = new AdapterRicetteConsigliate(spuntini, getActivity(),getFragmentManager());

        //setto le ricette nelle varie recycler
        RecyclerView rw = view.findViewById(R.id.rec_view);
        rw.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw.setAdapter(adapterAntipasti);

        RecyclerView rw2 = view.findViewById(R.id.rec_view2);
        rw2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw2.setAdapter(adapterPrimi);

        RecyclerView rw3 = view.findViewById(R.id.rec_view3);
        rw3.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw3.setAdapter(adapterSecondi);

        RecyclerView rw4 = view.findViewById(R.id.rec_view4);
        rw4.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw4.setAdapter(adapterDolci);

        RecyclerView rw5 = view.findViewById(R.id.rec_view5);
        rw5.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rw5.setAdapter(adapterSpuntini);

        executeRicetteService();

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

    public void executeRicetteService() {

        RicetteAPI ricetteAPI=RetrofitService.getInstance().getRetrofit().create(RicetteAPI.class);

        // get antipasti

        Map<String,Object> map = new HashMap<>();
        map.put("categoria","antipasto");
        map.put("limite",5);

        Call<ArrayList<Ricetta>> call=ricetteAPI.getRicetteCat(map);

        call.enqueue(new Callback<ArrayList<Ricetta>>() {
            @Override
            public void onResponse(Call<ArrayList<Ricetta>> call, Response<ArrayList<Ricetta>> response) {

                aus = new ArrayList<>();
                aus = response.body();

                for(Ricetta i : aus) {

                    antipasti.add(new Ricetta(i));
                }

                System.out.println("RESPONSE");

                adapterAntipasti.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Ricetta>> call, Throwable t) {

                System.out.println("FAIL soos");
            }
        });

        // get primi

        map = new HashMap<>();
        map.put("categoria","primo");
        map.put("limite",5);

        call=ricetteAPI.getRicetteCat(map);

        call.enqueue(new Callback<ArrayList<Ricetta>>() {
            @Override
            public void onResponse(Call<ArrayList<Ricetta>> call, Response<ArrayList<Ricetta>> response) {

                aus = new ArrayList<>();
                aus = response.body();

                for(Ricetta i : aus) {

                    primi.add(new Ricetta(i));
                }


                System.out.println("RESPONSE");
                adapterPrimi.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Ricetta>> call, Throwable t) {

                System.out.println("FAIL soos");
            }
        });

        // get secondi

        map = new HashMap<>();
        map.put("categoria","secondo");
        map.put("limite",5);

        call=ricetteAPI.getRicetteCat(map);

        call.enqueue(new Callback<ArrayList<Ricetta>>() {
            @Override
            public void onResponse(Call<ArrayList<Ricetta>> call, Response<ArrayList<Ricetta>> response) {

                aus = new ArrayList<>();
                aus = response.body();

                for(Ricetta i : aus) {

                    secondi.add(new Ricetta(i));
                }


                System.out.println("RESPONSE");
                adapterSecondi.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Ricetta>> call, Throwable t) {

                System.out.println("FAIL soos");
            }
        });

        // get dolci

        map = new HashMap<>();
        map.put("categoria","dolce");
        map.put("limite",5);

        call=ricetteAPI.getRicetteCat(map);

        call.enqueue(new Callback<ArrayList<Ricetta>>() {
            @Override
            public void onResponse(Call<ArrayList<Ricetta>> call, Response<ArrayList<Ricetta>> response) {

                aus = new ArrayList<>();
                aus = response.body();

                for(Ricetta i : aus) {

                    dolci.add(new Ricetta(i));
                }


                System.out.println("RESPONSE");
                adapterDolci.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Ricetta>> call, Throwable t) {

                System.out.println("FAIL soos");
            }
        });

        // get spuntini

        map = new HashMap<>();
        map.put("categoria","spuntino");
        map.put("limite",5);

        call=ricetteAPI.getRicetteCat(map);

        call.enqueue(new Callback<ArrayList<Ricetta>>() {
            @Override
            public void onResponse(Call<ArrayList<Ricetta>> call, Response<ArrayList<Ricetta>> response) {

                aus = new ArrayList<>();
                aus = response.body();

                for(Ricetta i : aus) {

                    spuntini.add(new Ricetta(i));
                }


                System.out.println("RESPONSE");
                adapterSpuntini.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ArrayList<Ricetta>> call, Throwable t) {

                System.out.println("FAIL soos");
            }
        });
    }




    private void convertiRicette() {

        cardRicette.clear();

        for (Ricetta i : listRicette) {

            cardRicette.add(new ItemListRicetteConsigliate(i.getNome(), R.drawable.pollo));
        }
    }

    private void aggiorna() {


        convertiRicette();

        adapterRicette.notifyDataSetChanged();
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

    @Override
    public void onResume() {
        super.onResume();

    }


}

