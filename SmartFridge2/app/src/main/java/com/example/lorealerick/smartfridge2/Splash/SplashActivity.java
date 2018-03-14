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
import com.example.lorealerick.smartfridge2.Models.Ricetta;
import com.example.lorealerick.smartfridge2.R;
import com.example.lorealerick.smartfridge2.Utils.DownloadDati;

import java.util.ArrayList;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new DownloadResources().execute();
    }

    private class DownloadResources extends AsyncTask <Void, Void, Void>{

        DownloadDati downloadDati;
        DatabaseAdapter databaseAdapter;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            downloadDati = new DownloadDati(SplashActivity.this);
            databaseAdapter = new DatabaseAdapter(SplashActivity.this);

            databaseAdapter.svuotaTabellaAlimenti();
            databaseAdapter.svuotaTabellaRicette();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList <Alimento> alimenti = downloadDati.scaricaAlimenti();
            ArrayList <Ricetta> ricette = downloadDati.scaricaVetrinaRicette();

            for(Alimento a : alimenti)

                databaseAdapter.addAlimento(a);

            for(Ricetta r : ricette)

                databaseAdapter.addRicetta(r);

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
