package com.esr.infrastructure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esr.model.Cliente;
import com.esr.service.AtivacaoClienteService;

@RestController
public class StartController {

	@Autowired
	AtivacaoClienteService service;

	@GetMapping
	public String message() {

		Cliente mauro = new Cliente("Mauro", "mauro@mail.com", "11 5878-9988");
		Cliente heitor = new Cliente("Heitor", "heitor@mail.com", "11 2254-9878");

		service.ativar(mauro);
		service.ativar(heitor);
		System.out.println("iniciado");
		return "iniciado";
	}
}
