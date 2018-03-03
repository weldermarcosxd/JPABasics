package br.com.kalls.store.tests;

import static org.junit.Assert.*;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.kalls.store.entity.Estado;
import br.com.kalls.store.entity.Pais;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class EstadoRunner {

	EntityManager em;

	@Before
	public void setUp() throws Exception {
		em = EntityManagerUtil.getEntityManger();
		em.getTransaction().begin();
	}

	@After
	public void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testPersistence() {
		
		Estado estado = new Estado("Minas Gerais", "MG", em.find(Pais.class, 1L));
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Estado>> violations = validator.validate(estado);

		if (violations.size() > 0) {
			for (ConstraintViolation<Estado> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
			fail("Objeto não pode ser persistido");
		} else {
			try {
				em.persist(estado);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
				fail("Objeto não pode ser persistido");
			}
		}
	}
}
