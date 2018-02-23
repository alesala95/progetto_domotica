package com.example.itsadmin.smartfridge_fragment.Main.Items;

        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.Date;

/**
 * Created by itsadmin on 30/01/2018.
 */
//mapping della list view dei prodotti
public class ListItem {


    String nome;
    String quantita;
    Date scadenza;

    int img;

    boolean flag;


    public ListItem(String nome, String quantita, Date scadenza, int img) {

        this.nome = nome;
        this.quantita=quantita;
        this.scadenza= scadenza;

        this.img = img;

        flag = false;
    }

    public ListItem(){


    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuantita() {
        return quantita;
    }

    public void setQuantita(String quantita) {
        this.quantita = quantita;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
