package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.esr.domain.model.Restaurante;
import com.esr.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> listar() {
		return manager.createQuery("from Restaurante c", Restaurante.class).getResultList();
	}

	@Transactional
	@Override
	public Restaurante salvar(Restaurante Restaurante) {
		return manager.merge(Restaurante);
	}

	@Override
	public Restaurante buscar(Long id) {
		return manager.find(Restaurante.class, id);
	}

	@Transactional
	@Override
	public void remover(Restaurante Restaurante) {
		Restaurante busca = this.buscar(Restaurante.getId());
		manager.remove(busca);
	}
}