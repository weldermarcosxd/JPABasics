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

import br.com.kalls.store.entity.Categoria;
import br.com.kalls.store.jpa.EntityManagerUtil;
import junit.framework.TestCase;

public class CategoriaRunner extends TestCase {

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

		Categoria categoria = new Categoria("Eletrodom�stico");
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Categoria>> violations = validator.validate(categoria);

		if (violations.size() > 0) {
			for (ConstraintViolation<Categoria> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
		} else {
			try {
				em.persist(categoria);
				em.getTransaction().commit();
				valid = true;
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
			}
		}
		Assert.assertEquals("Objeto n�o persistido" , true, valid);
	}
}
