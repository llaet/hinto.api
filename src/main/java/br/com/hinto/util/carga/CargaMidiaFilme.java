package br.com.hinto.util.carga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import br.com.hinto.entidade.Produtor;
import br.com.hinto.entidade.Genero;
import br.com.hinto.entidade.dto.ProdutorDTO;
import br.com.hinto.servico.impl.ProdutorServicoImpl;
import br.com.hinto.servico.impl.GeneroServicoImpl;
import br.com.hinto.servico.impl.MidiaServicoImpl;

//@Configuration
public class CargaMidiaFilme {
	
	@Value("${tmdb.url.base.filme.top}")
	private String URL_BASE_TOP_FILME;
	
	@Value("${tmdb.url.busca.filme.1}")
	private String INICIO_URL_BASE_TMDB_FILME;
	
	@Value("${tmdb.url.busca.filme.2}")
	private String FIM_URL_BASE_TMDB_FILME;
	
	@Value("${tmdb.url.banner.filme}")
	private String URL_TMDB_BANNER_FILME;
		
	@Value("${omdb.url.busca.filme}")
	private String URL_BASE_OMDB_FILME;
	
	private final Logger LOG = LoggerFactory.getLogger(CargaMidiaFilme.class);
	
	@Autowired
	private MidiaServicoImpl midiaServico;
	@Autowired
	private GeneroServicoImpl generoServico;
	@Autowired
	private ProdutorServicoImpl artistaServico;
	
	@Bean
	public CommandLineRunner runCargaFilmes(RestTemplate restTemplate) throws Exception {
		return args -> {
			FilmeJSON jsonResposta = restTemplate.getForObject(this.URL_BASE_TOP_FILME, FilmeJSON.class);
			LOG.info(jsonResposta.toString());
			//busca os generos de cada top filme encontrado
			jsonResposta.getResults().forEach(filme -> {
				filme.setPoster_path(this.URL_TMDB_BANNER_FILME.concat(filme.getPoster_path()));
				FilmeJSON jsonTMDB = restTemplate.getForObject(this.INICIO_URL_BASE_TMDB_FILME.concat(filme.getId().toString()).concat(FIM_URL_BASE_TMDB_FILME), 
						FilmeJSON.class);
				//LOG.info(jsonTMDB.toString());
				//mapeia os generos DTO para Genero				
				List<Genero> generos = this.generoServico.salvarTodos(jsonTMDB.getGenres());			
				filme.setGeneros(generos);
				/**
				LOG.info(this.URL_BASE_OMDB_FILME.concat(jsonTMDB.getImdb_id()));
				FilmeJSON jsonOMDB = restTemplate.getForObject(this.URL_BASE_OMDB_FILME.concat(jsonTMDB.getImdb_id()), FilmeJSON.class);
				LOG.info(jsonOMDB.toString());
				//trata as strings com nomes dos artistas				
				List<String> artistasString = Arrays.asList(jsonOMDB.getActors().split(","));
				String diretor = jsonOMDB.getDirector();
				
				List<ArtistaDTO> artistas = new ArrayList<>();
				
				artistas.add(new ArtistaDTO(diretor, "diretor"));
				artistasString.forEach(artista -> {
					artistas.add(new ArtistaDTO(artista.strip(), "atriz/ator"));
				});
				
				List<Artista> artistasSalvos = this.artistaServico.salvarTodos(artistas);
				
				filme.setArtistas(artistasSalvos);
				**/
				this.midiaServico.salvar(filme);				
			});
		};
	}
}
