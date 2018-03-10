package br.com.kalls.store.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", nullable = false, length = 50)
	@Length(max = 50, message = "O nome não pode ter mais de {max} caracteres")
	@NotBlank(message = "O campo nome é obrigatório")
	@NotNull(message = "O nome não pode ser nulo")
	private String nome;

	@Min(message="O preço não pode ser menor que zero", value = 0)
	@NotNull(message = "O preço não pode ser nulo")
	@Column(name = "preco", nullable = false, columnDefinition = "decimal(12,2)")
	private Double preco;

	@NotNull(message = "O preço não pode ser nulo")
	@Column(name = "quantidade_estoque", nullable = false, columnDefinition = "decimal(12,2)")
	private Double quantidadeEstoque;

	@Column(name = "descricao", nullable = false, length = 50)
	@Length(max = 50, min = 10, message = "A descricao tem que estar entre {min} e {max} caracteres")
	@NotBlank(message = "O campo descricao é obrigatório")
	@NotNull(message = "O descricao não pode ser nula")
	private String descricao;
	
	@ManyToOne
	@NotNull(message="A categoria deve ser informada")
	@JoinColumn(name="categoria", referencedColumnName="id",foreignKey = @ForeignKey(name = "FK_PRODUTO_CATEGORIA"), nullable=false)
	private Categoria categoria;
	
	@ManyToOne
	@NotNull(message="A marca deve ser informada")
	@JoinColumn(name="marca", referencedColumnName="id",foreignKey = @ForeignKey(name = "FK_PRODUTO_MARCA"), nullable=false)
	private Marca marca;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "desejos", joinColumns = @JoinColumn(name = "produto", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "pessoa_fisica", referencedColumnName = "id", nullable = false), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "pessoa_fisica", "produto" }) })
	private List<PessoaFisica> desejam = new ArrayList<PessoaFisica>();
	
	@OneToMany(mappedBy="produto", cascade=CascadeType.ALL, orphanRemoval=true, fetch= FetchType.LAZY)
	private List<Foto> fotos = new ArrayList<Foto>();
	
	public Produto() {
	}
	
	public Produto(
			@Length(max = 50, message = "O nome não pode ter mais de {max} caracteres") @NotBlank(message = "O campo nome é obrigatório") @NotNull(message = "O nome não pode ser nulo") String nome,
			@Min(message = "O preço não pode ser menor que zero", value = 0) @NotNull(message = "O preço não pode ser nulo") Double preco,
			@NotNull(message = "O preço não pode ser nulo") Double quantidadeEstoque,
			@Length(max = 50, min = 10, message = "A descricao tem que estar entre {min} e {max} caracteres") @NotBlank(message = "O campo descricao é obrigatório") @NotNull(message = "O descricao não pode ser nula") String descricao,
			@NotNull(message = "A categoria deve ser informada") Categoria categoria,
			@NotNull(message = "A marca deve ser informada") Marca marca) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.quantidadeEstoque = quantidadeEstoque;
		this.descricao = descricao;
		this.categoria = categoria;
		this.marca = marca;
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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Double getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Double quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public List<PessoaFisica> getDesejam() {
		return desejam;
	}

	public void setDesejam(List<PessoaFisica> desejam) {
		this.desejam = desejam;
	}
	
	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}
	
	public void addFoto(Foto foto) {
		this.fotos.add(foto);
	}
	
	public void removeFoto(Integer index) {
		Foto foto = this.fotos.get(index);
		this.fotos.remove(foto);
	}
}
