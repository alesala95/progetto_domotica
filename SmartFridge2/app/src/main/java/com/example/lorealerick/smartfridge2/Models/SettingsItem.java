package com.example.lorealerick.smartfridge2.Models;

/**
 * Created by itsadmin on 13/03/2018.
 */

public class SettingsItem {

    private String descrizione;
    private int icon;

    public SettingsItem(String descrizione, int icon) {
        this.descrizione = descrizione;
        this.icon = icon;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
