package com.esr.domain.service;

import static com.esr.util.Util.COZINHA_NAO_PODE_SER_REMOVIDA;
import static com.esr.util.Util.NAO_EXISTE_COZINHA;
import static java.lang.String.format;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.esr.domain.exception.EntidadeEmUsoException;
import com.esr.domain.exception.EntidadeNaoEcontrataException;
import com.esr.domain.model.Cozinha;
import com.esr.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	/**
	 * Método usado tanto para atualizar quanto para inserir um novo
	 */
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEcontrataException(format(NAO_EXISTE_COZINHA, cozinhaId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(format(COZINHA_NAO_PODE_SER_REMOVIDA, cozinhaId));
		}
	}

	public Cozinha getCozinha(Long id) {
		Optional<Cozinha> optCozinha = cozinhaRepository.findById(id);

		if (optCozinha.isPresent()) {
			return optCozinha.get();
		}
		System.out.println("cozinha nula");
		return null;
	}

	public List<Cozinha> consultaPorNome(String nome) {
		return cozinhaRepository.consultaPorNome(nome);
	}
}
