package br.com.kalls.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="cidade")
public class Cidade implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable = false, unique = true, length = 50)
	@Length(max=50, message="O nome não pode ter mais de {max} caracteres")
	@NotBlank(message="O campo nome é obrigatório")
	@NotNull(message="O nome não pode ser nulo")
	private String nome;
	
	@ManyToOne
	@NotNull(message="O campo estado não pode ser nulo")
	@JoinColumn(name="estado", referencedColumnName="id",foreignKey = @ForeignKey(name = "FK_CIDADE_ESTADO"), nullable=false)
	private Estado estado;

	public Cidade() {
	}

	public Cidade(
			@Length(max = 50, message = "O nome não pode ter mais de {max} caracteres") @NotBlank(message = "O campo nome é obrigatório") @NotNull(message = "O nome não pode ser nulo") String nome,
			@NotNull(message = "O campo estado não pode ser nulo") Estado estado) {
		super();
		this.nome = nome;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", estado=" + estado + "]";
	}
	
}
