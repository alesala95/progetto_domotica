package com.example.itsadmin.smartfridge_fragment.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by itsadmin on 28/02/2018.
 */

public class DataBaseAdapterRecipe {
    private static final String LOG_TAG = DataBaseAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    // Database fields
    private static final String DATABASE_TABLE = "ricetta";

    public static final String KEY_ID = "_id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_IMG = "immagine_ricetta";
    public static final String KEY_DIF="difficoltà";
    public static final String KEY_DURATA="durata";
    public static final String KEY_PREF="preferita";


    public DataBaseAdapterRecipe(Context context) {
        this.context = context;

    }

    public DataBaseAdapterRecipe open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }


    private ContentValues createContentValues(String nome, String immagine_ricetta, String difficoltà, String durata, String preferita) {
        ContentValues values = new ContentValues();
        values.put( KEY_NOME, nome );
        values.put( KEY_IMG, immagine_ricetta );
        values.put( KEY_DIF, difficoltà );
        values.put( KEY_DURATA, durata );
        values.put( KEY_PREF, preferita );


        return values;
    }


    public void close() {
        dbHelper.close();
    }

    //create ricetta
    public long ricetta(String nome, String immagine_ricetta, String difficoltà, String durata, String preferita ) {
        ContentValues initialValues = createContentValues(nome, immagine_ricetta, difficoltà,durata, preferita);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update ricetta
    public boolean updateRicetta( long contactID, String nome, String immagine_ricetta, String difficoltà, String durata, String preferita) {
        ContentValues updateValues = createContentValues(nome, immagine_ricetta, difficoltà,durata, preferita);
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "=" + contactID, null) > 0;
    }


    //delete a ricetta
    public boolean deleteAlimento(long contactID) {
        return database.delete(DATABASE_TABLE, KEY_ID + "=" + contactID, null) > 0;
    }

    //fetch all ricette
    public Cursor fetchAllAlimenti() {
        return database.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_NOME, KEY_IMG, KEY_DIF, KEY_DURATA, KEY_PREF}, null, null, null, null, null);
    }




}
