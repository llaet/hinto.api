package br.com.hinto.entidade.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.hinto.entidade.Produtor;
import br.com.hinto.entidade.Genero;
import br.com.hinto.entidade.Midia;
import br.com.hinto.enumeracao.TipoMidia;

public class MidiaRetornadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

    private Long id;
    private String titulo;
    private String imagemURL;
    private String sinopse;
    private TipoMidia tipo;
    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dataLancamento;
    private Boolean favoritada;
    private List<Produtor> produtores = new ArrayList<>();
    private List<Genero> generos = new ArrayList<>();
    
    public MidiaRetornadoDTO() {}

    public MidiaRetornadoDTO(Midia midia) {
        this.id = midia.getId();
        this.titulo = midia.getTitulo();
        this.imagemURL = midia.getImagemURL();
        this.sinopse = midia.getSinopse();
        this.tipo = midia.getTipo();
        this.dataLancamento = midia.getDataLancamento();
        this.favoritada = midia.getAfinidade();
        this.produtores = midia.getProdutores();
        this.generos = midia.getGeneros();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Midia midia = (Midia) o;
        return Objects.equals(id, midia.getId()) &&
                Objects.equals(titulo, midia.getTitulo()) &&
                Objects.equals(dataLancamento, midia.getDataLancamento());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, dataLancamento);
    }
    
    public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getImagemURL() {
		return imagemURL;
	}

	public String getSinopse() {
		return sinopse;
	}

	public TipoMidia getTipo() {
		return tipo;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public Boolean getFavoritada() {
		return favoritada;
	}

	public List<Genero> getGeneros() {
		return generos;
	}

    public List<Produtor> getProdutores() {
		return produtores;
	}

	public void setProdutores(List<Produtor> produtores) {
		this.produtores = produtores;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setImagemURL(String imagemURL) {
        this.imagemURL = imagemURL;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public void setTipo(TipoMidia tipo) {
        this.tipo = tipo;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public void setFavoritada(Boolean favoritada) {
        this.favoritada = favoritada;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
