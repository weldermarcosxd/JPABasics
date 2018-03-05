package br.com.kalls.store.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "pessoa_fisica")
@DiscriminatorValue("PessoaFisica")
public class PessoaFisica extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "rg", nullable = false, length = 10)
	@Length(max = 10, message = "O rg não pode ter mais de {max} caracteres")
	@NotBlank(message = "O campo rg é obrigatório")
	@NotNull(message = "O rg não pode ser nulo")
	private String rg;

	@Column(name = "cpf", nullable = false, length = 11)
	@Length(max = 11, message = "O cpf não pode ter mais de {max} caracteres")
	@NotBlank(message = "O campo cpf é obrigatório")
	@NotNull(message = "O cpf não pode ser nulo")
	@CPF(message = "Cpf deve ser válido")
	private String cpf;

	@Column(name = "nascimento", nullable = false)
	@NotNull(message = "Nacimento não pode ser nulo")
	@Temporal(TemporalType.DATE)
	private Calendar nascimento;

	@Column(name = "nome_usuario", nullable = false, length = 20, unique = true)
	@Length(max = 20, message = "O nome de usuário não pode ter mais de {max} caracteres")
	@NotBlank(message = "O nome de usuário é obrigatório")
	@NotNull(message = "O nome de usuário não pode ser nulo")
	private String nomeUsuario;

	@Column(name = "senha", nullable = false, length = 20)
	@Length(max = 10, message = "A senha não pode ter mais de {max} caracteres")
	@NotBlank(message = "O campo senha é obrigatório")
	@NotNull(message = "A senha não pode ser nula")
	private String senha;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "desejos", joinColumns = @JoinColumn(name = "pessoa_fisica", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "pessoa_fisica", "produto" }) })
	private List<Produto> desejos = new ArrayList<Produto>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "permissoes", joinColumns = @JoinColumn(name = "nome_usuario", referencedColumnName = "nome_usuario", nullable = false), inverseJoinColumns = @JoinColumn(name = "permissao", referencedColumnName = "nome", nullable = false), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "nome_usuario", "permissao" }) })
	private List<Permissao> permissoes = new ArrayList<Permissao>();

	public PessoaFisica() {
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Calendar getNascimento() {
		return nascimento;
	}

	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public List<Produto> getDesejos() {
		return desejos;
	}

	public void setDesejos(List<Produto> desejos) {
		this.desejos = desejos;
	}
	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	@Override
	public String toString() {
		return "PessoaFisica [rg=" + rg + ", cpf=" + cpf + ", nascimento=" + nascimento + ", nomeUsuario=" + nomeUsuario
				+ ", senha=" + senha + "]";
	}
}
