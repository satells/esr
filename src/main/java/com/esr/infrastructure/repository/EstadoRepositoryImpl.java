package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;

import com.esr.domain.model.Estado;

@lombok.Generated
public class EstadoRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<Estado> listar() {
		return manager.createQuery("from Estado c", Estado.class).getResultList();
	}

	public Estado salvar(Estado Estado) {
		return manager.merge(Estado);
	}

	public Estado buscar(Long id) {
		return manager.find(Estado.class, id);
	}

	public void remover(Long id) {
		Estado estado = this.buscar(id);

		if (estado == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(estado);
	}
}