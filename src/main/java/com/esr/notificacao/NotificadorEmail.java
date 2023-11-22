package com.esr.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esr.domain.model.Cliente;
import com.esr.notificacao.properties.NotificadorProperties;
import com.esr.tipos.tiponotificador.NivelUrgencia;
import com.esr.tipos.tiponotificador.TipoNotificador;

@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

	@Autowired
	private NotificadorProperties properties;

	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do e-mail %s porta: %s - host:%s -  %s\n", cliente.getNome(),
				cliente.getEmail(), properties.getPortServidor(), properties.getHostServidor(), mensagem);
	}
}
