package br.com.kalls.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "foto")
public class Foto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "O nome não pode ser nulo")
	@NotBlank(message = "O nome deve ser informado")
	@Length(max = 50, message = "O nome não pode ser maior que {max}")
	@Column(name = "nome", nullable = false, length = 50)
	private String nome;

	@NotNull(message = "A descrição não pode ser nula ")
	@NotBlank(message = "A descrição deve ser informada")
	@Length(max = 50, message = "A descrição não pode ser maior que {max}")
	@Column(name = "descricao", nullable = false, length = 50)
	private String descricao;

	@NotNull(message = "O arquivo deve ser informado")
	@Lob
	private byte[] arquivo;

	@NotNull(message = "O produto deve ser informado")
	@ManyToOne
	@JoinColumn(name = "produto", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_FOTO_PRODUTO"), nullable = false)
	private Produto produto;

	public Foto() {
	}

	public Foto(
			@NotNull(message = "O nome não pode ser nulo") @NotBlank(message = "O nome deve ser informado") @Length(max = 50, message = "O nome não pode ser maior que {max}") String nome,
			@NotNull(message = "A descrição não pode ser nula ") @NotBlank(message = "A descrição deve ser informada") @Length(max = 50, message = "A descrição não pode ser maior que {max}") String descricao,
			@NotNull(message = "O arquivo deve ser informado") byte[] arquivo,
			@NotNull(message = "O produto deve ser informado") Produto produto) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.arquivo = arquivo;
		this.produto = produto;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "Foto [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Foto other = (Foto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
