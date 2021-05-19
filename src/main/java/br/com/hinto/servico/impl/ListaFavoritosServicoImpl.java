package br.com.hinto.servico.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hinto.entidade.ListaFavoritos;
import br.com.hinto.entidade.Midia;
import br.com.hinto.entidade.Usuario;
import br.com.hinto.entidade.dto.ListaFavoritosDTO;
import br.com.hinto.excecao.DadosIncorretosException;
import br.com.hinto.repositorio.ListaFavoritosDAO;
import br.com.hinto.servico.ListaFavoritosServico;
import br.com.hinto.servico.MidiaServico;
import br.com.hinto.servico.UsuarioServico;

@Service
public class ListaFavoritosServicoImpl implements ListaFavoritosServico {
	
	@Autowired
	private ListaFavoritosDAO dao;
	@Autowired
	private MidiaServico midiaServico;
	@Autowired
	private UsuarioServico usuarioServico;

	@Override
	public ListaFavoritos salvar(ListaFavoritosDTO dto) {
		ListaFavoritos lista = this.toListaInteresse(dto);
		
		ListaFavoritos listaBuscada = this.dao.findByUsuarioId(dto.getUsuarioID());
		
		if (listaBuscada == null) {
			this.dao.saveAndFlush(lista);
		}
		
		return lista;
	}
	
	private void salvar(ListaFavoritos lista) {
		this.dao.saveAndFlush(lista);
	}
	
	/**
	 * mapeia um dto de lista de interesse em lista de interesse.
	 * @param dto
	 * @return objeto lista de interesse
	 */
	private ListaFavoritos toListaInteresse(ListaFavoritosDTO dto) {
		ListaFavoritos listaInteresse = new ListaFavoritos();
		
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
	public ListaFavoritos atualizarPorIdUsuario(Long midiaID, Long idUsuario) {
		ListaFavoritos lista = this.dao.findByUsuarioId(idUsuario);
		
		if (lista != null) {
			Midia midia = this.toMidia(midiaID);
			if (!lista.getMidias().contains(midia)) {
				lista.setMidia(midia);
			}
			lista.setDataAtualizacao(LocalDateTime.now());
			this.salvar(lista);
			return lista;
		}
        throw new DadosIncorretosException("Não há registros de listas de favoritos com ID informado.");
	}

	@Override
	public void deletarPorIdUsuario(Long idUsuario) {
		ListaFavoritos lista = this.encontrarPorIdUsuario(idUsuario);
		
		lista.getMidias().clear();
		this.salvar(lista);
	}

	@Override
	public ListaFavoritos encontrarPorIdUsuario(Long idUsuario) {
		ListaFavoritos lista = this.dao.findByUsuarioId(idUsuario);
		
		if (lista != null) {
			return lista;
		}
        throw new DadosIncorretosException("Lista de interesse não encontrada!");
	}

	@Override
	public ListaFavoritos listarFavoritosPorIdUsuario(Long id) {
		ListaFavoritos lista = this.dao.findByUsuarioId(id);
		
		return lista;
	}

	@Override
	public ListaFavoritos removerMidiaPorId(Long idUsuario, Long midiaID) {
		ListaFavoritos favoritos = this.dao.findByUsuarioId(idUsuario);
		
		Midia midiaEncontrada = favoritos.getMidias().stream()
				.filter(midia -> midia.getId().equals(midiaID)).findFirst().orElse(null);
		
		if (midiaEncontrada != null) {
			favoritos.getMidias().remove(midiaEncontrada);
		}
		this.dao.saveAndFlush(favoritos);
		
		return favoritos;
	}

}
