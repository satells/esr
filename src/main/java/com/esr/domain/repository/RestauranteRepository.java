package com.esr.domain.repository;

import java.util.List;

import com.esr.domain.model.Restaurante;

public interface RestauranteRepository {
	List<Restaurante> listar();

	Restaurante buscar(Long id);

	Restaurante salvar(Restaurante cozinha);

	void remover(Restaurante cozinha);

}
