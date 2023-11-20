package com.esr.notificacao;

import org.springframework.stereotype.Component;

import com.esr.model.Cliente;
import com.esr.tipos.tiponotificador.NivelUrgencia;
import com.esr.tipos.tiponotificador.TipoNotificador;

@TipoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorWhatsApp implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s por Whats App através do telefone %s: %s\n", cliente.getNome(),
				cliente.getTelefone(), mensagem);
	}

}
