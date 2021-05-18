package br.com.hinto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.hinto.entidade.ListaFavoritos;

@Repository
public interface ListaFavoritosDAO extends JpaRepository<ListaFavoritos, Long>{
	
	@Transactional(readOnly = true)
	@Query(value = "SELECT * FROM LISTA_FAVORITOS WHERE USUARIO_ID = :usuarioId", nativeQuery = true)
	ListaFavoritos findByUsuarioId(@Param("usuarioId") Long usuarioId);
}
