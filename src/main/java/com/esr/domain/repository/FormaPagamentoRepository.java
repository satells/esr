package com.esr.domain.repository;

import java.util.List;

import com.esr.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {
	List<FormaPagamento> listar();

	FormaPagamento buscar(Long id);

	FormaPagamento salvar(FormaPagamento FormaPagamento);

	void remover(FormaPagamento FormaPagamento);

}
