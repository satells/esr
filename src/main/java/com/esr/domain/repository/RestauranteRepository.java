package com.esr.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esr.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	List<Restaurante> findByTaxaFreteBetweenOrderByTaxaFreteDesc(BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findTop2ByNomeContaining(String nome);

	boolean existsByNome(String nome);

}
