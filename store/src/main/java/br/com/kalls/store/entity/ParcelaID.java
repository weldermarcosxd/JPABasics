package br.com.kalls.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
public class ParcelaID implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message="A venda não pode ser nula")
	@ManyToOne
	@JoinColumn(name="venda",referencedColumnName="id", nullable=false, foreignKey = @ForeignKey(name="FK_PARCELA_VENDA"))
	private Venda venda;
	
	@Min(message="O número de parcelas não pode ser menor que {min}", value=1)
	@NotNull(message="O número da parcela não pode ser nulo")
	@Column(name="numero", nullable=false)
	private Integer numero;
	
	public ParcelaID() {
	}

	public ParcelaID(Venda venda, Integer numero) {
		super();
		this.venda = venda;
		this.numero = numero;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return "ParcelaID [venda=" + venda + ", numero=" + numero + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((venda == null) ? 0 : venda.hashCode());
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
		ParcelaID other = (ParcelaID) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (venda == null) {
			if (other.venda != null)
				return false;
		} else if (!venda.equals(other.venda))
			return false;
		return true;
	}
}
