package com.esr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class Config {

	@Bean
	ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}
}
