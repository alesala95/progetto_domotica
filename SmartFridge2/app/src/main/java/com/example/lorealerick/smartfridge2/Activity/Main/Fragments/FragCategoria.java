package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;

import com.example.lorealerick.smartfridge2.Activity.Main.Adapters.AdapterGrigliaRicette;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;

import java.util.ArrayList;

/**
 * Created by itsadmin on 12/03/2018.
 */

public class FragCategoria extends Fragment {

    ArrayList <Ricetta> listaRicette;
    GridView grigliaRicetteCategoria;
    AdapterGrigliaRicette adapterGrigliaRicette;

    boolean isScrolling;
    int itemCorrenti;
    int itemTotali;
    int itemScrollati;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         View view = inflater.inflate(R.layout.frag_home, container, false);

         grigliaRicetteCategoria = view.findViewById(R.id.grigliaRicetteCategoria);
         adapterGrigliaRicette = new AdapterGrigliaRicette(getActivity(),R.layout.item_ricetta,listaRicette);
         isScrolling = false;

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

                 if(isScrolling && (itemCorrenti + itemScrollati == itemTotali)){

                     isScrolling = false;
                     // fetch
                 }

             }
         });


         return view;
    }



    private void clearDataSet (){

        listaRicette.clear();
        adapterGrigliaRicette.notifyDataSetChanged();
    }

    private void notifyDataChanged (){

        adapterGrigliaRicette.notifyDataSetChanged();
    }


}
