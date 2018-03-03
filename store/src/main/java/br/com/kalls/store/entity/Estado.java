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
@Table(name="estado")
public class Estado implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome", nullable = false, unique = true, length = 50)
	@Length(max=50, message="O nome não pode ter mais de {max} caracteres")
	@NotBlank(message="O campo nome é obrigatório")
	@NotNull(message="O nome não pode ser nulo")
	private String nome;
	
	@Column(name="uf", nullable = false, unique = true, length = 2)
	@Length(max=2, message="A uf não pode ter mais de {max} caracteres")
	@NotBlank(message="O campo uf é obrigatório")
	@NotNull(message="O campo uf não pode ser nulo")
	private String uf;
	
	@ManyToOne
	@NotNull(message="O campo país não pode ser nulo")
	@JoinColumn(name="pais", referencedColumnName="id",foreignKey = @ForeignKey(name = "FK_ESTADO_PAIS"), nullable=false)
	private Pais pais;

	public Estado() {
	}
	
	public Estado(String nome, String uf, Pais pais) {
		super();
		this.nome = nome;
		this.uf = uf;
		this.pais = pais;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public Pais getPais() {
		return pais;
	}
	
	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Pais [id=" + id + ", nome=" + nome + ", uf=" + uf + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((uf == null) ? 0 : uf.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Estado other = (Estado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (uf == null) {
			if (other.uf != null)
				return false;
		} else if (!uf.equals(other.uf))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
}
