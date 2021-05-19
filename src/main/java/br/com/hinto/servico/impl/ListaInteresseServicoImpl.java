package br.com.hinto.servico.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hinto.entidade.ListaInteresse;
import br.com.hinto.entidade.Midia;
import br.com.hinto.entidade.Usuario;
import br.com.hinto.entidade.dto.ListaInteresseDTO;
import br.com.hinto.excecao.DadosIncorretosException;
import br.com.hinto.repositorio.ListaInteresseDAO;
import br.com.hinto.servico.ListaInteresseServico;
import br.com.hinto.servico.MidiaServico;
import br.com.hinto.servico.UsuarioServico;

@Service
public class ListaInteresseServicoImpl implements ListaInteresseServico {
	
	@Autowired
	private ListaInteresseDAO dao;
	@Autowired
	private MidiaServico midiaServico;
	@Autowired
	private UsuarioServico usuarioServico;

	@Override
	public ListaInteresse salvar(ListaInteresseDTO dto) {
		ListaInteresse lista = this.toListaInteresse(dto);
		
		ListaInteresse listaBuscada = this.dao.findByUsuarioId(dto.getUsuarioID());
		
		if (listaBuscada == null) {
			this.dao.saveAndFlush(lista);
		}
		
		return lista;
	}
	
	private void salvar(ListaInteresse lista) {
		this.dao.saveAndFlush(lista);
	}
	
	/**
	 * mapeia um dto de lista de interesse em lista de interesse.
	 * @param dto
	 * @return objeto lista de interesse
	 */
	private ListaInteresse toListaInteresse(ListaInteresseDTO dto) {
		ListaInteresse listaInteresse = new ListaInteresse();
		
		listaInteresse.setDataCriacao(LocalDateTime.now());
		listaInteresse.setDataAtualizacao(LocalDateTime.now());
		Long midiaID = dto.getMidiaID();
		if (midiaID != null) {
			Midia midia = toMidia(midiaID);
			listaInteresse.setMidia(midia);
		}
		Usuario usuario = this.usuarioServico.findById(dto.getUsuarioID());
		listaInteresse.setUsuario(usuario);
		
		return listaInteresse;
	}

	private Midia toMidia(Long midiaID) {
		Midia midia = this.midiaServico.findById(midiaID);
		return midia;
	}
	
	@Override
	public ListaInteresse atualizarPorIdUsuario(Long midiaID, Long idUsuario) {
		ListaInteresse lista = this.dao.findByUsuarioId(idUsuario);
		
		if (lista != null) {
			Midia midia = this.toMidia(midiaID);
			if (!lista.getMidias().contains(midia)) {
				lista.setMidia(midia);
			}
			lista.setDataAtualizacao(LocalDateTime.now());
			this.salvar(lista);
			return lista;
		}
        throw new DadosIncorretosException("Não há registros de listas de interesse com ID informado.");
	}

	@Override
	public void deletarPorIdUsuario(Long idUsuario) {
		ListaInteresse lista = this.encontrarPorIdUsuario(idUsuario);
		
		lista.getMidias().clear();
		this.salvar(lista);
	}

	@Override
	public ListaInteresse encontrarPorIdUsuario(Long idUsuario) {
		ListaInteresse lista = this.dao.findByUsuarioId(idUsuario);
		
		if (lista != null) {
			return lista;
		}
        throw new DadosIncorretosException("Lista de interesse não encontrada!");
	}

	@Override
	public void deletarListaPorId(Long id) {
		this.dao.deleteById(id);	
	}
}
