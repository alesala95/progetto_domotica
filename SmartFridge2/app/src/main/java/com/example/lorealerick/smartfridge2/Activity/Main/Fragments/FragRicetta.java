package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.BitmapHandle;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;
import com.example.lorealerick.smartfridge2.Utils.UtilsAnimation;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragRicetta extends Fragment implements View.OnClickListener {

    private DatabaseAdapter dbAdapter;
    private TextView nomeRicetta;
    private TextView autoreRicetta;
    private TextView durataRicetta;
    private TextView difficoltaRicetta;
    private TextView ingredienti;
    private TextView procedimento;
    private ImageView immagine;

    private ListenerApriRicetta listenerApriRicetta;
    private ListenerRefreshUI listenerRefreshUI;

    private RelativeLayout content;
    private Ricetta ricetta;

    FloatingActionButton Floatingbtn;
    FloatingActionButton FABadd;
    FloatingActionButton FABfavourite;
    FloatingActionButton FABShare;
    boolean flag = false;
    final Animation fadein = new AlphaAnimation(0.0f, 1.0f);
    final Animation fadeout = new AlphaAnimation(1.0f, 0.0f);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        dbAdapter = new DatabaseAdapter(context);
        listenerRefreshUI = (MainActivity)context;
        listenerApriRicetta = (MainActivity)context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricetta, container, false);

        content = view.findViewById(R.id.content);

        nomeRicetta = view.findViewById(R.id.titoloRicetta);
        autoreRicetta = view.findViewById(R.id.autoreRicetta);
        durataRicetta = view.findViewById(R.id.durataRicetta);
        difficoltaRicetta = view.findViewById(R.id.difficoltaRicetta);
        ingredienti = view.findViewById(R.id.testoIngredienti);
        procedimento = view.findViewById(R.id.testoProcedimento);

        immagine = view.findViewById(R.id.immagine);

        new DownloadDettagliRicetta().execute(getArguments().getInt("id"));

        Floatingbtn = (FloatingActionButton) view.findViewById(R.id.FloatingBtn);
        FABadd = (FloatingActionButton) view.findViewById(R.id.FBAadd);
        FABfavourite = (FloatingActionButton) view.findViewById(R.id.FBAfavourite);
        FABShare = (FloatingActionButton) view.findViewById(R.id.FBAshare);

        fadein.setDuration(650);
        fadeout.setDuration(500);

        Floatingbtn.setOnClickListener(this);
        FABadd.setOnClickListener(this);
        FABfavourite.setOnClickListener(this);
        FABShare.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.FloatingBtn:
                if(!flag)
                    FABshow();
                else
                    FABhide();
                break;

            case R.id.FBAadd:
                listenerApriRicetta.apriCreateRicetta();
                break;

            case R.id.FBAfavourite:
                break;

            case R.id.FBAshare:
                share();
                break;
        }
    }

    private void share() {
        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain/image");
        String shareBody= (String) nomeRicetta.getText();
        shareBody= shareBody.concat("\n").concat((String) ingredienti.getText()).concat("\n").concat((String) procedimento.getText());
        String shareSub="Ingredienti,procedimento";
        intent.putExtra(Intent.EXTRA_STREAM,ricetta.getImage());
        intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
        intent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(intent,"Condividi"));
    }

    private void FABhide() {
        FABShare.startAnimation(fadeout);
        FABShare.setVisibility(View.GONE);


        FABadd.startAnimation(fadeout);
        FABadd.setVisibility(View.GONE);

        FABfavourite.startAnimation(fadeout);
        FABfavourite.setVisibility(View.GONE);

        Floatingbtn.setImageResource(R.mipmap.fab);

        flag=false;
    }

    private void FABshow() {
        Floatingbtn.setImageResource(R.mipmap.close);

        FABShare.startAnimation(fadein);
        FABShare.setVisibility(View.VISIBLE);

        FABadd.startAnimation(fadein);
        FABadd.setVisibility(View.VISIBLE);

        FABfavourite.startAnimation(fadein);
        FABfavourite.setVisibility(View.VISIBLE);

        flag=true;
    }


    private class DownloadDettagliRicetta extends AsyncTask <Integer,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            content.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Integer... ints) {

            ricetta = DownloadDati.scaricaRicetta(ints[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            aggiorna(ricetta);

            content.setVisibility(View.VISIBLE);
            UtilsAnimation.startFadeInAnimation(content,getActivity());
        }
    }

    private void aggiorna (Ricetta ricetta){

        nomeRicetta.setText(ricetta.getNome());
        autoreRicetta.setText(ricetta.getAutore());
        durataRicetta.setText("Durata: " + ricetta.getDurata());

        switch (ricetta.getDifficolta()){

            case 1:

                difficoltaRicetta.setText("Facile");
                break;

            case 2:

                difficoltaRicetta.setText("Media");
                break;

            case 3:

                difficoltaRicetta.setText("Difficile");
                break;
        }

        ingredienti.setText(ricetta.getIngredienti());
        procedimento.setText(ricetta.getProcedimento());
        immagine.setImageBitmap(BitmapHandle.getBitmap(ricetta.getImage()));
        listenerRefreshUI.onRefreshUI("Ricetta",ricetta.getNome());
    }

    @Override
    public void onResume() {
        super.onResume();

        if(ricetta!=null)
            listenerRefreshUI.onRefreshUI("Ricetta",ricetta.getNome());
    }


}
