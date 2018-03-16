package com.example.lorealerick.smartfridge2.Splash;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.lorealerick.*;
import com.example.lorealerick.smartfridge2.Activity.Main.MainActivity;
import com.example.lorealerick.smartfridge2.Database.DatabaseAdapter;
import com.example.lorealerick.smartfridge2.Login.LoginActivity;
import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Frigo;
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;

import java.util.ArrayList;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.BlueColor));//cambia colore navigation bar

        new DownloadResources().execute();
    }

    private class DownloadResources extends AsyncTask <Void, Void, Void>{

        DatabaseAdapter databaseAdapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            databaseAdapter = new DatabaseAdapter(SplashActivity.this);

            databaseAdapter.svuotaTabellaAlimenti();
            databaseAdapter.svuotaTabellaRicette();
            databaseAdapter.svuotaTabellaFrigo();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList <Alimento> alimenti = DownloadDati.scaricaAlimenti();
            ArrayList <Ricetta> ricette = DownloadDati.scaricaVetrinaRicette();
            Frigo frigo = DownloadDati.scaricaInfoFrigo();

            for(Alimento a : alimenti)

                databaseAdapter.addAlimento(a);

            for(Ricetta r : ricette)

                databaseAdapter.addRicetta(r);

            databaseAdapter.addFrigo(frigo);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            goToMain();
        }
    }

    private void goToMain (){

        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }
}
