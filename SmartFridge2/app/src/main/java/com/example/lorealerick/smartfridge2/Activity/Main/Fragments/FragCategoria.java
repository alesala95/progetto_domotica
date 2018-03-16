package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterGrigliaRicette;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerApriRicetta;
import com.example.lorealerick.smartfridge2.Activity.Main.Interfaces.ListenerRefreshUI;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;
import com.example.lorealerick.smartfridge2.Utils.UtilsTesto;

import java.util.ArrayList;

/**
 * Created by itsadmin on 12/03/2018.
 */

public class FragCategoria extends Fragment {

    private ArrayList <Ricetta> listaRicette;
    private GridView grigliaRicetteCategoria;
    private AdapterGrigliaRicette adapterGrigliaRicette;

    private boolean isScrolling;
    private boolean fineItem;
    private int itemCorrenti;
    private int itemTotali;
    private int itemScrollati;

    private String categoria;
    private FetchRicette fetchRicette;

    private int itemPerPagina;
    private int pagina;

    private ListenerRefreshUI listenerRefreshUI;
    private ListenerApriRicetta listenerApriRicetta;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listenerRefreshUI = (MainActivity)context;
        listenerApriRicetta = (MainActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.frag_categoria_ricette, container, false);

         itemPerPagina = 10;
         pagina = 0;
         categoria = getArguments().getString("category");
         listenerRefreshUI.onRefreshUI("Categoria",UtilsTesto.letteraMaiuscola(categoria));
         listaRicette = new ArrayList<>();

         grigliaRicetteCategoria = view.findViewById(R.id.grigliaRicetteCategoria);
         adapterGrigliaRicette = new AdapterGrigliaRicette(getActivity(),R.layout.item_ricetta,listaRicette);
         grigliaRicetteCategoria.setAdapter(adapterGrigliaRicette);
         grigliaRicetteCategoria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 listenerApriRicetta.apriRicetta(listaRicette.get(position).getId());
             }
         });

         isScrolling = false;
         fineItem = false;

         grigliaRicetteCategoria.setOnScrollListener(new AbsListView.OnScrollListener() {
             @Override
             public void onScrollStateChanged(AbsListView view, int scrollState) {

                 if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                     isScrolling = true;
             }

             @Override
             public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                 itemCorrenti = visibleItemCount;
                 itemScrollati = firstVisibleItem;
                 itemTotali = totalItemCount;

                 if(!fineItem){

                     if(isScrolling && (itemCorrenti + itemScrollati == itemTotali)){

                         isScrolling = false;
                         fetchRicette = new FetchRicette();
                         fetchRicette.execute(itemPerPagina,pagina);
                     }
                 }

             }
         });

         fetchRicette = new FetchRicette();
         fetchRicette.execute(itemPerPagina,pagina);

         return view;
    }

    class FetchRicette extends AsyncTask <Integer,Void,Void>{

        int limite;
        int page;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Integer... integers) {

            limite = integers [0];
            page = integers [1];
            page *= limite;

            System.out.print("Limite= "+limite);
            System.out.print("Pagina= "+page);
            System.out.print("Categoria= "+categoria);

            int cont = listaRicette.size();

            listaRicette.addAll(DownloadDati.getRicetteForCategoryOffset(categoria,limite,page));

            if(listaRicette.size()>cont)

                pagina ++;
            else
                fineItem = true;


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            notifyDataChanged();
        }
    }



    private void clearDataSet (){

        listaRicette.clear();
        adapterGrigliaRicette.notifyDataSetChanged();
    }

    private void notifyDataChanged (){

        adapterGrigliaRicette.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        listenerRefreshUI.onRefreshUI("Categoria", UtilsTesto.letteraMaiuscola(categoria));
    }
}
