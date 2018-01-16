package it.progetto_domotica.business;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import it.progetto_domotica.model.Utente;

public class GestioneAccount {

	public boolean login(String usernameOrEmail, String password) {

		EntityManager em = JPAUtility.emf.createEntityManager();
		Utente utente = em.find(Utente.class, usernameOrEmail);

		return false;
	}

	private boolean CheckPassword(String password, Utente utente) {
		if (utente.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}

	}
}
