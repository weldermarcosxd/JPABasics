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

import br.com.kalls.store.entity.PessoaJuridica;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class PessoaJuridicaRunner {

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
		
		PessoaJuridica pessoaJuridica = new PessoaJuridica();
		pessoaJuridica.setNome("Kalls Tecnologia");
		pessoaJuridica.setEmail("Contato@kallstecnologia.com");
		pessoaJuridica.setTelefone("37998191067");
		pessoaJuridica.setCnpj("74199865000199");
		pessoaJuridica.setIe("12345678912345");
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PessoaJuridica>> violations = validator.validate(pessoaJuridica);

		if (violations.size() > 0) {
			for (ConstraintViolation<PessoaJuridica> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
			fail("Objeto não pode ser persistido");
		} else {
			try {
				em.persist(pessoaJuridica);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
				fail("Objeto não pode ser persistido");
			}
		}
	}
}
