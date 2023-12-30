package com.esr.api.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esr.domain.exception.EntidadeEmUsoException;
import com.esr.domain.exception.EntidadeNaoEcontrataException;
import com.esr.domain.model.Estado;
import com.esr.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	CadastroEstadoService cadastroEstadoService;

	@GetMapping
	public List<Estado> listar() {
		return cadastroEstadoService.listar();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id) {
		try {
			Estado estado = cadastroEstadoService.buscar(id);
			return ResponseEntity.ok(estado);
		} catch (EntidadeNaoEcontrataException e) {
			return ResponseEntity.status(NOT_FOUND).header("Content-Type", "text/plain;charset=UTF-8")
					.body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
		Estado estadoSalvo = cadastroEstadoService.salvar(estado);

		return ResponseEntity.status(CREATED).body(estadoSalvo);

	}

	@PutMapping
	public ResponseEntity<?> alterar(@RequestBody Estado estado) {

		try {
			Estado estadoAlterado = cadastroEstadoService.alterar(estado);
			return ResponseEntity.ok(estadoAlterado);
		} catch (EntidadeNaoEcontrataException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "text/plain;charset=UTF-8")
					.body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Estado> excluir(@PathVariable Long id) {
		try {
			cadastroEstadoService.excluir(id);
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEcontrataException e) {
			return ResponseEntity.notFound().build();
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}

	}

}
