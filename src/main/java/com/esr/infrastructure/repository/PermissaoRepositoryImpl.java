package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.esr.domain.model.Permissao;
import com.esr.domain.repository.PermissaoRepository;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permissao> listar() {
		return manager.createQuery("from Permissao c", Permissao.class).getResultList();
	}

	@Transactional
	@Override
	public Permissao salvar(Permissao Permissao) {
		return manager.merge(Permissao);
	}

	@Override
	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}

	@Transactional
	@Override
	public void remover(Permissao Permissao) {
		Permissao busca = this.buscar(Permissao.getId());
		manager.remove(busca);
	}
}