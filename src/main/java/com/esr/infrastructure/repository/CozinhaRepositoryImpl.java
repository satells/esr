package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.esr.domain.model.Cozinha;
import com.esr.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha c", Cozinha.class).getResultList();
	}

	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}

	@Transactional
	@Override
	public void remover(Long id) {
		Cozinha cozinha = this.buscar(id);

		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(id);
	}
}