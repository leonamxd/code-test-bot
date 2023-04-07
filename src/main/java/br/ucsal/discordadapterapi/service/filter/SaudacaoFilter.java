package br.ucsal.discordadapterapi.service.filter;

import org.springframework.stereotype.Component;

import br.ucsal.discordadapterapi.to.DadosTO;
import br.ucsal.discordadapterapi.util.Constantes;
import discord4j.core.object.entity.Message;

@Component
public class SaudacaoFilter implements Filter<DadosTO> {
	
	private String autor = "Desconhecido";

	@Override
	public DadosTO process(DadosTO to) {
		Message msg = to.getMessage();
		String conteudo = msg.getData().content();
		final Boolean isNotBot = msg.getAuthor().map(user -> !user.isBot()).orElse(false);
		if (isNotBot && conteudo.startsWith(Constantes.COMANDO_INICIAL_BOT.concat(" oi"))) {
			msg.getAuthor().ifPresent(user -> autor = user.getUsername());
			to.setRetorno(String.format("Olá, %s!", autor));
		}

		return to;
	}

}
