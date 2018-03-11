package com.example.lorealerick.smartfridge2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.lorealerick.smartfridge2.Models.Alimento;
import com.example.lorealerick.smartfridge2.Models.Ricetta;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by LoreAleRick on 08/03/2018.
 */

public class DatabaseAdapter {


    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public DatabaseAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        dbHelper.close();
        database.close();
    }

    public void svuotaTabellaRicette (){

        open();

        database.execSQL("delete from " + DatabaseHelper.TABELLA_RICETTA);

        close();
    }

    public void svuotaTabellaAlimenti (){

        open();

        database.execSQL("delete from " + DatabaseHelper.TABELLA_ALIMENTO);

        close();
    }

    public void addAlimento(Alimento alimento){

        open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_ALIMENTO_ID, alimento.getIdAlimento());
        values.put(DatabaseHelper.KEY_ALIMENTO_NOME, alimento.getNome());
        values.put(DatabaseHelper.KEY_ALIMENTO_DATA_INSERIMENTO, alimento.getData_inserimento());
        values.put(DatabaseHelper.KEY_ALIMENTO_STIMA_SCADENZA, alimento.getStima_scadenza());
        values.put(DatabaseHelper.KEY_ALIMENTO_IMMAGINE, alimento.getImage());

        database.insert(DatabaseHelper.TABELLA_ALIMENTO, null, values);

        close();
    }

    public ArrayList <Ricetta> getRicetteConsigliate (ArrayList <Alimento> alimenti){

        ArrayList <Ricetta> ricette = new ArrayList<>();
        String sql = "";

        open();

        for (Alimento a : alimenti){

            sql = "SELECT * FROM " + DatabaseHelper.TABELLA_RICETTA + " WHERE " + DatabaseHelper.KEY_RICETTA_INGREDIENTI + " LIKE '%" + a.getNome().toLowerCase() + "%';";
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor.moveToFirst()) {

                do {

                    Ricetta ricetta = new Ricetta();

                    ricetta.setId(Integer.parseInt(cursor.getString(0)));
                    ricetta.setNome(cursor.getString(1));
                    ricetta.setAutore(cursor.getString(2));
                    ricetta.setDurata(cursor.getString(3));
                    ricetta.setDifficolta(Integer.parseInt(cursor.getString(4)));
                    ricetta.setIngredienti(cursor.getString(5));
                    ricetta.setProcedimento(cursor.getString(8));
                    ricetta.setImage(cursor.getBlob(7));
                    ricetta.setCategoria(cursor.getString(6));

                    ricette.add(ricetta);
                } while (cursor.moveToNext());
            }
        }

        close();

        return ricette;
    }

    public void addRicetta(Ricetta ricetta) {
        open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_RICETTA_ID, ricetta.getId());
        values.put(DatabaseHelper.KEY_RICETTA_NOME, ricetta.getNome());
        values.put(DatabaseHelper.KEY_RICETTA_AUTORE, ricetta.getAutore());
        values.put(DatabaseHelper.KEY_RICETTA_DURATA, ricetta.getDurata());
        values.put(DatabaseHelper.KEY_RICETTA_DIFFICOLTA, ricetta.getDifficolta());
        values.put(DatabaseHelper.KEY_RICETTA_INGREDIENTI, ricetta.getIngredienti());
        values.put(DatabaseHelper.KEY_RICETTA_PROCEDIMENTO, ricetta.getProcedimento());
        values.put(DatabaseHelper.KEY_RICETTA_CATEGORIA, ricetta.getCategoria());
        values.put(DatabaseHelper.KEY_RICETTA_IMMAGINE, ricetta.getImage());

        database.insert(DatabaseHelper.TABELLA_RICETTA, null, values);

        close();
    }

    public ArrayList <Ricetta> getAllRicette () {

        ArrayList<Ricetta> listaRicette = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABELLA_RICETTA;

        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                Ricetta ricetta = new Ricetta();

                ricetta.setId(Integer.parseInt(cursor.getString(0)));
                ricetta.setNome(cursor.getString(1));
                ricetta.setAutore(cursor.getString(2));
                ricetta.setDurata(cursor.getString(3));
                ricetta.setDifficolta(Integer.parseInt(cursor.getString(4)));
                ricetta.setIngredienti(cursor.getString(5));
                ricetta.setProcedimento(cursor.getString(8));
                ricetta.setImage(cursor.getBlob(7));
                ricetta.setCategoria(cursor.getString(6));

                listaRicette.add(ricetta);
            } while (cursor.moveToNext());
        }

        close();

        return listaRicette;
    }

    public ArrayList <Alimento> getAllAlimenti () {

        ArrayList<Alimento> listaAlimenti = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABELLA_ALIMENTO;

        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                Alimento alimento = new Alimento();

                alimento.setIdAlimento(Integer.parseInt(cursor.getString(1)));
                alimento.setNome(cursor.getString(2));
                alimento.setData_inserimento(cursor.getString(3));
                alimento.setImage(cursor.getBlob(4));
                alimento.setStima_scadenza(Integer.parseInt(cursor.getString(5)));

                listaAlimenti.add(alimento);
            } while (cursor.moveToNext());
        }

        close();

        return listaAlimenti;
    }

    public ArrayList <Ricetta> getAllRicetteForCategoria (String categoria, int limite) {

        ArrayList<Ricetta> listaRicette = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABELLA_RICETTA + " WHERE " + DatabaseHelper.KEY_RICETTA_CATEGORIA + " = '" + categoria +"' LIMIT " + limite +" ";

        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                Ricetta ricetta = new Ricetta();

                ricetta.setId(Integer.parseInt(cursor.getString(0)));
                ricetta.setNome(cursor.getString(1));
                ricetta.setAutore(cursor.getString(2));
                ricetta.setDurata(cursor.getString(3));
                ricetta.setDifficolta(Integer.parseInt(cursor.getString(4)));
                ricetta.setIngredienti(cursor.getString(5));
                ricetta.setProcedimento(cursor.getString(8));
                ricetta.setCategoria(cursor.getString(6));
                ricetta.setImage(cursor.getBlob(7));

                listaRicette.add(ricetta);
            } while (cursor.moveToNext());
        }

        close();

        return listaRicette;
    }

    public ArrayList <String> getAllCategorie (){

        ArrayList <String> listaCategorie = new ArrayList<>();

        String selectQuery = "SELECT DISTINCT " + DatabaseHelper.KEY_RICETTA_CATEGORIA + " FROM " + DatabaseHelper.TABELLA_RICETTA + "; ";

        open();

        Cursor cursor = database.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){

            do{

                listaCategorie.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        close();

        return listaCategorie;
    }


    public Ricetta getRicetta (int idRicetta) {

        String query = "SELECT * FROM " + DatabaseHelper.TABELLA_RICETTA + " WHERE " + DatabaseHelper.KEY_RICETTA_ID + " = " + idRicetta + ";";

        open();

        Cursor cursor = database.rawQuery(query,null);

        Ricetta ricetta = null;

        if (cursor.moveToFirst()) {

            ricetta = new Ricetta();

            ricetta.setId(Integer.parseInt(cursor.getString(0)));
            ricetta.setNome(cursor.getString(1));
            ricetta.setAutore(cursor.getString(2));
            ricetta.setDurata(cursor.getString(3));
            ricetta.setDifficolta(Integer.parseInt(cursor.getString(4)));
            ricetta.setIngredienti(cursor.getString(5));
            ricetta.setCategoria(cursor.getString(6));
            ricetta.setImage(cursor.getBlob(7));
            ricetta.setProcedimento(cursor.getString(8));
        }

        close();

        return ricetta;
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
    /*
    void addContact(Contact contact) {
        open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_CONTACT_NAME, contact.getName());
        values.put(DatabaseHelper.KEY_CONTACT_PHONE, contact.getPhoneNumber());

        database.insert(DatabaseHelper.TABLE_CONTACTS, null, values);

        close();
    }

    Contact getContact(int id) {
        open();

        Cursor cursor = database.query(DatabaseHelper.TABLE_CONTACTS, new String[] {DatabaseHelper.KEY_CONTACT_ID,
                        DatabaseHelper.KEY_CONTACT_NAME, DatabaseHelper.KEY_CONTACT_PHONE }, DatabaseHelper.KEY_CONTACT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return contact;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();

        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_CONTACTS;

        open();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));

                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    public int updateContact(Contact contact) {
        open();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_CONTACT_NAME, contact.getName());
        values.put(DatabaseHelper.KEY_CONTACT_PHONE, contact.getPhoneNumber());

        return database.update(DatabaseHelper.TABLE_CONTACTS, values, DatabaseHelper.KEY_CONTACT_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    public void deleteContact(Contact contact) {
        open();
        database.delete(DatabaseHelper.TABLE_CONTACTS, DatabaseHelper.KEY_CONTACT_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        close();
    }


    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_CONTACTS;

        open();
        Cursor cursor = database.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
    */
}
