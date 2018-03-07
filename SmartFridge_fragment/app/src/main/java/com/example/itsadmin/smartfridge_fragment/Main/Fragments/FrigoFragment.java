package com.example.itsadmin.smartfridge_fragment.Main.Fragments;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Adapters.AdapterList;
import com.example.itsadmin.smartfridge_fragment.Main.Items.ListItem;
import com.example.itsadmin.smartfridge_fragment.Main.MainActivity;
import com.example.itsadmin.smartfridge_fragment.Models.Alimento;
import com.example.itsadmin.smartfridge_fragment.R;
import com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI.AlimentiAPI;
import com.example.itsadmin.smartfridge_fragment.SmartFridgeAPI.PingAPI;
import com.example.itsadmin.smartfridge_fragment.Singleton.RetrofitService;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FrigoFragment extends Fragment {

    ArrayList<ListItem> list;
    ArrayList<Alimento> listAlimenti;

    GridView gw;
    AdapterList adapterAlimenti;


    public FrigoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_frigo, container, false);

        list = new ArrayList<>();
        listAlimenti = new ArrayList<>();

        // si riempie l'array list alimenti (listAlimenti) con gli alimenti con retrofit

        // con listAlimenti si costruisce l'arrayList di listItem (ila)

        gw = (GridView) view.findViewById(R.id.gw);
        adapterAlimenti = new AdapterList(getActivity(), list);

        gw.setAdapter(adapterAlimenti);

        gw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                final RelativeLayout back = (RelativeLayout) view.findViewById(R.id.back);
                final ImageView ima = (ImageView) view.findViewById(R.id.imageF);
                final TextView txt = (TextView) view.findViewById(R.id.nomeF);

                //animazione card alimento

                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa1.setDuration(350);
                oa2.setDuration(350);
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());
                oa1.addListener(new AnimatorListenerAdapter() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        if (!list.get(position).isFlag()) {
                            //retro
                            list.get(position).setFlag(true);
                            back.setVisibility(View.VISIBLE);
                            ima.setVisibility(View.INVISIBLE);
                            txt.setVisibility(View.INVISIBLE);
                            oa2.start();

                        } else {
                            //fronte
                            list.get(position).setFlag(false);
                            back.setVisibility(View.INVISIBLE);
                            ima.setVisibility(View.VISIBLE);
                            txt.setVisibility(View.VISIBLE);
                            oa2.start();
                        }
                    }
                });
                oa1.start();
            }


        });

        executeAlimentiService();
        //executeAlimentoService();
        //executePing();

        return view;
    }

    public void executeAlimentiService() {

        AlimentiAPI colorService = RetrofitService.getInstance().getRetrofit().create(AlimentiAPI.class);

        Call<ArrayList<Alimento>> call = colorService.getAllAlimenti();

        call.enqueue(new Callback<ArrayList<Alimento>>() {
            @Override
            public void onResponse(Call<ArrayList<Alimento>> call, Response<ArrayList<Alimento>> response) {

                listAlimenti = response.body();

                for(Alimento a : listAlimenti)

                    System.out.println(a.toString());


                System.out.println("RESPONSE");
                aggiorna();
            }

            @Override
            public void onFailure(Call<ArrayList<Alimento>> call, Throwable t) {

                System.out.println("FAIL");
            }
        });
    }


    public void executeAlimentoService() {

        AlimentiAPI colorService = RetrofitService.getInstance().getRetrofit().create(AlimentiAPI.class);

        Call<Alimento> call = colorService.getAlimento();

        call.enqueue(new Callback<Alimento>() {
            @Override
            public void onResponse(Call<Alimento> call, Response<Alimento> response) {

                System.out.println(new Alimento(response.body()).toString());
                System.out.println("RESPONSE");
            }

            @Override
            public void onFailure(Call<Alimento> call, Throwable t) {

                System.out.println("FAIL");
            }
        });
    }

    public void executePing (){

        PingAPI colorService = RetrofitService.getInstance().getRetrofit().create(PingAPI.class);

        Call<ResponseBody> call = colorService.ping();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                System.out.println("RESPONSE");
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                System.out.println("FAIL");
            }
        });

    }

    private void convertiAlimenti() {

        list.clear();

        for (Alimento a : listAlimenti) {

            list.add(new ListItem(a.getNome(), a.getQuantita(), a.getScadenza(), R.drawable.pollo));
        }
    }

    private void aggiorna() {


        convertiAlimenti();

        adapterAlimenti.notifyDataSetChanged();
    }

}
