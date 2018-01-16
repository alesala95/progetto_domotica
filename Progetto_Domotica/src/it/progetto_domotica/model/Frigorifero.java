package it.progetto_domotica.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Frigorifero {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idFrigorifero;
	@Column(length = 5, nullable = false)
	private int capacita;
	@Column(length = 3, nullable = false)
	private int altezza;
	@Column(length = 3, nullable = false)
	private int larghezza;

	@OneToOne(fetch = FetchType.LAZY)
	private Utente utente;	
	@OneToMany(mappedBy = "frigorifero", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Prodotto> prodotti;

	public int getIdFrigorifero() {
		return idFrigorifero;
	}

	public void setIdFrigorifero(int idFrigorifero) {
		this.idFrigorifero = idFrigorifero;
	}

	public int getCapacita() {
		return capacita;
	}

	public void setCapacita(int capacita) {
		this.capacita = capacita;
	}

	public int getAltezza() {
		return altezza;
	}

	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}

	public int getLarghezza() {
		return larghezza;
	}

	public void setLarghezza(int larghezza) {
		this.larghezza = larghezza;
	}

}
