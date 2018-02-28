package com.example.itsadmin.smartfridge_fragment.Login.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itsadmin.smartfridge_fragment.Login.Fragments.LoginFragment;
import com.example.itsadmin.smartfridge_fragment.R;

/**
 * Created by itsadmin on 22/02/2018.
 */

public class FragmentBenvenuto extends Fragment implements SwipeRefreshLayout.OnRefreshListener{//per refresh della pagina

    //Fragment per ricerca e connessione al frigo

    SwipeRefreshLayout refreshLayout;
    RecyclerView recSwipe;
    taskRicerca taskR;
    LoginFragment.FragmentLoginListener listener;

    public FragmentBenvenuto() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        listener = (LoginFragment.FragmentLoginListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_benvenuto, container, false);

        recSwipe = view.findViewById(R.id.recSwipe);

        refreshLayout = view.findViewById(R.id.refLayout);
        refreshLayout.setOnRefreshListener(this);

        taskR = new taskRicerca();
        taskR.execute();

        return view;
    }

    @Override
    public void onRefresh() {

        taskR.cancel(true);

        taskR = new taskRicerca();
        taskR.execute();
    }

    private void frigoDialog(String nome){ //dialog usato per l'inserimento della password per connettersi al frigo


        final AlertDialog.Builder miaAlert = new AlertDialog.Builder(getActivity());
        miaAlert.setTitle("Frigo trovato: "+nome);
        miaAlert.setMessage("Inserisci il codice che trovi dietro al frigo");
        miaAlert.setCancelable(false);

        miaAlert.setView(R.layout.dialog_layout);
        miaAlert.setPositiveButton("Connetti", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                // retrofit + pingTask
                // se è giusto
                listener.cambia(-1);

            }
        });
        miaAlert.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog alert = miaAlert.create();
        alert.show();
    }

    private class taskRicerca extends AsyncTask<Void, Void, Void> {

        NsdManager.DiscoveryListener mDiscoveryListener;
        NsdManager.ResolveListener mResolveListener;
        private final String mServiceName = "Frigo";

        String nome = "Martina";

        NsdManager mNsdManager = (NsdManager) getActivity().getSystemService(Context.NSD_SERVICE);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            initializeDiscoveryListener();
            initializeResolveListener();

            long timeout = System.currentTimeMillis() + 5000;
            boolean stop = false;

            mNsdManager.discoverServices("_http._tcp", NsdManager.PROTOCOL_DNS_SD, mDiscoveryListener);

            while (!stop&&!isCancelled()){

                if(timeout <= System.currentTimeMillis()){

                    stop = true;
                }
            }

            mNsdManager.stopServiceDiscovery(mDiscoveryListener);

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected void onPostExecute(Void v) {
            super.onPostExecute(v);

            if(refreshLayout.isRefreshing())
                refreshLayout.setRefreshing(false);

            frigoDialog(nome);
        }

        public void initializeResolveListener(){

            mResolveListener = new NsdManager.ResolveListener() {
                @Override
                public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {

                }

                @Override
                public void onServiceResolved(NsdServiceInfo nsdServiceInfo) {

                    //ac.add(new mConnessione(nsdServiceInfo.getServiceName(),(nsdServiceInfo.getHost()+"").substring(1),nsdServiceInfo.getHost()));
                    nome = nsdServiceInfo.getServiceName();
                }
            };
        }

        public void initializeDiscoveryListener() {

            // Instantiate a new DiscoveryListener
            mDiscoveryListener = new NsdManager.DiscoveryListener() {

                // Called as soon as service discovery begins.
                @Override
                public void onDiscoveryStarted(String regType) {


                }

                @Override
                public void onServiceFound(NsdServiceInfo service) {
                    // A service was found! Do something with it.

                    if (service.getServiceName().contains(mServiceName))
                        mNsdManager.resolveService(service,mResolveListener);
                }

                @Override
                public void onServiceLost(NsdServiceInfo service) {
                    // When the network service is no longer available.
                    // Internal bookkeeping code goes here.
                }

                @Override
                public void onDiscoveryStopped(String serviceType) {

                }

                @Override
                public void onStartDiscoveryFailed(String serviceType, int errorCode) {

                    mNsdManager.stopServiceDiscovery(this);
                }

                @Override
                public void onStopDiscoveryFailed(String serviceType, int errorCode) {

                    mNsdManager.stopServiceDiscovery(this);
                }
            };
        }
    }
}
