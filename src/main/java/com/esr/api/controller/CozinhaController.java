package com.esr.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esr.api.model.CozinhasXmlRepresentationModel;
import com.esr.domain.exception.EntidadeEmUsoException;
import com.esr.domain.exception.EntidadeNaoEcontrataException;
import com.esr.domain.model.Cozinha;
import com.esr.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(value = "/cozinhas") // , produces = {
										// MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE })
public class CozinhaController {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@PostMapping
	public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha) {
		Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinha);

		return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaSalva);
	}

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Cozinha>> listar() {
		List<Cozinha> cozinhas = cadastroCozinhaService.listar();
		return ResponseEntity.ok(cozinhas);
	}

	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<CozinhasXmlRepresentationModel> listarxml() {
		CozinhasXmlRepresentationModel cozinhas = new CozinhasXmlRepresentationModel(cadastroCozinhaService.listar());

		return ResponseEntity.ok(cozinhas);
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cozinha> getCozinha(@PathVariable Long id) {
		Cozinha cozinha = cadastroCozinhaService.getCozinha(id);

		if (cozinha == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(cozinha);
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://api.esr.local/cozinhas");
//		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@RequestBody Cozinha cozinha, @PathVariable Long id) {
		Cozinha cozinhaAtual = cadastroCozinhaService.getCozinha(id);

		if (cozinhaAtual == null) {
			return ResponseEntity.notFound().build();
		}

		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

		cozinhaAtual = cadastroCozinhaService.salvar(cozinhaAtual);

		return ResponseEntity.ok(cozinhaAtual);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cozinha> remover(@PathVariable Long id) {
		try {
			cadastroCozinhaService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEcontrataException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@GetMapping("/por-nome")
	public ResponseEntity<List<Cozinha>> consultaPorNome(@RequestParam String nome) {
		List<Cozinha> cozinhas = cadastroCozinhaService.consultaPorNome(nome);
		return ResponseEntity.ok(cozinhas);
	}
}
