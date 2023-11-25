package com.esr.domain.repository;

import java.util.List;

import com.esr.domain.model.Permissao;

public interface PermissaoRepository {
	List<Permissao> listar();

	Permissao buscar(Long id);

	Permissao salvar(Permissao Permissao);

	void remover(Permissao Permissao);

}
