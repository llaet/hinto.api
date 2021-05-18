package br.com.hinto.servico.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hinto.entidade.Genero;
import br.com.hinto.entidade.Midia;
import br.com.hinto.entidade.dto.MidiaAnimeCriadoDTO;
import br.com.hinto.entidade.dto.MidiaFilmeCriadoDTO;
import br.com.hinto.entidade.dto.MidiaRetornadoDTO;
import br.com.hinto.excecao.DadosIncorretosException;
import br.com.hinto.repositorio.MidiaDAO;
import br.com.hinto.servico.MidiaServico;

@Service
public class MidiaServicoImpl implements MidiaServico {
    @Autowired
    private MidiaDAO dao;


    private MidiaRetornadoDTO toDTO(Midia midia){
        return new MidiaRetornadoDTO(midia);
    }

    private Midia toMidia(MidiaAnimeCriadoDTO dto) {
        Midia midia = new Midia();

        //midia.setAfinidade(dto.getAfinidade());
        midia.setDataLancamento(dto.getStart_date());
        midia.setTipo(dto.getTipo());
        midia.setImagemURL(dto.getImage_url());
        midia.setSinopse(dto.getSynopsis());
        midia.setTitulo(dto.getTitle());
        midia.setProdutores(dto.getProdutores());
        midia.setGeneros(dto.getGeneros());

        return midia;
    }

    @Override
    public MidiaRetornadoDTO salvar(MidiaAnimeCriadoDTO dto) {
        Midia midia = this.toMidia(dto);
        this.dao.saveAndFlush(midia);

        return this.toDTO(midia);
    }
    
    private Midia toMidia(MidiaFilmeCriadoDTO dto) {
        Midia midia = new Midia();

        //midia.setAfinidade(dto.getAfinidade());
        midia.setDataLancamento(dto.getRelease_date());
        midia.setTipo(dto.getTipo());
        midia.setImagemURL(dto.getPoster_path());
        midia.setSinopse(dto.getOverview());
        midia.setTitulo(dto.getTitle());
        midia.setProdutores(dto.getProdutores());
        midia.setGeneros(dto.getGeneros());

        return midia;
    }

    @Override
    public MidiaRetornadoDTO salvar(MidiaFilmeCriadoDTO dto) {
        Midia midia = this.toMidia(dto);
        this.dao.saveAndFlush(midia);

        return this.toDTO(midia);
    }
    
    public Midia toMidia(MidiaRetornadoDTO dto) {
        Midia midia = new Midia();

        //midia.setAfinidade(dto.getAfinidade());
        midia.setDataLancamento(dto.getDataLancamento());
        midia.setTipo(dto.getTipo());
        midia.setImagemURL(dto.getImagemURL());
        midia.setSinopse(dto.getSinopse());
        midia.setTitulo(dto.getTitulo());
        midia.setProdutores(dto.getProdutores());
        midia.setGeneros(dto.getGeneros());

        return midia;
    }

    @Override
    public void deletar(Long idMidia) {
        this.encontrarPorId(idMidia);

        this.dao.deleteById(idMidia);
    }

    @Override
    public MidiaRetornadoDTO atualizar(MidiaAnimeCriadoDTO midia, Long idMidia) {
        this.encontrarPorId(idMidia);

        Midia midiaEntidade = this.dao.findById(idMidia).get();
        midiaEntidade = this.atualizar(midia, midiaEntidade);

        this.dao.saveAndFlush(midiaEntidade);

        return this.toDTO(midiaEntidade);
    }

    @Override
    public List<MidiaRetornadoDTO> encontrarTodos() {
        List<MidiaRetornadoDTO> midias = this.dao.findAll().stream()
                .map(midia -> new MidiaRetornadoDTO(midia))
                .collect(Collectors.toList());

        return midias;
    }

    @Override
    public MidiaRetornadoDTO encontrarPorId(Long idMidia) {
        Midia midia = this.dao.findById(idMidia).get();

        if (midia == null) {
            throw new DadosIncorretosException("Mídia não encontrada!");
        }
        return this.toDTO(midia);
    }
    
    public Midia findById(Long midiaID) {
        Midia midia = this.dao.findById(midiaID).get();

        if (midia == null) {
            throw new DadosIncorretosException("Mídia não encontrada!");
        }
        return midia;
    }

    private Midia atualizar(MidiaAnimeCriadoDTO dto, Midia midia) {
        if (dto.getSynopsis() != null) {
            midia.setSinopse(dto.getSynopsis());
        }
        if (dto.getStart_date() != null) {
            midia.setDataLancamento(dto.getStart_date());
        }
        if (dto.getImage_url() != null) {
            midia.setImagemURL(dto.getImage_url());
        }
        return midia;
    }

	@Override
	public List<MidiaRetornadoDTO> buscarPorString(String stringBusca) {
		List<Midia> midiasBuscadasPorString = this.dao.findByTituloContainingIgnoreCaseOrSinopseContainingIgnoreCase(stringBusca, stringBusca);
		
		return midiasBuscadasPorString.stream().map(midia -> this.toDTO(midia))
				.collect(Collectors.toList());
	}
}
