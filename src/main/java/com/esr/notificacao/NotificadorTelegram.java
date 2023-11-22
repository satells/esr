package com.esr.notificacao;

import org.springframework.stereotype.Component;

import com.esr.domain.model.Cliente;
import com.esr.tipos.tiponotificador.NivelUrgencia;
import com.esr.tipos.tiponotificador.TipoNotificador;

@TipoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorTelegram implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s pelo Telegram através do telefone %s: %s\n", cliente.getNome(),
				cliente.getTelefone(), mensagem);
	}

}
