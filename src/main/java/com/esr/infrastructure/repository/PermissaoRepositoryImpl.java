package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esr.domain.model.Permissao;

@lombok.Generated
public class PermissaoRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<Permissao> listar() {
		return manager.createQuery("from Permissao c", Permissao.class).getResultList();
	}

	public Permissao salvar(Permissao Permissao) {
		return manager.merge(Permissao);
	}

	public Permissao buscar(Long id) {
		return manager.find(Permissao.class, id);
	}

	public void remover(Permissao Permissao) {
		Permissao busca = this.buscar(Permissao.getId());
		manager.remove(busca);
	}
}