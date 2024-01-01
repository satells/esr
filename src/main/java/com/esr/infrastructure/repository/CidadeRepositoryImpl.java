package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esr.domain.model.Cidade;

@lombok.Generated

public class CidadeRepositoryImpl {

	@PersistenceContext

	private EntityManager manager;

	public List<Cidade> listar() {
		return manager.createQuery("from Cidade c", Cidade.class).getResultList();
	}

	public Cidade salvar(Cidade Cidade) {
		return manager.merge(Cidade);
	}

	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}

	public void remover(Cidade Cidade) {
		Cidade busca = this.buscar(Cidade.getId());
		manager.remove(busca);
	}
}