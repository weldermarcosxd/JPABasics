package br.com.kalls.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class CompraID implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message = "O número da nota deve ser informado")
	@Column(name = "numero_nota", nullable = false)
	private Integer numeroNota;

	@NotNull(message = "A pessoa jurídica deve ser informada")
	@ManyToOne
	@JoinColumn(name = "pessoa_juridica", referencedColumnName = "id", nullable = false)
	private PessoaJuridica pessoaJuridica;

	public CompraID() {
	}

	public CompraID(@NotNull(message = "O número da nota deve ser informado") Integer numeroNota,
			@NotNull(message = "A pessoa jurídica deve ser informada") PessoaJuridica pessoaJuridica) {
		super();
		this.numeroNota = numeroNota;
		this.setPessoaJuridica(pessoaJuridica);
	}

	public Integer getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(Integer numeroNota) {
		this.numeroNota = numeroNota;
	}
	
	public PessoaJuridica getPessoaJuridica() {
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) {
		this.pessoaJuridica = pessoaJuridica;
	}

	@Override
	public String toString() {
		return "CompraID [numeroNota=" + numeroNota + ", pessoaJuridica=" + getPessoaJuridica() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroNota == null) ? 0 : numeroNota.hashCode());
		result = prime * result + ((getPessoaJuridica() == null) ? 0 : getPessoaJuridica().hashCode());
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
		CompraID other = (CompraID) obj;
		if (numeroNota == null) {
			if (other.numeroNota != null)
				return false;
		} else if (!numeroNota.equals(other.numeroNota))
			return false;
		if (getPessoaJuridica() == null) {
			if (other.getPessoaJuridica() != null)
				return false;
		} else if (!getPessoaJuridica().equals(other.getPessoaJuridica()))
			return false;
		return true;
	}
}
