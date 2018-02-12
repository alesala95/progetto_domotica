package it.progetto_domotica.business;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtility {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("domotica");
}
