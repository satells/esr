package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.esr.domain.model.Estado;
import com.esr.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Estado> listar() {
		return manager.createQuery("from Estado c", Estado.class).getResultList();
	}

	@Transactional
	@Override
	public Estado salvar(Estado Estado) {
		return manager.merge(Estado);
	}

	@Override
	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	@Transactional
	@Override
	public void remover(Estado Estado) {
		Estado busca = this.buscar(Estado.getId());
		manager.remove(busca);
	}
}