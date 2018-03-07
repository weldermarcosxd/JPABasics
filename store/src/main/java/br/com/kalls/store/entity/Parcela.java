package br.com.kalls.store.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="parcela")
public class Parcela implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ParcelaID parcelaID;
	
	@Min(message="O valor da parcela não pode ser menor que {min}", value = 0)
	@NotNull(message="O valor da parcela não pode ser nulo")
	@Column(name="valor", nullable=false, columnDefinition="decimal(12,2)")
	private BigDecimal valor;
	
	@Temporal(TemporalType.DATE)
	@NotNull(message="A data de vencimento da parcela não pode ser nula")
	@Column(name="data_vencimento", nullable=false)
	private Calendar dataVencimento;
	
	@Min(message="O valor do pagamento da parcela não pode ser menor que {min}", value=0)
	@Column(name="valor_pagamento", columnDefinition="decimal(12,2)")
	private BigDecimal valorPagamento;
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_pagamento")
	private Calendar dataPagamento;
	
	public Parcela() {
	}

	public Parcela(ParcelaID parcelaID,
			@Min(message = "O valor da parcela não pode ser menor que {min}", value = 0) @NotNull(message = "O valor da parcela não pode ser nulo") BigDecimal valor,
			@NotNull(message = "A data de vencimento da parcela não pode ser nula") Calendar dataVencimento,
			@Min(message = "O valor do pagamento da parcela não pode ser menor que {min}", value = 0) BigDecimal valorPagamento,
			Calendar dataPagamento) {
		super();
		this.parcelaID = parcelaID;
		this.valor = valor;
		this.dataVencimento = dataVencimento;
		this.valorPagamento = valorPagamento;
		this.dataPagamento = dataPagamento;
	}

	public ParcelaID getParcelaID() {
		return parcelaID;
	}

	public void setParcelaID(ParcelaID parcelaID) {
		this.parcelaID = parcelaID;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Calendar getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Calendar dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorPagamento() {
		return valorPagamento;
	}

	public void setValorPagamento(BigDecimal valorPagamento) {
		this.valorPagamento = valorPagamento;
	}

	public Calendar getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Calendar dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	@Override
	public String toString() {
		return "Parcela [parcelaID=" + parcelaID + ", valor=" + valor + ", dataVencimento=" + dataVencimento
				+ ", valorPagamento=" + valorPagamento + ", dataPagamento=" + dataPagamento + "]";
	}
}
