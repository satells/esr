package com.esr.domain.service;

import java.util.List;

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
		return restauranteRepository.listar();
	}

	public Restaurante buscar(Long id) {
		return restauranteRepository.buscar(id);
	}

	public Restaurante salvar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();

		Cozinha cozinha = cozinhaRepository.buscar(idCozinha);

		if (cozinha == null) {
			throw new EntidadeNaoEcontrataException(String.format("Não existe cozinha com o id %d", idCozinha));
		}
		restaurante.setCozinha(cozinha);

		return restauranteRepository.salvar(restaurante);

	}

	public Restaurante alterar(Restaurante restaurante) {
		Restaurante restauranteBusca = restauranteRepository.buscar(restaurante.getId());
		if (restauranteBusca == null) {
			throw new EntidadeNaoEcontrataException(
					String.format("Não existe restaurante com o id %d", restaurante.getId()));
		}

		Cozinha cozinhaBusca = cozinhaRepository.buscar(restaurante.getCozinha().getId());
		if (cozinhaBusca == null) {
			throw new EntidadeNaoEcontrataException(
					String.format("Não existe cozinha com o id %d", restaurante.getCozinha().getId()));
		}

		BeanUtils.copyProperties(restaurante, restauranteBusca, "id");

		return restauranteRepository.salvar(restaurante);

	}

}