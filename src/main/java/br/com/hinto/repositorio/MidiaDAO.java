package br.com.hinto.repositorio;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hinto.entidade.Midia;

/**
 * criada por 
 * Interface Repositório Usuario.
 */
@Repository
public interface MidiaDAO extends JpaRepository<Midia, Long> {

	@Transactional(readOnly = true)
	List<Midia> findByTituloContainingIgnoreCaseOrSinopseContainingIgnoreCase(String stringBusca, String stringBusca2);
}
