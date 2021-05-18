package br.com.hinto.entidade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hinto.enumeracao.TipoMidia;

@Entity(name = "MIDIA")
public class Midia implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, length = 125)
	private String titulo;

	@Column(name = "IMAGEM_URL", nullable = true, length = 255)
	private String imagemURL;
	
	@Column(nullable = true, length = 725)
	private String sinopse;
	
	@Column(nullable = false)
	private TipoMidia tipo;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(nullable = true)
	private Date dataLancamento;
	
	@Column(nullable = true)
	private Boolean afinidade;
	
	@ManyToMany
	private List<Genero> generos = new ArrayList<>();
	
	@ManyToMany
	private List<Produtor> produtores = new ArrayList<>();
	
	public Midia() {}
	
	public List<Produtor> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

	public void setGeneros(List<Genero> generos) {
		this.generos = generos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getImagemURL() {
		return imagemURL;
	}

	public void setImagemURL(String imagemURL) {
		this.imagemURL = imagemURL;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public TipoMidia getTipo() {
		return tipo;
	}

	public void setTipo(TipoMidia tipo) {
		this.tipo = tipo;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public Boolean getAfinidade() {
		return afinidade;
	}

	public void setAfinidade(Boolean afinidade) {
		this.afinidade = afinidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((afinidade == null) ? 0 : afinidade.hashCode());
		result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
		result = prime * result + ((generos == null) ? 0 : generos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagemURL == null) ? 0 : imagemURL.hashCode());
		result = prime * result + ((produtores == null) ? 0 : produtores.hashCode());
		result = prime * result + ((sinopse == null) ? 0 : sinopse.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		Midia other = (Midia) obj;
		if (afinidade == null) {
			if (other.afinidade != null)
				return false;
		} else if (!afinidade.equals(other.afinidade))
			return false;
		if (dataLancamento == null) {
			if (other.dataLancamento != null)
				return false;
		} else if (!dataLancamento.equals(other.dataLancamento))
			return false;
		if (generos == null) {
			if (other.generos != null)
				return false;
		} else if (!generos.equals(other.generos))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (imagemURL == null) {
			if (other.imagemURL != null)
				return false;
		} else if (!imagemURL.equals(other.imagemURL))
			return false;
		if (produtores == null) {
			if (other.produtores != null)
				return false;
		} else if (!produtores.equals(other.produtores))
			return false;
		if (sinopse == null) {
			if (other.sinopse != null)
				return false;
		} else if (!sinopse.equals(other.sinopse))
			return false;
		if (tipo != other.tipo)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
}
