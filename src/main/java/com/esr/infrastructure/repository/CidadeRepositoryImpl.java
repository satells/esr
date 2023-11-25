package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.esr.domain.model.Cidade;
import com.esr.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Cidade> listar() {
		return manager.createQuery("from Cidade c", Cidade.class).getResultList();
	}

	@Transactional
	@Override
	public Cidade salvar(Cidade Cidade) {
		return manager.merge(Cidade);
	}

	@Override
	public Cidade buscar(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Transactional
	@Override
	public void remover(Cidade Cidade) {
		Cidade busca = this.buscar(Cidade.getId());
		manager.remove(busca);
	}
}