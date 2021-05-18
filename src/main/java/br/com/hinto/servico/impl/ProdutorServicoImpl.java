package br.com.hinto.servico.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hinto.entidade.Produtor;
import br.com.hinto.entidade.dto.ProdutorDTO;
import br.com.hinto.excecao.DadosIncorretosException;
import br.com.hinto.repositorio.ProdutorDAO;
import br.com.hinto.servico.ProdutorServico;

@Service
public class ProdutorServicoImpl implements ProdutorServico {
	
	@Autowired
	private ProdutorDAO dao;

	@Override
	public List<Produtor> salvarTodos(List<ProdutorDTO> dtos) {
		//mapeia dto's para objeto Produtor
		List<Produtor> produtores = dtos.stream().map(dto -> this.toProdutor(dto))
				.collect(Collectors.toList());
		
		List<Produtor> produtoresCriados = new ArrayList<>();
		
		produtores.forEach(artista -> {
			Produtor artistaBuscado = this.encontrarProdutorPorNome(artista.getNome());
			if (artistaBuscado == null) {
				produtoresCriados.add(artista);
			} else {
				produtoresCriados.add(artistaBuscado);
			}
		});
		
		return this.dao.saveAll(produtoresCriados);
	}
	
	private Produtor toProdutor(ProdutorDTO dto) {
		Produtor artista = new Produtor();
		
		artista.setId(null);
		artista.setNome(dto.getNome());
		
		return artista;
	}

	@Override
	public Produtor encontrarProdutorPorNome(String nome) {
		return this.dao.findByNome(nome);
	}

	@Override
	public Produtor salvar(ProdutorDTO dto) {
		Produtor produtor = this.toProdutor(dto);
		
		produtor = this.dao.saveAndFlush(produtor);
		
		return produtor;
	}

	@Override
	public void deletarPorId(Long id) {
		this.encontrarPorId(id);
		
		this.dao.deleteById(id);
	}

	@Override
	public Produtor atualizarPorId(Long id, ProdutorDTO dto) {
		Produtor produtor = this.encontrarPorId(id);
		
		produtor.setNome(dto.getNome());
		
		return this.dao.saveAndFlush(produtor);
		
	}

	@Override
	public List<Produtor> encontrarTodos() {
		return this.dao.findAll();
	}
	
	private Produtor encontrarPorId(Long id) {
		Produtor produtor = this.dao.findById(id).get();
		
		if (produtor != null) {
			return produtor;
		}
		throw new DadosIncorretosException("Produtor não encontrado pelo ID informado.");
	}
}
