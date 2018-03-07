package br.com.kalls.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "venda")
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "data", nullable = false)
	@NotNull(message = "Data da venda não pode ser nula")
	@Temporal(TemporalType.DATE)
	private Calendar data;

	@Min(message = "O valor total não pode ser menor que zero", value = 0)
	@NotNull(message = "O valor total não pode ser nulo")
	@Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)")
	private BigDecimal valorTotal;

	@Min(message = "A quantidade de parcelas não pode ser menor que 1", value = 1)
	@NotNull(message = "A quantidade de parcelas não pode ser nula")
	@Column(name = "parcelas", nullable = false)
	private Integer parcelas;

	@ManyToOne
	@NotNull(message = "O pessoa não pode ser nula")
	@JoinColumn(name = "pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_VENDA_PESSOA"), nullable = false)
	private PessoaFisica pessoa;

	@OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<ItemVenda> itensVenda = new ArrayList<ItemVenda>();

	@OneToMany(mappedBy = "parcelaID.venda", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)    
    private List<Parcela> listaParcelas = new ArrayList<>();

	public Venda() {
	}

	public Venda(Calendar data, BigDecimal valorTotal, Integer prcelas, PessoaFisica pessoa) {
		super();
		this.data = data;
		this.valorTotal = valorTotal;
		this.parcelas = prcelas;
		this.pessoa = pessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getParcelas() {
		return parcelas;
	}

	public void setParcelas(Integer prcelas) {
		this.parcelas = prcelas;
	}

	public PessoaFisica getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public List<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(List<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

	public void addItens(ItemVenda item) {
		item.setVenda(this);
		this.getItensVenda().add(item);
		this.setValorTotal(this.getValorTotal().add(item.getValorTotal()));
	}

	public void removeItens(Integer index) {
		ItemVenda vendaItem = this.getItensVenda().get(index);
		this.getValorTotal().subtract(vendaItem.getValorTotal());
		this.getItensVenda().remove(vendaItem);
	}
	
	public List<Parcela> getListaParcelas() {
		return listaParcelas;
	}
	
	public void parcelar() {
		BigDecimal valorParcela = this.valorTotal.divide(new BigDecimal(this.parcelas));
		for (int i = 1; i <= this.parcelas; i++) {
			ParcelaID parcelaID = new ParcelaID(this, i);
			Calendar vencimento = (Calendar) this.getData().clone();
			vencimento.add(Calendar.MONTH, i);
			Parcela parcela = new Parcela(parcelaID, valorParcela, vencimento, null, null);
			this.listaParcelas.add(parcela);
		}
	}

	public void setListaParcelas(List<Parcela> listaParcelas) {
		this.listaParcelas = listaParcelas;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", data=" + data + ", valorTotal=" + valorTotal + ", prcelas=" + parcelas + "]";
	}
}
