package br.com.kalls.store.tests;

import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.kalls.store.entity.Compra;
import br.com.kalls.store.entity.CompraID;
import br.com.kalls.store.entity.ItemCompra;
import br.com.kalls.store.entity.PessoaJuridica;
import br.com.kalls.store.entity.Produto;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class CompraRunner {

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
		PessoaJuridica pessoaJuridica = em.find(PessoaJuridica.class, 2L);
		
		CompraID compraID = new CompraID(123456987, pessoaJuridica);
		Compra compra = new Compra(compraID, Calendar.getInstance(), BigDecimal.ZERO);
		ItemCompra itemCompra = new ItemCompra(new Double(10), new BigDecimal("10"), new BigDecimal("100"), compra, produto);
		
		compra.addItem(itemCompra);

		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Compra>> violations = validator.validate(compra);

		if (violations.size() > 0) {
			for (ConstraintViolation<Compra> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
			fail("Objeto não pode ser persistido");
		} else {
			try {
				em.persist(compra);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
				fail("Objeto não pode ser persistido");
			}
		}
	}
}
