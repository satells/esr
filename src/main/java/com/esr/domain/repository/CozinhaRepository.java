package com.esr.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esr.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

	@Query("select c from Cozinha c where nome like CONCAT('%', :nome ,'%')")
	List<Cozinha> consultaPorNome(@Param("nome") String nome);

	List<Cozinha> findByNomeContaining(String nome);
}
