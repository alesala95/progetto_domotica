package com.example.itsadmin.smartfridge_fragment.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by itsadmin on 27/02/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SmartFridge.db";
    private static final int DATABASE_VERSION = 1;

    // Lo statement SQL di creazione del database
    private static final String DATABASE_TABLE_ALIMENTO = "create table alimento (_id integer primary key autoincrement, nome text not null, immagine blob not null, data_scadenza text not null);";
    private static final String DATABASE_TABLE_RICETTA="CREATE TABLE ricetta ( _id INT primary key autoincrement, nome TEXT NOT NULL, immagine_ricetta` BLOB NOT NULL, difficoltà INT NOT NULL, durata` TEXT NOT NULL, preferita` INT NOT NULL);";


    // Costruttore
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Questo metodo viene chiamato durante la creazione del database
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_TABLE_ALIMENTO);
        database.execSQL(DATABASE_TABLE_RICETTA);
    }

    // Questo metodo viene chiamato durante l'upgrade del database, ad esempio quando viene incrementato il numero di versione
    @Override
    public void onUpgrade( SQLiteDatabase database, int oldVersion, int newVersion ) {

        database.execSQL("DROP TABLE IF EXISTS contact");
        onCreate(database);

    }

}

