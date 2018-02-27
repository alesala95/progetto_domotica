package com.example.itsadmin.smartfridge_fragment.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.sql.Blob;

/**
 * Created by itsadmin on 27/02/2018.
 */

public class DataBaseAdapter {

    @SuppressWarnings("unused")
    private static final String LOG_TAG = DataBaseAdapter.class.getSimpleName();

    private Context context;
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;

    // Database fields
    private static final String DATABASE_TABLE = "alimento";

    public static final String KEY_ID = "_id";
    public static final String KEY_NOME = "nome";
    public static final String KEY_DATA_SCADENZA = "data_scadenza";
    public static final String KEY_IMG="immagine";


    public DataBaseAdapter(Context context) {
        this.context = context;
    }

    public DataBaseAdapter open() throws SQLException {
        dbHelper = new DataBaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }



    private ContentValues createContentValues(String nome, String data_scadenza, String immagine) {
        ContentValues values = new ContentValues();
        values.put( KEY_NOME, nome );
        values.put( KEY_DATA_SCADENZA, data_scadenza );
        values.put( KEY_IMG, immagine );


        return values;
    }

    //create a contact
    public long alimento(String nome, String data_scadenza, String immagine ) {
        ContentValues initialValues = createContentValues(nome, data_scadenza, immagine);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    //update a contact
    public boolean updateAlimento( long contactID, String nome, String data_scadenza, String immagine ) {
        ContentValues updateValues = createContentValues(nome,data_scadenza, immagine);
        return database.update(DATABASE_TABLE, updateValues, KEY_ID + "=" + contactID, null) > 0;
    }


    //delete a contact
    public boolean deleteAlimento(long contactID) {
        return database.delete(DATABASE_TABLE, KEY_ID + "=" + contactID, null) > 0;
    }

    //fetch all contacts
    public Cursor fetchAllAlimenti() {
        return database.query(DATABASE_TABLE, new String[] { KEY_ID, KEY_NOME, KEY_DATA_SCADENZA, KEY_IMG}, null, null, null, null, null);
    }

    //fetch contacts filter by a string
    public Cursor fetchAlimentoByFilter(String filter) {
        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        KEY_ID, KEY_NOME, KEY_DATA_SCADENZA, KEY_IMG  },
                KEY_NOME + " like '%"+ filter + "%'", null, null, null,
                null, null);

        return mCursor;

    }


    public Cursor fetchAlimento(String filter){
        Cursor sCursor= database.query(true,DATABASE_TABLE,new String[]
                {KEY_ID, KEY_NOME, KEY_DATA_SCADENZA, KEY_IMG },KEY_ID+"="+filter,null,null,null,null,null);
        return sCursor;
    }

}
