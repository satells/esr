package com.esr.notificacao.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {
	/**
	 * Host do Servidor de e-mail
	 */
	private String hostServidor;
	/**
	 * Porta do servidor de e-mail
	 */
	private String portServidor;

}
