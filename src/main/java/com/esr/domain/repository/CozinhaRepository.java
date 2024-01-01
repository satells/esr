package com.esr.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esr.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	List<Cozinha> consultaPorNome(@Param("nome") String nome);

	/**
	 * findBy - prefixo
	 * 
	 */
	List<Cozinha> findByNomeContaining(String nome);

}
