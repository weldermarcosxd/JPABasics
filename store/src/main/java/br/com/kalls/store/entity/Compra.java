package br.com.kalls.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "compra")
public class Compra implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CompraID compraID;

	@Temporal(TemporalType.DATE)
	@Column(name = "data", nullable = false)
	private Calendar data;

	@NotNull(message = "O valor da compra não pode ser nullo")
	@Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)")
	private BigDecimal valorTotal;

	@OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemCompra> itensCompra = new ArrayList<ItemCompra>();

	public Compra() {
	}

	public Compra(CompraID compraID, Calendar data,
			@NotNull(message = "O valor da compra não pode ser nullo") BigDecimal valorTotal) {
		super();
		this.compraID = compraID;
		this.data = data;
		this.valorTotal = valorTotal;
	}

	public CompraID getCompraID() {
		return compraID;
	}

	public void setCompraID(CompraID compraID) {
		this.compraID = compraID;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<ItemCompra> getItensCompra() {
		return itensCompra;
	}

	public void setItensCompra(List<ItemCompra> itensCompra) {
		this.itensCompra = itensCompra;
	}

	public void addItem(ItemCompra itemCompra) {
		itemCompra.setCompra(this);
		this.getItensCompra().add(itemCompra);
		this.setValorTotal(this.getValorTotal().add(itemCompra.getValorTotal()));
	}

	public void removeItem(Integer index) {
		ItemCompra itemCompra = this.getItensCompra().get(index);
		this.setValorTotal(this.getValorTotal().subtract(itemCompra.getValorTotal()));
		this.itensCompra.remove(itemCompra);
	}

	@Override
	public String toString() {
		return "Compra [compraID=" + compraID + ", data=" + data + ", valorTotal=" + valorTotal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compraID == null) ? 0 : compraID.hashCode());
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
		Compra other = (Compra) obj;
		if (compraID == null) {
			if (other.compraID != null)
				return false;
		} else if (!compraID.equals(other.compraID))
			return false;
		return true;
	}
}
