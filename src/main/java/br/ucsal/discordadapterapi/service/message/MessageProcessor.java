package br.ucsal.discordadapterapi.service.message;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ucsal.discordadapterapi.service.filter.Filter;
import br.ucsal.discordadapterapi.service.pipe.Pipeline;
import br.ucsal.discordadapterapi.to.DadosTO;
import br.ucsal.discordadapterapi.util.Constantes;
import discord4j.core.object.entity.Message;

@Service
public class MessageProcessor {

	private Pipeline<DadosTO> pipeline;

	@Autowired
	public MessageProcessor(List<Filter<DadosTO>> filters) {
		this.pipeline = new Pipeline<DadosTO>(filters);
	}

	public String obterResposta(Message msg) {
		if (Objects.isNull(msg) || Objects.isNull(msg.getData()) || 
			Objects.isNull(msg.getData().content()) || !msg.getData().content().startsWith(Constantes.COMANDO_INICIAL_BOT)) {
			return Constantes.EMPTY_STRING;
		}
		DadosTO to = new DadosTO(msg);
		return pipeline.process(to).getRetorno();
	}

}
