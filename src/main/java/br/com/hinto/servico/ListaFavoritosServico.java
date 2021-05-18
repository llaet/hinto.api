package br.com.hinto.servico;

import br.com.hinto.entidade.ListaFavoritos;
import br.com.hinto.entidade.dto.ListaFavoritosDTO;

/**
 * criada por @llaet
 * Interface de regras de negócio da lista de favoritos do usuário.
 */
public interface ListaFavoritosServico {
	
	public ListaFavoritos salvar(ListaFavoritosDTO dto);
	public void deletarPorIdUsuario(Long idUsuario);
	public ListaFavoritos removerMidiaPorId(Long idUsuario, Long midiaID);
	public ListaFavoritos atualizarPorIdUsuario(Long midiaID, Long idUsuario);
	public ListaFavoritos encontrarPorIdUsuario(Long idUsuario);
	public ListaFavoritos listarFavoritosPorIdUsuario(Long id);
}
