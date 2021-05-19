package br.com.hinto.servico;

import br.com.hinto.entidade.ListaInteresse;
import br.com.hinto.entidade.dto.ListaInteresseDTO;

/**
 * criada por @llaet
 * Interface de regras de negócio da lista de interesse do usuário.
 */
public interface ListaInteresseServico {
	
	public ListaInteresse salvar(ListaInteresseDTO dto);
	public void deletarPorIdUsuario(Long idUsuario);
	public ListaInteresse atualizarPorIdUsuario(Long midiaID, Long idUsuario);
	public ListaInteresse encontrarPorIdUsuario(Long idUsuario);
	public void deletarListaPorId(Long id);
}
