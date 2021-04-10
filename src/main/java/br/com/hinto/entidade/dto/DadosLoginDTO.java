package br.com.hinto.entidade.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class DadosLoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @NotNull
    private String email;
    @NotNull
    private String senha;
    
    public DadosLoginDTO() {}
    
    public DadosLoginDTO(String email,String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
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
		DadosLoginDTO other = (DadosLoginDTO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}
}
