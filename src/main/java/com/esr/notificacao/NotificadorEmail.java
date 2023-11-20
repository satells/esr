package com.esr.notificacao;

import org.springframework.stereotype.Component;

import com.esr.model.Cliente;
import com.esr.tipos.tiponotificador.NivelUrgencia;
import com.esr.tipos.tiponotificador.TipoNotificador;

@TipoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {

	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
