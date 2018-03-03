package br.com.kalls.store.tests;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.kalls.store.entity.Marca;
import br.com.kalls.store.jpa.EntityManagerUtil;
import junit.framework.TestCase;

public class MarcaRunner extends TestCase {

	EntityManager em;

	@Before
	protected void setUp() throws Exception {
		em = EntityManagerUtil.getEntityManger();
		em.getTransaction().begin();
	}

	@After
	protected void tearDown() throws Exception {
		em.close();
	}

	@Test
	public void testPersistence() {
		
		Boolean valid = false;

		Marca marca = new Marca("Dako");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Marca>> violations = validator.validate(marca);

		if (violations.size() > 0) {
			for (ConstraintViolation<Marca> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
		} else {
			try {
				em.persist(marca);
				em.getTransaction().commit();
				valid = true;
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
			}
		}
		Assert.assertEquals("Objeto não persistido" , true, valid);
	}
}
