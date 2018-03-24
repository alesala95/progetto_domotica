package com.example.lorealerick.smartfridge2.Activity.Login.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.example.lorealerick.smartfridge2.Activity.Login.Interfaces.ListenerLogin;
import com.example.lorealerick.smartfridge2.Activity.Login.LoginActivity;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.UserControls;
import com.example.lorealerick.smartfridge2.Utils.UtenteCorrente;

/**
 * Created by itsadmin on 22/02/2018.
 */

public class FragmentBenvenuto extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout refreshLayout;
    private taskRicerca taskR;
    private SharedPreferences sharedPreferences;
    private ListenerLogin listenerLogin;

    public FragmentBenvenuto() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        sharedPreferences = context.getSharedPreferences("SmartFridge",Context.MODE_PRIVATE);
        listenerLogin = (LoginActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_benvenuto, container, false);

        refreshLayout = view.findViewById(R.id.refLayout);
        refreshLayout.setOnRefreshListener(this);

        taskR = new taskRicerca();
        taskR.execute();

        return view;
    }

    @Override
    public void onRefresh() {

        taskR.cancel(true);
        if (taskR.getStatus() != AsyncTask.Status.RUNNING){

            taskR = new taskRicerca();
            taskR.execute();
        }
    }

    private void frigoDialog(final NsdServiceInfo service){

        final AlertDialog.Builder miaAlert = new AlertDialog.Builder(getActivity());
        miaAlert.setTitle("Frigo trovato: "+service.getServiceName());

        miaAlert.setMessage("Scrivi sono gay per confermare che tu non sia un bot");
        miaAlert.setCancelable(false);

        miaAlert.setView(R.layout.dialog_layout);
        miaAlert.setPositiveButton("Connetti", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if(UserControls.getCodiceFrigo((service.getHost()+"").substring(1))){

                    sharedPreferences.edit().putString("codiceFrigo", UtenteCorrente.getInstance().getCodiceFrigo()).apply();
                    listenerLogin.cambiaFragment(-1);
                }else{

                    Toast.makeText(getActivity(),"Impossibile connettersi",Toast.LENGTH_SHORT).show();
                }
            }
        });
        miaAlert.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Toast.makeText(getActivity(),"Connessione annullata",Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alert = miaAlert.create();
        alert.show();
    }

    private class taskRicerca extends AsyncTask<Void, Void, Void> {

        private NsdManager.DiscoveryListener mDiscoveryListener;
        private NsdManager.ResolveListener mResolveListener;
        private final String mServiceName = "SmartFridge";
        private NsdServiceInfo services = null;

        private NsdManager mNsdManager = (NsdManager) getActivity().getSystemService(Context.NSD_SERVICE);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            refreshLayout.setRefreshing(true);
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

            mNsdManager.stopServiceDiscovery(mDiscoveryListener);

            if(services != null){

                frigoDialog(services);
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();

            if(refreshLayout.isRefreshing())
                refreshLayout.setRefreshing(false);

            mNsdManager.stopServiceDiscovery(mDiscoveryListener);
        }

        public void initializeResolveListener(){

            mResolveListener = new NsdManager.ResolveListener() {
                @Override
                public void onResolveFailed(NsdServiceInfo nsdServiceInfo, int i) {

                }

                @Override
                public void onServiceResolved(NsdServiceInfo nsdServiceInfo) {

                    services = nsdServiceInfo;
                    System.out.println("Risolto");
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
