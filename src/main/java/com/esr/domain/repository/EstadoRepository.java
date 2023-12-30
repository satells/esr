package com.esr.domain.repository;

import java.util.List;

import com.esr.domain.model.Estado;

public interface EstadoRepository {
	List<Estado> listar();

	Estado buscar(Long id);

	Estado salvar(Estado Estado);

	void remover(Long id);

}
