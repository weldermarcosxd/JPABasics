package br.com.kalls.store.tests;

import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import br.com.kalls.store.entity.Venda;
import br.com.kalls.store.jpa.EntityManagerUtil;
import junit.framework.TestCase;

public class ParcelasRunner extends TestCase {

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

		Venda venda = em.find(Venda.class, 2L);
		venda.parcelar();

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Venda>> violations = validator.validate(venda);

		if (violations.size() > 0) {
			for (ConstraintViolation<Venda> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
			fail("Objeto não pode ser persistido");
		} else {
			try {
				em.persist(venda);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
				fail("Objeto não pode ser persistido");
			}
		}
	}
}
