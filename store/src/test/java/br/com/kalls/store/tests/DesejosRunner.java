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

import br.com.kalls.store.entity.PessoaFisica;
import br.com.kalls.store.entity.Produto;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class DesejosRunner {

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
		
		Produto produto = em.find(Produto.class, 1L);
		PessoaFisica pessoa = em.find(PessoaFisica.class, 1L);
		
		produto.getDesejam().add(pessoa);
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Produto>> violations = validator.validate(produto);

		if (violations.size() > 0) {
			for (ConstraintViolation<Produto> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
			fail("Objeto não pode ser persistido");
		} else {
			try {
				em.persist(produto);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
				fail("Objeto não pode ser persistido");
			}
		}
	}
}
