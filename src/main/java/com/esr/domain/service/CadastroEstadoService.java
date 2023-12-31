package com.esr.domain.service;

import static java.lang.String.format;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.esr.domain.exception.EntidadeEmUsoException;
import com.esr.domain.exception.EntidadeNaoEcontrataException;
import com.esr.domain.model.Estado;
import com.esr.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	public Estado buscar(Long id) {
		Estado estado = estadoRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEcontrataException(String.format("Estado não encontrado com o id %d", id)));

		return estado;
	}

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public Estado alterar(Estado estado) {
		Estado estadoEncontrado = estadoRepository.findById(estado.getId())
				.orElseThrow(() -> new EntidadeNaoEcontrataException(
						String.format("Estado não encontrado com o id %d", estado.getId())));
		BeanUtils.copyProperties(estado, estadoEncontrado, "id");

		return estadoRepository.save(estadoEncontrado);

	}

	public void excluir(Long id) {
		try {
			estadoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEcontrataException(format("Estado não existe com id %d", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(format("Estado está em uso %d", id));
		}
	}

}
