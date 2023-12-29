package com.esr.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
