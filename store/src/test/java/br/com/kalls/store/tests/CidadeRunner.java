package br.com.kalls.store.tests;

import static org.junit.Assert.fail;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.kalls.store.entity.Cidade;
import br.com.kalls.store.entity.Estado;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class CidadeRunner {

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
		
		Cidade cidade = new Cidade("Formiga", em.find(Estado.class, 1L));
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Cidade>> violations = validator.validate(cidade);

		if (violations.size() > 0) {
			for (ConstraintViolation<Cidade> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
			fail("Objeto n�o pode ser persistido");
		} else {
			try {
				em.persist(cidade);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
				fail("Objeto n�o pode ser persistido");
			}
		}
	}
}
