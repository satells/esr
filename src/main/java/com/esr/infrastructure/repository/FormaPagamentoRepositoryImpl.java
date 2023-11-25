package com.esr.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.esr.domain.model.FormaPagamento;
import com.esr.domain.repository.FormaPagamentoRepository;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<FormaPagamento> listar() {
		return manager.createQuery("from FormaPagamento c", FormaPagamento.class).getResultList();
	}

	@Transactional
	@Override
	public FormaPagamento salvar(FormaPagamento FormaPagamento) {
		return manager.merge(FormaPagamento);
	}

	@Override
	public FormaPagamento buscar(Long id) {
		return manager.find(FormaPagamento.class, id);
	}

	@Transactional
	@Override
	public void remover(FormaPagamento FormaPagamento) {
		FormaPagamento busca = this.buscar(FormaPagamento.getId());
		manager.remove(busca);
	}
}