package br.com.kalls.store.tests;

import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.kalls.store.entity.PessoaFisica;
import br.com.kalls.store.jpa.EntityManagerUtil;

public class PessoaFisicaRunner {

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
		
		PessoaFisica pessoaFisica = new PessoaFisica();
		pessoaFisica.setNome("Welder Marcos");
		pessoaFisica.setEmail("weldermarcosxd@gmail.com");
		pessoaFisica.setTelefone("37998191067");
		pessoaFisica.setRg("17962522");
		pessoaFisica.setCpf("12546752642");
		pessoaFisica.setNascimento(Calendar.getInstance());
		pessoaFisica.setNomeUsuario("weldermarcosxd");
		pessoaFisica.setSenha("teste123");
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<PessoaFisica>> violations = validator.validate(pessoaFisica);

		if (violations.size() > 0) {
			for (ConstraintViolation<PessoaFisica> constraintViolation : violations) {
				System.out.println("Erro: " + constraintViolation.getMessage());
			}
			fail("Objeto não pode ser persistido");
		} else {
			try {
				em.persist(pessoaFisica);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Erro: " + e.getCause().getCause().getMessage());
				em.getTransaction().rollback();
				fail("Objeto não pode ser persistido");
			}
		}
	}
}
