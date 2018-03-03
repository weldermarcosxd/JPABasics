package br.com.kalls.store.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	
	public static final String persistenceUnity = "store";

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;

	public static EntityManager getEntityManger() {
		if (entityManagerFactory == null) {
			entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnity);
		}
		if (entityManager == null) {
			entityManager = entityManagerFactory.createEntityManager();
		}
		return entityManager;
	}
}
