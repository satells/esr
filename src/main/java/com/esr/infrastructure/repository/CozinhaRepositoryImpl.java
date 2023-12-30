package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;

import com.esr.domain.model.Cozinha;

public class CozinhaRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<Cozinha> listar() {
		return manager.createQuery("from Cozinha c", Cozinha.class).getResultList();
	}

	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}

	public Cozinha buscar(Long id) {
		Cozinha cozinha = manager.find(Cozinha.class, id);
		return cozinha;
	}

	public void remover(Long id) {
		Cozinha cozinha = this.buscar(id);

		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}

		manager.remove(cozinha);
	}

	public List<Cozinha> consultaPorNome(String nome) {
		return manager.createQuery("from Cozinha where nome like CONCAT('%', :nome ,'%')", Cozinha.class)
				.setParameter("nome", nome).getResultList();
	}
}