package com.esr.domain.repository;

import java.util.List;

import com.esr.domain.model.Cidade;

public interface CidadeRepository {
	List<Cidade> listar();

	Cidade buscar(Long id);

	Cidade salvar(Cidade Cidade);

	void remover(Cidade Cidade);

}
