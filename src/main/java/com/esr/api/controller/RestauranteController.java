package com.esr.api.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esr.domain.exception.EntidadeNaoEcontrataException;
import com.esr.domain.model.Restaurante;
import com.esr.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	ObjectMapper objectMapper;

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

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAlterado = restauranteService.alterar(restaurante);
			return ResponseEntity.ok(restauranteAlterado);
		} catch (EntidadeNaoEcontrataException e) {
			return ResponseEntity.status(NOT_FOUND).header("Content-Type", "text/plain;charset=UTF-8")
					.body(e.getMessage());
		}
	}

	@PatchMapping("/{id}")
	public ResponseEntity<?> alterarParcialmente(@PathVariable Long id, @RequestBody Map<String, Object> campos) {

		Restaurante restaurante = restauranteService.buscar(id);

		merge(campos, restaurante);

		return alterar(restaurante);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {

		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

		System.out.println(restauranteOrigem);

		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);

			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}

	@GetMapping("/por-taxa-entre")
	public ResponseEntity<List<Restaurante>> porTaxaEntre(@RequestParam BigDecimal taxaInicial,
			@RequestParam BigDecimal taxaFinal) {

		List<Restaurante> restaurantes = restauranteService.getPorTaxaEntre(taxaInicial, taxaFinal);

		return ResponseEntity.ok(restaurantes);
	}

	@GetMapping("/restaurantes-primeiros2")
	public ResponseEntity<List<Restaurante>> restaurantesprimeiros2(@RequestParam String nome) {
		List<Restaurante> restaurantes = restauranteService.findRestaurantesTop2ByNome(nome);

		return ResponseEntity.ok(restaurantes);

	}

	@GetMapping("/existe")
	public boolean existe(@RequestParam String nome) {
		return restauranteService.existe(nome);
	}

}
