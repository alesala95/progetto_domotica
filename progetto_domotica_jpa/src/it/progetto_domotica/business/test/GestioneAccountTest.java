package it.progetto_domotica.business.test;

import static org.junit.Assert.*;
import org.junit.Test;
import it.progetto_domotica.business.GestioneAccount;

public class GestioneAccountTest {

	@Test
	public void testLogin() {
		GestioneAccount ga = new GestioneAccount();

		boolean esito = ga.login("non esiste user", "pwd errata");
		assertTrue("Login errato", esito == false);
	}

	@Test
	public void testRegistrazione() {
		GestioneAccount ga = new GestioneAccount();

		boolean esito = ga.Registrazione("ale@gmail.com", "ale", "sala", 22, "ale95", "abcd123");
		assertTrue("Registrazione andata a buon fine", esito == true);

		esito = ga.Registrazione("ciao@gmail.com", "lory", "sala", 25, "lore02", "abcd1235678");
		assertTrue("Registrazione non corretta (cognome già esistente)", esito == true);

	}

}
