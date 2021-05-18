package br.com.hinto.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hinto.entidade.ListaFavoritos;
import br.com.hinto.entidade.dto.ListaFavoritosDTO;
import br.com.hinto.servico.ListaFavoritosServico;

/**
 * criada por @llaet
 * Classe controladora do endpoint da lista de interesses do usuário.
 */
@RestController
@RequestMapping("/favoritos")
public class ListaFavoritosControlador {
	
	@Autowired
	private ListaFavoritosServico servico;
	
	@PostMapping
	@CrossOrigin(origins = "*")
	public ListaFavoritos salvar(@Valid @RequestBody ListaFavoritosDTO dto) {
		return this.servico.salvar(dto);
	}

	@CrossOrigin(origins = "*")
	@DeleteMapping("/{idUsuario}")
	public void deletarPorIdUsuario(@PathVariable("idUsuario") Long idUsuario) {
		this.servico.deletarPorIdUsuario(idUsuario);
	}

	@CrossOrigin(origins = "*")
	@PutMapping("/{idUsuario}/{midiaID}")
	public ListaFavoritos atualizarPorIdUsuario(@PathVariable("midiaID") Long midiaID, @PathVariable("idUsuario") Long idUsuario) {
		return this.servico.atualizarPorIdUsuario(midiaID, idUsuario);
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/{idUsuario}")
	public ListaFavoritos encontrarPorIdUsuario(@PathVariable("idUsuario") Long idUsuario) {
		return this.servico.encontrarPorIdUsuario(idUsuario);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/favoritos/{idUsuario}")
	public ListaFavoritos listarFavoritosPorIdUsuario(@PathVariable("idUsuario") Long idUsuario) {
		return this.servico.listarFavoritosPorIdUsuario(idUsuario);
	}
	
	@CrossOrigin(origins = "*")
	@DeleteMapping("/{idUsuario}/{idMidia}")
	public ListaFavoritos removerPorIdUsuario(@PathVariable("idMidia") Long midiaID, @PathVariable("idUsuario") Long idUsuario) {
		return this.servico.removerMidiaPorId(idUsuario, midiaID);
	}
}
