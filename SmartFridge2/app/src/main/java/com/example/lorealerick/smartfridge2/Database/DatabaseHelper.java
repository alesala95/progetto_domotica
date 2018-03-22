package com.example.lorealerick.smartfridge2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "smartFridge";

    public static final String TABELLA_RICETTA = "ricetta";
    public static final String TABELLA_ALIMENTO = "alimento";
    public static final String TABELLA_FRIGO = "frigo";
    public static final String TABELLA_RICETTE_PREFERITE = "ricette_preferite";

    public static final String KEY_RICETTA_ID = "_idRicetta";
    public static final String KEY_RICETTA_NOME = "nomeRicetta";
    public static final String KEY_RICETTA_AUTORE = "autoreRicetta";
    public static final String KEY_RICETTA_DURATA = "durataRicetta";
    public static final String KEY_RICETTA_DIFFICOLTA = "difficoltaRicetta";
    public static final String KEY_RICETTA_INGREDIENTI = "ingredientiRicetta";
    public static final String KEY_RICETTA_PROCEDIMENTO = "procedimentoRicetta";
    public static final String KEY_RICETTA_CATEGORIA = "categoriaRicetta";
    public static final String KEY_RICETTA_IMMAGINE = "immagineRicetta";

    public static final String KEY_ALIMENTO_ID_RECORD = "_id";
    public static final String KEY_ALIMENTO_ID = "_idAlimento";
    public static final String KEY_ALIMENTO_NOME = "nomeAlimento";
    public static final String KEY_ALIMENTO_DATA_INSERIMENTO = "dataInserimento";
    public static final String KEY_ALIMENTO_STIMA_SCADENZA = "stimaScadenzaAlimento";
    public static final String KEY_ALIMENTO_IMMAGINE = "immagineAlimento";

    public static final String KEY_FRIGO_ID = "_id";
    public static final String KEY_FRIGO_MODELLO = "modelloFrigo";
    public static final String KEY_FRIGO_FRIGO_ACCESO = "frigoAcceso";
    public static final String KEY_FRIGO_FREEZER_ACCESO = "freezerAcceso";
    public static final String KEY_FRIGO_FRIGO_TEMPERATURA = "frigoTemperatura";
    public static final String KEY_FRIGO_FREEZER_TEMPERATURA = "freezerTemperatura";
    public static final String KEY_FRIGO_CAPACITA_FRIGO = "capacitaFrigo";
    public static final String KEY_FRIGO_CAPACITA_FREEZER = "capacitaFreezer";
    public static final String KEY_FRIGO_N_PORTE = "nPorte";
    public static final String KEY_FRIGO_TIPO_RAFFREDDAMENTO = "tipoRaffreddamento";
    public static final String KEY_FRIGO_CLASSE_ENERGETICA = "classeEnergetica";
    public static final String KEY_FRIGO_LAMPADINA_ACCESA = "lampadinaAccesa";
    public static final String KEY_FRIGO_ALLARME_ATTIVO = "allarmeAttivo";
    public static final String KEY_FRIGO_VACATION_MODE = "vacationMode";

    public static final String KEY_RICETTA_PREFERITA = "_idRicettaPreferita";

    private static final String CREAZIONE_TABELLA_RICETTA_PREFERITA = "CREATE TABLE " + TABELLA_RICETTE_PREFERITE + " ("
            +" "+ KEY_RICETTA_PREFERITA + " INTEGER PRIMARY KEY)";

    private static final String CREAZIONE_TABELLA_RICETTA = "CREATE TABLE " + TABELLA_RICETTA + " ("
            +" "+ KEY_RICETTA_ID + " INTEGER PRIMARY KEY,"
            + KEY_RICETTA_NOME + " TEXT,"
            + KEY_RICETTA_AUTORE + " TEXT,"
            + KEY_RICETTA_DURATA + " TEXT,"
            + KEY_RICETTA_DIFFICOLTA + " INTEGER,"
            + KEY_RICETTA_INGREDIENTI + " TEXT,"
            + KEY_RICETTA_CATEGORIA + " TEXT,"
            + KEY_RICETTA_IMMAGINE + " BLOB,"
            + KEY_RICETTA_PROCEDIMENTO + " TEXT)";

    private static final String CREAZIONE_TABELLA_ALIMENTO = "CREATE TABLE " + TABELLA_ALIMENTO + "("
            +" "+ KEY_ALIMENTO_ID_RECORD + " INTEGER PRIMARY KEY,"
            + KEY_ALIMENTO_ID + " INTEGER,"
            + KEY_ALIMENTO_NOME + " TEXT,"
            + KEY_ALIMENTO_DATA_INSERIMENTO + " TEXT,"
            + KEY_ALIMENTO_IMMAGINE + " BLOB,"
            + KEY_ALIMENTO_STIMA_SCADENZA + " INTEGER)";

    private static final String CREAZIONE_TABELLA_FRIGO = "CREATE TABLE " + TABELLA_FRIGO + "("
            +" "+KEY_FRIGO_ID + " TEXT PRIMARY KEY,"
            +KEY_FRIGO_MODELLO + " TEXT,"
            +KEY_FRIGO_FRIGO_ACCESO + " INTEGER,"
            +KEY_FRIGO_FREEZER_ACCESO + " INTEGER,"
            +KEY_FRIGO_FRIGO_TEMPERATURA + " REAL,"
            +KEY_FRIGO_FREEZER_TEMPERATURA + " REAL,"
            +KEY_FRIGO_CAPACITA_FRIGO + " INTEGER,"
            +KEY_FRIGO_CAPACITA_FREEZER + " INTEGER,"
            +KEY_FRIGO_N_PORTE + " INTEGER,"
            +KEY_FRIGO_TIPO_RAFFREDDAMENTO + " TEXT,"
            +KEY_FRIGO_CLASSE_ENERGETICA + " TEXT,"
            +KEY_FRIGO_LAMPADINA_ACCESA + " INTEGER,"
            +KEY_FRIGO_ALLARME_ATTIVO + " INTEGER,"
            +KEY_FRIGO_VACATION_MODE + " INTEGER)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAZIONE_TABELLA_RICETTA);
        db.execSQL(CREAZIONE_TABELLA_FRIGO);
        db.execSQL(CREAZIONE_TABELLA_ALIMENTO);
        db.execSQL(CREAZIONE_TABELLA_RICETTA_PREFERITA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELLA_RICETTA);
        db.execSQL("DROP TABLE IF EXISTS " + TABELLA_FRIGO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELLA_ALIMENTO);
        db.execSQL("DROP TABLE IF EXISTS " + TABELLA_RICETTE_PREFERITE);

        onCreate(db);
    }
}
