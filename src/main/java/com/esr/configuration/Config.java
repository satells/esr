package com.esr.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.esr.notificacao.Notificador;
import com.esr.notificacao.NotificadorEmail;
import com.esr.notificacao.NotificadorSms;

@Configuration
public class Config {

	@Bean("notificadoresEmailSms")
	List<Notificador> getNotificadoresEmailSms(NotificadorEmail notificadorEmail, NotificadorSms notificadorSms) {
		return List.of(notificadorEmail, notificadorSms);
	}

	@Bean("notificadoresSms")
	List<Notificador> getNotificadoresSms(NotificadorSms notificadorSms) {
		return List.of(notificadorSms);

	}

}
