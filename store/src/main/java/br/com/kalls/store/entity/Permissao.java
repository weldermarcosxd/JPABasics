package br.com.kalls.store.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "permissao")
public class Permissao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "nome", nullable = false, unique = true, length = 20)
	@Length(max = 20, message = "O nome não pode ter mais de {max} caracteres")
	@NotBlank(message = "O campo nome é obrigatório")
	@NotNull(message = "O nome não pode ser nulo")
	private String nome;
	
	@Column(name="descricao", nullable = false, unique = true, length = 50)
	@Length(max=50, message="A descricao não pode ter mais de {max} caracteres")
	@NotBlank(message="O campo descricao é obrigatório")
	@NotNull(message="A descricao não pode ser nula")
	private String descricao;
	
	public Permissao() {
	}

	public Permissao(
			@Length(max = 20, message = "O nome não pode ter mais de {max} caracteres") @NotBlank(message = "O campo nome é obrigatório") @NotNull(message = "O nome não pode ser nulo") String nome,
			@Length(max = 50, message = "A descricao não pode ter mais de {max} caracteres") @NotBlank(message = "O campo descricao é obrigatório") @NotNull(message = "A descricao não pode ser nula") String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Permissao [nome=" + nome + ", descricao=" + descricao + "]";
	}
}
