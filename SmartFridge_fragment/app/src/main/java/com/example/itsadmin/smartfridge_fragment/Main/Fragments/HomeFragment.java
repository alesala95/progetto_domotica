package com.example.itsadmin.smartfridge_fragment.Main.Fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Adapters.AdapterRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.Main.Adapters.AdapterScadenze;
import com.example.itsadmin.smartfridge_fragment.Main.Items.ItemListRicetteConsigliate;
import com.example.itsadmin.smartfridge_fragment.Main.Items.ItemListaScadenze;
import com.example.itsadmin.smartfridge_fragment.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    CardView targetStatusContainer;
    TextView temperaturaTarget;
    ImageView iconaTarget;
    TextView target;
    TextView targetStatus;

    boolean flag = false;

    public HomeFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // lista alimenti in scadenza
        ArrayList <ItemListaScadenze> ila = new ArrayList<>();

        RecyclerView rw = (RecyclerView) view.findViewById(R.id.rec_view);
        rw.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        ila.add(new ItemListaScadenze("Mela",R.drawable.mela));
        ila.add(new ItemListaScadenze("Banana",R.drawable.banana));
        ila.add(new ItemListaScadenze("adas",R.drawable.snowwwww));

        rw.setAdapter(new AdapterScadenze(ila));

        // lista ricette consigliate
        ArrayList<ItemListRicetteConsigliate> cardRicette = new ArrayList<>();

        RecyclerView rw2 = (RecyclerView) view.findViewById(R.id.rec_view2);
        rw2.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));

        cardRicette.add(new ItemListRicetteConsigliate("Pasta",R.drawable.pasta));
        cardRicette.add(new ItemListRicetteConsigliate("Pollo",R.drawable.pollo));
        cardRicette.add(new ItemListRicetteConsigliate("Tiramisù",R.drawable.tira));

        rw2.setAdapter(new AdapterRicetteConsigliate(cardRicette,getContext(), getFragmentManager()));

        // card temperatura frigo/freezer
        targetStatusContainer = view.findViewById(R.id.targetStatusContainer);
        temperaturaTarget = view.findViewById(R.id.temperaturaTarget);
        iconaTarget = view.findViewById(R.id.iconaTarget);
        target = view.findViewById(R.id.target);
        targetStatus = view.findViewById(R.id.statusTarget);

        // query per controllare lo stato
        target.setText("Frigo");

        targetStatusContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(targetStatusContainer, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(targetStatusContainer, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa1.setDuration(350);
                oa2.setDuration(350);
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                oa1.addListener(new AnimatorListenerAdapter() {

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (!flag) {

                            temperaturaTarget.setText("Temperatura: -3°");
                            iconaTarget.setImageResource(R.drawable.snowwwww);
                            target.setText("Freezer");
                            oa2.start();
                            flag=true;
                        }else {

                            temperaturaTarget.setText("Temperatura: 3°");
                            iconaTarget.setImageResource(R.drawable.drop);
                            target.setText("Frigo");
                            oa2.start();
                            flag=false;
                        }
                    }
                });
                oa1.start();
            }
        });

        return view;
    }
}
