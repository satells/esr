package com.esr.api.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esr.domain.exception.EntidadeNaoEcontrataException;
import com.esr.domain.model.Restaurante;
import com.esr.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	CadastroRestauranteService restauranteService;

	@GetMapping
	public ResponseEntity<List<Restaurante>> listar() {
		List<Restaurante> restaurantes = restauranteService.listar();

		return ResponseEntity.ok(restaurantes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id) {
		Restaurante restaurante = restauranteService.buscar(id);
		if (restaurante == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(restaurante);
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteSalvo = restauranteService.salvar(restaurante);
			return ResponseEntity.status(CREATED).body(restauranteSalvo);
		} catch (EntidadeNaoEcontrataException e) {
			return ResponseEntity.badRequest().header("Content-Type", "text/plain;charset=UTF-8").body(e.getMessage());
		}
	}
}
