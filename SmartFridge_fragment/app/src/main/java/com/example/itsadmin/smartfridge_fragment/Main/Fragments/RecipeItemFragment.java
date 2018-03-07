package com.example.itsadmin.smartfridge_fragment.Main.Fragments;


import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itsadmin.smartfridge_fragment.Models.Ricetta;
import com.example.itsadmin.smartfridge_fragment.R;
import com.example.itsadmin.smartfridge_fragment.Singleton.RetrofitService;
import com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI.RicetteAPI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeItemFragment extends Fragment {

    //fragment singola ricetta

    FloatingActionButton Floatingbtn;
    FloatingActionButton FBAadd;
    FloatingActionButton FBAfavourite;
    FloatingActionButton FBAShare;

    RelativeLayout relativeLayout;//????????????????????????????????????????????

    Button TextFABAdd;
    Button TextFABFavourite;
    Button TextFABShare;



    boolean flag = false;

    TextView nomeRicetta;
    TextView nomeUtente;
    TextView durataRicetta;

    TextView testoIngredienti;
    TextView testoProcedimento;

    RatingBar difficoltaRate;

    ImageView imgRic;

    public RecipeItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_recipe_item, container, false);

        //RatingBar rate = view.findViewById(R.id.valutazionePersonale);//????????????????????????????????????????

        //manca la votazione

        int idR;
        idR = getArguments().getInt("id");

        System.out.println("ID: "+idR);

        // rerer

        difficoltaRate = view.findViewById(R.id.difficolta_rate);

        nomeRicetta = view.findViewById(R.id.nomeRicetta);
        nomeUtente = view.findViewById(R.id.nomeUtente);
        durataRicetta = view.findViewById(R.id.durata);

        testoIngredienti = view.findViewById(R.id.testoIngredienti);
        testoProcedimento = view.findViewById(R.id.testoProcedimento);

        imgRic = view.findViewById(R.id.imgRic);

        aggiornaUI(idR);


























        //Gestione FBA
        Floatingbtn = (FloatingActionButton) view.findViewById(R.id.FloatingBtn);

        FBAadd = (FloatingActionButton) view.findViewById(R.id.FBAadd);
        FBAfavourite = (FloatingActionButton) view.findViewById(R.id.FBAfavourite);
        FBAShare = (FloatingActionButton) view.findViewById(R.id.FBAshare);

        TextFABAdd = (Button) view.findViewById(R.id.TextFABRecipe);
        TextFABFavourite = (Button) view.findViewById(R.id.TextFABFavourite);
        TextFABShare = (Button) view.findViewById(R.id.TextFABShare);


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


                    FBAadd.startAnimation(fadein);
                    FBAadd.setVisibility(View.VISIBLE);
                    TextFABAdd.startAnimation(rightToleft);
                    TextFABAdd.setVisibility(View.VISIBLE);

                    FBAfavourite.startAnimation(fadein);
                    FBAfavourite.setVisibility(View.VISIBLE);
                    TextFABFavourite.startAnimation(rightToleft);
                    TextFABFavourite.setVisibility(View.VISIBLE);

                    FBAShare.startAnimation(fadein);
                    FBAShare.setVisibility(View.VISIBLE);
                    TextFABShare.startAnimation(rightToleft);
                    TextFABShare.setVisibility(View.VISIBLE);

                    flag = true;
                } else {

                    FAB(fadeout);
                }

            }
        });


        FBAShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                String shareBody="Ricetta";//mettere nome della ricetta, titolo
                String shareSub="Ingredienti,procedimento";//inserire ricetta, se possibile con foto
                intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                intent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(intent,"Condividi"));

            }
        });


        FBAfavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(),"Aggiunto ai preferiti",Toast.LENGTH_LONG).show();
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


        return view;
    }

    private void FAB(Animation fadeout) {


        FBAadd.startAnimation(fadeout);
        FBAadd.setVisibility(View.GONE);
        TextFABAdd.startAnimation(fadeout);
        TextFABAdd.setVisibility(View.GONE);

        FBAfavourite.startAnimation(fadeout);
        FBAfavourite.setVisibility(View.GONE);
        TextFABFavourite.startAnimation(fadeout);
        TextFABFavourite.setVisibility(View.GONE);

        FBAShare.startAnimation(fadeout);
        FBAShare.setVisibility(View.GONE);
        TextFABShare.startAnimation(fadeout);
        TextFABShare.setVisibility(View.GONE);

        Floatingbtn.setImageResource(R.mipmap.fab);


        flag = false;
    }

    private void aggiornaUI (int id){

        RicetteAPI ricetteAPI= RetrofitService.getInstance().getRetrofit().create(RicetteAPI.class);

        Map <String,Object> idm = new HashMap<>();

        idm.put("id",id);

        Call <ArrayList<Ricetta>> call=ricetteAPI.getRicetta(idm);

        call.enqueue(new Callback<ArrayList<Ricetta>>() {
            @Override
            public void onResponse(Call<ArrayList<Ricetta>> call, Response<ArrayList<Ricetta>> response) {

                ArrayList <Ricetta> arl = response.body();

                nomeRicetta.setText(arl.get(0).getNome());
                nomeUtente.setText(arl.get(0).getAutore());
                durataRicetta.setText(arl.get(0).getDurata());
                testoIngredienti.setText(arl.get(0).getIngredienti());
                testoProcedimento.setText(arl.get(0).getProcedimento());
                difficoltaRate.setRating(arl.get(0).getDifficolta());

                Picasso.get().load(RetrofitService.getInstance().getRetrofit().baseUrl()+""+arl.get(0).getUrlImage()).into(imgRic);

                System.out.println("Response");
            }

            @Override
            public void onFailure(Call<ArrayList<Ricetta>> call, Throwable t) {

                System.out.println("Failure");
                System.out.println(t.getCause());
                System.out.println(t.getMessage());
            }
        });

    }

}
