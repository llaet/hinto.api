package br.com.hinto.entidade.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class ListaFavoritosDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotNull
	private Long usuarioID;
	private Long midiaID;
	
	public ListaFavoritosDTO() {}
	
	public ListaFavoritosDTO(@NotNull Long usuarioID, Long midiaID) {
		super();
		this.usuarioID = usuarioID;
		this.midiaID = midiaID;
	}
	
	public Long getUsuarioID() {
		return usuarioID;
	}
	
	public void setUsuarioID(Long usuarioID) {
		this.usuarioID = usuarioID;
	}
	
	public Long getMidiaID() {
		return midiaID;
	}
	
	public void setMidiaID(Long midiaID) {
		this.midiaID = midiaID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((midiaID == null) ? 0 : midiaID.hashCode());
		result = prime * result + ((usuarioID == null) ? 0 : usuarioID.hashCode());
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
		ListaFavoritosDTO other = (ListaFavoritosDTO) obj;
		if (midiaID == null) {
			if (other.midiaID != null)
				return false;
		} else if (!midiaID.equals(other.midiaID))
			return false;
		if (usuarioID == null) {
			if (other.usuarioID != null)
				return false;
		} else if (!usuarioID.equals(other.usuarioID))
			return false;
		return true;
	}
	
	
}
