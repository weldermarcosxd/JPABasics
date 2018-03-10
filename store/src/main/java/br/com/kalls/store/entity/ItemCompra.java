package br.com.kalls.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_compra")
public class ItemCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "A quantidade não pode ser nula")
	@Min(message = "É necessário ao menos {min} item", value = 1)
	@Column(name = "quantidade", nullable = false, columnDefinition = "decimal(12,0)")
	private Double quantidade;

	@NotNull(message = "O valor unitário não pode ser nulo")
	@Min(message = "O valor unitário não deve ser menor que {min}", value = 0)
	@Column(name = "valor_unitario", nullable = false, columnDefinition = "decimal(12,0)")
	private BigDecimal valorUnitario;

	@NotNull(message = "O valor total não pode ser nulo")
	@Min(message = "O valor total não deve ser menor que {min}", value = 0)
	@Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,0)")
	private BigDecimal valorTotal;

	@NotNull(message = "A compra deve ser informada")
	@ManyToOne
	private Compra compra;

	@NotNull(message = "O produto deve ser informado")
	@ManyToOne
	@JoinColumn(name = "produto", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ITEM_COMPRA_PRODUTO"), nullable = false)
	private Produto produto;

	public ItemCompra() {
	}
	
	public ItemCompra(
			@NotNull(message = "A quantidade não pode ser nula") @Min(message = "É necessário ao menos {min} item", value = 1) Double quantidade,
			@NotNull(message = "O valor unitário não pode ser nulo") @Min(message = "O valor unitário não deve ser menor que {min}", value = 0) BigDecimal valorUnitario,
			@NotNull(message = "O valor total não pode ser nulo") @Min(message = "O valor total não deve ser menor que {min}", value = 0) BigDecimal valorTotal,
			@NotNull(message = "A compra deve ser informada") Compra compra,
			@NotNull(message = "O produto deve ser informado") Produto produto) {
		super();
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.compra = compra;
		this.produto = produto;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ItemCompra [id=" + id + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario
				+ ", valorTotal=" + valorTotal + "]";
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
		ItemCompra other = (ItemCompra) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
