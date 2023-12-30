package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esr.domain.model.FormaPagamento;

public class FormaPagamentoRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	public List<FormaPagamento> listar() {
		return manager.createQuery("from FormaPagamento c", FormaPagamento.class).getResultList();
	}

	public FormaPagamento salvar(FormaPagamento FormaPagamento) {
		return manager.merge(FormaPagamento);
	}

	public FormaPagamento buscar(Long id) {
		return manager.find(FormaPagamento.class, id);
	}

	public void remover(FormaPagamento FormaPagamento) {
		FormaPagamento busca = this.buscar(FormaPagamento.getId());
		manager.remove(busca);
	}
}