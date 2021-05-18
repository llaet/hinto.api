package br.com.hinto.servico.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.hinto.entidade.Usuario;
import br.com.hinto.entidade.dto.ListaFavoritosDTO;
import br.com.hinto.entidade.dto.ListaInteresseDTO;
import br.com.hinto.entidade.dto.UsuarioCriadoDTO;
import br.com.hinto.entidade.dto.UsuarioRetornadoDTO;
import br.com.hinto.enumeracao.Perfil;
import br.com.hinto.enumeracao.Sexo;
import br.com.hinto.excecao.DadosIncorretosException;
import br.com.hinto.repositorio.UsuarioDAO;
import br.com.hinto.servico.ListaFavoritosServico;
import br.com.hinto.servico.ListaInteresseServico;
import br.com.hinto.servico.UsuarioServico;

/**
 * criada por @llaet
 * Classe de implementação das regras de negócio Usuario.
 */
@Service
public class UsuarioServicoImpl implements UsuarioServico, UserDetailsService {
	
	@Autowired
	private UsuarioDAO dao;
	@Autowired
	private ListaInteresseServico interesseServico;
	@Autowired
	private ListaFavoritosServico favoritosServico;

	@Override
	public UsuarioRetornadoDTO salvar(UsuarioCriadoDTO dto) {
		//cria usuario a partir do dto recebido
		Usuario usuario = this.toUsuario(dto);
		
		Usuario usuarioSalvo = this.dao.saveAndFlush(usuario);
		
		this.criarListaInteresse(usuarioSalvo);
		this.criarListaFavoritos(usuarioSalvo);
		
		return this.toDTO(usuarioSalvo);
	}
	
	private void criarListaInteresse(Usuario usuario) {
		ListaInteresseDTO dto = new ListaInteresseDTO();
		dto.setUsuarioID(usuario.getId());
		this.interesseServico.salvar(dto);
	}
	
	private void criarListaFavoritos(Usuario usuario) {
		ListaFavoritosDTO dto = new ListaFavoritosDTO();
		dto.setUsuarioID(usuario.getId());
		this.favoritosServico.salvar(dto);
	}
	
	/**
	 * cria um objeto Usuario a partir de um objeto de transferencia.
	 * @param dto
	 * @return um objeto Usuario.
	 */
	private Usuario toUsuario(UsuarioCriadoDTO dto) {
		Usuario usuario = new Usuario();

		usuario.setNome(dto.getNome());
		usuario.setEmail(dto.getEmail());
		usuario.setSenha(dto.getSenha());
		usuario.setDataNascimento(dto.getDataNascimento());
		usuario.setPerfil(Perfil.USUARIO);
		usuario.setAtivo(Boolean.TRUE);
		usuario.setDataCriacao(LocalDateTime.now());
		usuario.setUltimoAcesso(LocalDateTime.now());
		usuario.setSexo(dto.getSexo() == null ? Sexo.NAO_INFORMADO : dto.getSexo());
		
		return usuario;
	}
	
	@Override
	public Usuario findById(Long id) {		
		Usuario usuario = this.dao.findById(id).get();
		
		if (usuario == null) {
			throw new DadosIncorretosException("Usuário não encontrado!");
		}
		return usuario;
	}
	
	/**
	 * cria um objeto de transferencia a partir da entidade.
	 * @param usuario
	 * @return dto de Usuario.
	 */
	private UsuarioRetornadoDTO toDTO(Usuario usuario) {
		return new UsuarioRetornadoDTO(usuario);		
	}

	@Override
	public void deletar(Long idUsuario) {
		//verifica se o usuario existe
		this.encontrarPorId(idUsuario);
		
		this.dao.deleteById(idUsuario);
	}

	@Override
	public UsuarioRetornadoDTO atualizar(UsuarioCriadoDTO usuario, Long idUsuario) {
		// verifica se o usuário existe pelo método find by id
		this.encontrarPorId(idUsuario);
		
		Usuario entidadeUsuario = this.dao.findById(idUsuario).get();
		entidadeUsuario = this.atualizar(usuario, entidadeUsuario);
		
		this.dao.saveAndFlush(entidadeUsuario);
		
		return this.toDTO(entidadeUsuario);		
	}
	
	/**
	 * método interno para atualizar os campos possíveis, conforme interface com usuário.
	 * @param dto
	 * @param usuario
	 * @return objeto Usuario com campos atualizados.
	 */
	private Usuario atualizar(UsuarioCriadoDTO dto, Usuario usuario) {
		if (dto.getDataNascimento() != null) {
			usuario.setDataNascimento(dto.getDataNascimento());
		}
		if (dto.getNome() != null) {
			usuario.setNome(dto.getNome());
		}
		if (dto.getSenha() != null) {
			usuario.setSenha(dto.getSenha());
		}
		if (dto.getSexo() != null) {
			usuario.setSexo(dto.getSexo());
		}
		return usuario;
	}

	@Override
	public List<UsuarioRetornadoDTO> encontrarTodos() {
		//uso da API stream do Java. Busca todos usuarios, mapeia um por um criando o dto e insere em uma lista.
		List<UsuarioRetornadoDTO> usuarios = this.dao.findAll().stream()
				.map(usuario -> new UsuarioRetornadoDTO(usuario))
				.collect(Collectors.toList());
		
		return usuarios;
	}

	@Override
	public UsuarioRetornadoDTO encontrarPorId(Long idUsuario) {
		
	
		Usuario usuario = this.dao.findById(idUsuario).get();
	
		if (usuario == null) {
			throw new DadosIncorretosException("Usuário não encontrado!");
		}
		return this.toDTO(usuario);
	}
	
	@Override
	public UsuarioRetornadoDTO autenticarUsuario(String email, String senha) {
		Usuario usuario = this.dao.findByEmail(email);	
		
		if (usuario == null) {
			throw new DadosIncorretosException("Email Incorreto!");
		}
		if (usuario.getSenha().equals(senha)) {
			return this.toDTO(usuario);
		}		
		throw new DadosIncorretosException("Senha Incorreta!");
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return this.dao.findByEmail(email);
	}
}
