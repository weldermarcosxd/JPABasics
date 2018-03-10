package br.com.kalls.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="pessoa_juridica")
public class PessoaJuridica extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(message="A inscrição estadual não pode ser nula")
	@NotBlank(message="A inscrição estadual deve ser informada")
	@Length(max=15, message="A inscrição estadual deve possuir {max} caracteres")
	@Column(name="ie", nullable=false, length=15)
	private String ie;
	
	@NotNull(message="O cnpj não pode ser nulo")
	@NotBlank(message="O cnpj deve ser informado")
	@Length(max=14, message="O cnpj deve possuir {max} caracteres")
	@Column(name="cnpj", nullable=false, length=14)
	private String cnpj;
	
	public PessoaJuridica() {
	}

	public PessoaJuridica(
			@NotNull(message = "A inscrição estadual não pode ser nula") @NotBlank(message = "A inscrição estadual deve ser informada") @Length(max = 15, message = "A inscrição estadual deve possuir {max} caracteres") String ie,
			@NotNull(message = "O cnpj não pode ser nulo") @NotBlank(message = "O cnpj deve ser informado") @Length(max = 14, message = "O cnpj deve possuir {max} caracteres") String cnpj) {
		super();
		this.ie = ie;
		this.cnpj = cnpj;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [ie=" + ie + ", cnpj=" + cnpj + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cnpj == null) ? 0 : cnpj.hashCode());
		result = prime * result + ((ie == null) ? 0 : ie.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaJuridica other = (PessoaJuridica) obj;
		if (cnpj == null) {
			if (other.cnpj != null)
				return false;
		} else if (!cnpj.equals(other.cnpj))
			return false;
		if (ie == null) {
			if (other.ie != null)
				return false;
		} else if (!ie.equals(other.ie))
			return false;
		return true;
	}
}
