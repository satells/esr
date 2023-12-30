package com.esr.domain.service;

import static java.lang.String.format;

import java.util.List;
import java.util.Optional;

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
		Optional<Estado> optEstado = estadoRepository.findById(id);

		if (optEstado.isPresent()) {
			return optEstado.get();
		}
		throw new EntidadeNaoEcontrataException(String.format("Estado não encontrado com o id %d", id));

	}

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public Estado alterar(Estado estado) {
		Optional<Estado> optEstado = estadoRepository.findById(estado.getId());

		if (!optEstado.isPresent()) {
			throw new EntidadeNaoEcontrataException(String.format("Estado não encontrado com o id %d", estado.getId()));
		}
		Estado estadoEncontrado = optEstado.get();

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
