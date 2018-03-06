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
@Table(name="item_venda")
public class ItemVenda implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Min(message="A quantidade não pode ser menor que 1", value = 1)
	@NotNull(message = "A quantidade não pode ser nula")
	@Column(name = "quantidade", nullable = false)
	private Double quantidade;
	
	@Min(message="O valor unitário não pode ser menor que zero", value = 0)
	@NotNull(message = "O valor unitário não pode ser nulo")
	@Column(name = "valor_unitario", nullable = false, columnDefinition = "decimal(12,2)")
	private BigDecimal valorUnitario;
	
	@Min(message="O valor total não pode ser menor que zero", value = 0)
	@NotNull(message = "O valor total não pode ser nulo")
	@Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)")
	private BigDecimal valorTotal;
	
	@ManyToOne
	@NotNull(message="O campo venda não pode ser nulo")
	@JoinColumn(name="venda", referencedColumnName="id",foreignKey = @ForeignKey(name = "FK_ITEM_VENDA"), nullable=false)
	private Venda venda;
	
	@ManyToOne
	@NotNull(message="O campo produto não pode ser nulo")
	@JoinColumn(name="produto", referencedColumnName="id",foreignKey = @ForeignKey(name = "FK_ITEM_PRODUTO"), nullable=false)
	private Produto produto;
	
	public ItemVenda() {
	}

	public ItemVenda(Double quantidade, BigDecimal valorUnitario, BigDecimal valorTotal, Venda venda,
			Produto produto) {
		super();
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.valorTotal = valorTotal;
		this.venda = venda;
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

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "VendaItens [id=" + id + ", quantidade=" + quantidade + ", valorUnitario=" + valorUnitario
				+ ", valorTotal=" + valorTotal + ", venda=" + venda + ", produto=" + produto + "]";
	}
}
