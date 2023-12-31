package com.esr.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esr.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
	// List<Cidade> findTop100ByOrderById();
}
