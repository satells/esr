package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esr.domain.model.Restaurante;

public class RestauranteRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante c", Restaurante.class).getResultList();
	}

	public Restaurante salvar(Restaurante Restaurante) {
		return manager.merge(Restaurante);
	}

	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}

	public void remover(Restaurante Restaurante) {
		Restaurante busca = this.buscar(Restaurante.getId());
		manager.remove(busca);
	}
}