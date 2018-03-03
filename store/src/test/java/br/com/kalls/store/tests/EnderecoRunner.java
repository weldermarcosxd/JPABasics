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

import br.com.kalls.store.entity.Endereco;
import br.com.kalls.store.entity.Pessoa;
import br.com.kalls.store.entity.TipoEndereco;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class EnderecoRunner {

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
		
		Pessoa pessoa = em.find(Pessoa.class, 1L);
		TipoEndereco tipoEndereco = em.find(TipoEndereco.class, 1L);
		Endereco endereco = new Endereco("Residencial", "Rua A", 1000, null, 35570000, "Baiiro A", null, pessoa, tipoEndereco);
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Endereco>> violations = validator.validate(endereco);
		
		pessoa.addEnderecos(endereco);

		if (violations.size() > 0) {
			for (ConstraintViolation<Endereco> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
			fail("Objeto não pode ser persistido");
		} else {
			try {
				em.persist(pessoa);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
				fail("Objeto não pode ser persistido");
			}
		}
	}
}
