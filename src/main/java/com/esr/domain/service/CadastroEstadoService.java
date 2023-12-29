package com.esr.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esr.domain.model.Estado;
import com.esr.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listar() {
		return estadoRepository.listar();
	}
}
