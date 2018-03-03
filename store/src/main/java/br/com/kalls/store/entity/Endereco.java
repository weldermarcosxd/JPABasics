package br.com.kalls.store.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="endereco")
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nickname", nullable = false, length = 20)
	@Length(max = 20, message = "O nickname não pode ter mais de {max} caracteres")
	@NotBlank(message = "O campo nickname é obrigatório")
	@NotNull(message = "O nickname não pode ser nulo")
	private String nickname;

	@Column(name = "endereco", nullable = false, length = 50)
	@Length(max = 50, message = "O endereco não pode ter mais de {max} caracteres")
	@NotBlank(message = "O campo endereco é obrigatório")
	@NotNull(message = "O endereco não pode ser nulo")
	private String endereco;

	@Column(name = "numero", nullable = false)
	@NotNull(message = "O nome não pode ser nulo")
	private Integer numero;

	@Column(name = "complemento", length = 20)
	@Length(max = 20, message = "O complemento não pode ter mais de {max} caracteres")
	private String complemento;

	@Column(name = "cep", nullable = false, length = 8)
	@NotNull(message = "O nome não pode ser nulo")
	private Integer cep;

	@Column(name = "bairro", nullable = false, length = 30)
	@Length(max = 30, message = "O bairro não pode ter mais de {max} caracteres")
	@NotBlank(message = "O campo bairro é obrigatório")
	@NotNull(message = "O bairro não pode ser nulo")
	private String bairro;

	@Column(name = "referencia", length = 50)
	@Length(max = 50, message = "A referencia não pode ter mais de {max} caracteres")
	private String referencia;

	@NotNull(message = "A Pessoa não pode ser nula")
	@JoinColumn(name = "pessoa", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ENDERECO_PESSOA"), nullable = false)
	@ManyToOne
	private Pessoa pessoa;
	
	@NotNull(message = "O tipo do endereco não pode ser nulo")
	@JoinColumn(name = "tipo_endereco", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ENDERECO_TIPO"), nullable = false)
	@ManyToOne
	private TipoEndereco tipoEndereco;

	public Endereco() {
	}

	public Endereco(
			@Length(max = 20, message = "O nickname não pode ter mais de {max} caracteres") @NotBlank(message = "O campo nickname é obrigatório") @NotNull(message = "O nickname não pode ser nulo") String nickname,
			@Length(max = 50, message = "O endereco não pode ter mais de {max} caracteres") @NotBlank(message = "O campo endereco é obrigatório") @NotNull(message = "O endereco não pode ser nulo") String endereco,
			@NotNull(message = "O nome não pode ser nulo") Integer numero,
			@Length(max = 20, message = "O complemento não pode ter mais de {max} caracteres") String complemento,
			@NotNull(message = "O nome não pode ser nulo") Integer cep,
			@Length(max = 30, message = "O bairro não pode ter mais de {max} caracteres") @NotBlank(message = "O campo bairro é obrigatório") @NotNull(message = "O bairro não pode ser nulo") String bairro,
			@Length(max = 50, message = "A referencia não pode ter mais de {max} caracteres") String referencia,
			@NotNull(message = "A Pessoa não pode ser nula") Pessoa pessoa, TipoEndereco tipoEndereco) {
		super();
		this.nickname = nickname;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.cep = cep;
		this.bairro = bairro;
		this.referencia = referencia;
		this.pessoa = pessoa;
		this.tipoEndereco = tipoEndereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public TipoEndereco getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(TipoEndereco tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", nickname=" + nickname + ", endereco=" + endereco + ", numero=" + numero
				+ ", complemento=" + complemento + ", cep=" + cep + ", bairro=" + bairro + ", referencia=" + referencia
				+ ", pessoa=" + pessoa + "]";
	}

}
