package com.example.lorealerick.smartfridge2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "contactsManager";

    public static final String TABLE_CONTACTS = "contacts";

    public static final String TABELLA_RICETTA = "ricetta";

    public static final String KEY_CONTACT_ID = "_id";
    public static final String KEY_CONTACT_NAME = "name";
    public static final String KEY_CONTACT_PHONE = "phone_number";

    public static final String KEY_RICETTA_ID = "_idRicetta";
    public static final String KEY_RICETTA_NOME = "nomeRicetta";
    public static final String KEY_RICETTA_AUTORE = "autoreRicetta";
    public static final String KEY_RICETTA_DURATA = "durataRicetta";
    public static final String KEY_RICETTA_DIFFICOLTA = "difficoltaRicetta";
    public static final String KEY_RICETTA_INGREDIENTI = "ingredientiRicetta";
    public static final String KEY_RICETTA_PROCEDIMENTO = "procedimentoRicetta";
    public static final String KEY_RICETTA_CATEGORIA = "categoriaRicetta";

    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + KEY_CONTACT_ID + " INTEGER PRIMARY KEY," + KEY_CONTACT_NAME + " TEXT,"
            + KEY_CONTACT_PHONE + " TEXT" + ")";

    private static final String CREAZIONE_TABELLA_RICETTA = "CREATE TABLE " + TABELLA_RICETTA + " ("
            +" "+ KEY_RICETTA_ID + " INTEGER PRIMARY KEY,"
            + KEY_RICETTA_NOME + " TEXT,"
            + KEY_RICETTA_AUTORE + " TEXT,"
            + KEY_RICETTA_DURATA + " TEXT,"
            + KEY_RICETTA_DIFFICOLTA + " INTEGER,"
            + KEY_RICETTA_INGREDIENTI + " TEXT,"
            + KEY_RICETTA_CATEGORIA + " TEXT,"
            + KEY_RICETTA_PROCEDIMENTO + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAZIONE_TABELLA_RICETTA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELLA_RICETTA);

        onCreate(db);
    }
}
