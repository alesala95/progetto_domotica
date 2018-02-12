package it.progetto_domotica.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Utente {

	@Id
	private String email;
	@Column(length = 50, nullable = false)
	private String nome;
	@Column(length = 50, nullable = false)
	private String cognome;
	@Column(length = 3, nullable = false)
	private int eta;
	@Column(length = 50, nullable = false)
	private String username;
	@Column(length = 50, nullable = false)
	private String password;

	@OneToOne(mappedBy = "utente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Frigorifero frigoriferi;
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ricetta> ricette;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
