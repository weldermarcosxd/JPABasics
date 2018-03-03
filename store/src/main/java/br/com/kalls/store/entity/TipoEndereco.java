package br.com.kalls.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="tipo_endereco")
public class TipoEndereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="descricao", nullable = false, unique = true, length = 50)
	@Length(max=50, message="A descricao não pode ter mais de {max} caracteres")
	@NotBlank(message="O campo descricao é obrigatório")
	@NotNull(message="A descricao não pode ser nula")
	private String descricao;
	
	public TipoEndereco() {
	}

	public TipoEndereco(
			@Length(max = 50, message = "A descricao não pode ter mais de {max} caracteres") @NotBlank(message = "O campo descricao é obrigatório") @NotNull(message = "A descricao não pode ser nula") String descricao) {
		super();
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "TipoEndereco [id=" + id + ", descricao=" + descricao + "]";
	}
}
