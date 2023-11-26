package com.esr.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esr.api.model.CozinhasXmlRepresentationModel;
import com.esr.domain.model.Cozinha;
import com.esr.domain.repository.CozinhaRepository;

@RestController
@RequestMapping(value = "/cozinhas") // , produces = { MediaType.APPLICATION_JSON_VALUE,
										// MediaType.APPLICATION_XML_VALUE })
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Cozinha>> listar() {
		List<Cozinha> cozinhas = cozinhaRepository.listar();

		return ResponseEntity.ok(cozinhas);
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CozinhasXmlRepresentationModel> listarxml() {
		CozinhasXmlRepresentationModel cozinhas = new CozinhasXmlRepresentationModel(cozinhaRepository.listar());

		return ResponseEntity.ok(cozinhas);
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cozinha> getCozinha(@PathVariable Long id) {
		Cozinha cozinha = cozinhaRepository.buscar(id);

		if (cozinha == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cozinha);
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://api.esr.local/cozinhas");
//		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
	}

	@PostMapping
	public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {

		System.out.println(cozinha);
		Cozinha cozinhaSalva = cozinhaRepository.salvar(cozinha);

		HttpHeaders headers = new HttpHeaders();

		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long id) {
		Cozinha cozinhaAtual = cozinhaRepository.buscar(id);

		if (cozinhaAtual == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

		cozinhaAtual = cozinhaRepository.salvar(cozinhaAtual);

		return ResponseEntity.ok(cozinhaAtual);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		try {
			Cozinha cozinha = cozinhaRepository.buscar(id);

			if (cozinha == null) {
				return ResponseEntity.notFound().build();
			}

			cozinhaRepository.remover(cozinha);

			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (DataIntegrityViolationException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
