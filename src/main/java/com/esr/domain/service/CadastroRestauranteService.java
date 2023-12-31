package com.esr.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esr.domain.exception.EntidadeNaoEcontrataException;
import com.esr.domain.model.Cozinha;
import com.esr.domain.model.Restaurante;
import com.esr.domain.repository.CozinhaRepository;
import com.esr.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	RestauranteRepository restauranteRepository;
	@Autowired
	CozinhaRepository cozinhaRepository;

	public List<Restaurante> listar() {
		return restauranteRepository.findAll();
	}

	public Restaurante buscar(Long id) {
		Optional<Restaurante> optRestaurante = restauranteRepository.findById(id);

		if (optRestaurante.isPresent()) {
			return optRestaurante.get();
		}

		return null;
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();

		Optional<Cozinha> optCozinha = cozinhaRepository.findById(idCozinha);

		if (optCozinha.isPresent()) {
			restaurante.setCozinha(optCozinha.get());
			return restauranteRepository.save(restaurante);
		}

		throw new EntidadeNaoEcontrataException(String.format("Não existe cozinha com o id %d", idCozinha));

	}

	public Restaurante alterar(Restaurante restaurante) {
		Restaurante restauranteBusca = restauranteRepository.findById(restaurante.getId())
				.orElseThrow(() -> new EntidadeNaoEcontrataException(
						String.format("Não existe restaurante com o id %d", restaurante.getId())));

		cozinhaRepository.findById(restaurante.getCozinha().getId())
				.orElseThrow(() -> new EntidadeNaoEcontrataException(
						String.format("Não existe cozinha com o id %d", restaurante.getCozinha().getId())));

		BeanUtils.copyProperties(restaurante, restauranteBusca, "id");

		return restauranteRepository.save(restaurante);

	}

}