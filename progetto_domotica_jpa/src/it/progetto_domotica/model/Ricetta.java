package it.progetto_domotica.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Ricetta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRicetta;
	@Column(length = 50, nullable = false)
	private String nome;
	@Column(length = 500, nullable = false)
	private String descrizione;
	@Column(length = 500, nullable = false)
	private String ingredienti;
	@Column(length = 5, nullable = false)
	private int tempoPreparazione;
	@Column(nullable = false)
	private byte[] immagine;

	@ManyToOne(cascade = CascadeType.ALL)
	private Utente utente;
	@OneToMany(mappedBy = "ricetta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Prodotto> prodotti;

	public int getIdRicetta() {
		return idRicetta;
	}

	public void setIdRicetta(int idRicetta) {
		this.idRicetta = idRicetta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIngredienti() {
		return ingredienti;
	}

	public void setIngredienti(String ingredienti) {
		this.ingredienti = ingredienti;
	}

	public int getTempoPreparazione() {
		return tempoPreparazione;
	}

	public void setTempoPreparazione(int tempoPreparazione) {
		this.tempoPreparazione = tempoPreparazione;
	}

	public byte[] getImmagine() {
		return immagine;
	}

	public void setImmagine(byte[] immagine) {
		this.immagine = immagine;
	}

}
