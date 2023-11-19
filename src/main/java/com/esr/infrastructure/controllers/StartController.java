package com.esr.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartController {

	@GetMapping
	public String message() {
		System.out.println("iniciado");
		return "iniciado";
	}
}
