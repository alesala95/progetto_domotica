package it.progetto_domotica.business;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import it.progetto_domotica.model.Utente;

public class GestioneAccount {

	public boolean login(String usernameOrEmail, String password) {
		EntityManager em = JPAUtility.emf.createEntityManager();

		Utente utente = em.find(Utente.class, usernameOrEmail);
		if (utente != null) {
			return CheckPassword(password, utente);
		} else {
			utente = utentePerUsername(usernameOrEmail, em);
			if (utente != null) {
				return CheckPassword(password, utente);
			}
		}
		return false;
	}

	public Utente utenteMailOrUsername(String usernameOrEmail) {
		EntityManager em = JPAUtility.emf.createEntityManager();

		Utente utente = em.find(Utente.class, usernameOrEmail);
		if (utente != null) {
			return utente;
		} else {
			return utentePerUsername(usernameOrEmail, em);
		}
	}

	public boolean Registrazione(String email, String nome, String cognome, int eta, String username, String password) {
		EntityManager em = JPAUtility.emf.createEntityManager();

		Utente utente = em.find(Utente.class, email);
		if (utente != null) {
			return false;
		} else {
			utente = utentePerUsername(username, em);
			if (utente != null) {
				return false;
			}
		}

		Utente u = new Utente();
		u.setEmail(email);
		u.setNome(nome);
		u.setCognome(cognome);
		u.setEta(eta);
		u.setUsername(username);
		u.setPassword(password);

		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();

		return true;
	}

	private Utente utentePerUsername(String username, EntityManager em) {
		Utente utente = null;
		try {
			utente = em.createQuery("SELECT u FROM Utente u WHERE u.username=:username", Utente.class)
					.setParameter("username", username).getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}
		return utente;
	}

	private boolean CheckPassword(String password, Utente utente) {
		if (utente.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

}