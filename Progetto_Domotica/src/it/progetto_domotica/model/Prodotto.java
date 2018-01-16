package it.progetto_domotica.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Prodotto {

	@Id
	private String codiceBarre;
	@Column(length = 50, nullable = false)
	private String nome;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date scadenza;
	@Column(length = 5, nullable = false)
	private int prezzo;
	@Column(length = 5, nullable = false)
	private int quantita;
	@Column(nullable = false)
	private byte[] immagine;

	@ManyToOne(cascade = CascadeType.ALL)
	private Frigorifero frigorifero;
	@ManyToOne(cascade = CascadeType.ALL)
	private Ricetta ricetta;

	public String getCodiceBarre() {
		return codiceBarre;
	}

	public void setCodiceBarre(String codiceBarre) {
		this.codiceBarre = codiceBarre;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getScadenza() {
		return scadenza;
	}

	public void setScadenza(Date scadenza) {
		this.scadenza = scadenza;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public byte[] getImmagine() {
		return immagine;
	}

	public void setImmagine(byte[] immagine) {
		this.immagine = immagine;
	}

}
