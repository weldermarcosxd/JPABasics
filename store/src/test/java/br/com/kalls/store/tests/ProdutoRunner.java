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

import br.com.kalls.store.entity.Categoria;
import br.com.kalls.store.entity.Marca;
import br.com.kalls.store.entity.Produto;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class ProdutoRunner {

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
		
		Produto produto = new Produto("Geladeira 1000L", 1000.0, 10.0, "Geladeira econômica", em.find(Categoria.class, 1L), em.find(Marca.class, 1L));
		
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
