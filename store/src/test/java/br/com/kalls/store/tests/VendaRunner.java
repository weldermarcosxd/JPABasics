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

import br.com.kalls.store.entity.ItemVenda;
import br.com.kalls.store.entity.PessoaFisica;
import br.com.kalls.store.entity.Produto;
import br.com.kalls.store.entity.Venda;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class VendaRunner {

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
		
		Venda venda = new Venda(Calendar.getInstance(), new BigDecimal("0.0"), 1, pessoa);
		ItemVenda itemVenda = new ItemVenda(new Double("2"), new BigDecimal("5.0"), new BigDecimal("10.0"), venda, produto);
		
		venda.addItens(itemVenda);

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
