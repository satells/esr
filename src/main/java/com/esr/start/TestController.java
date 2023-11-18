package com.esr.start;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@GetMapping
	public String message() {
		System.out.println("iniciado o sistema");
		return "iniciado o sistema";
	}
}
