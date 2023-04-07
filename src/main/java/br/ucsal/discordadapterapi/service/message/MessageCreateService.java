package br.ucsal.discordadapterapi.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateService implements EventListener<MessageCreateEvent> {
	
	@Autowired
	private MessageProcessor messageProcessor;

	@Override
	public Class<MessageCreateEvent> getEventType() {
		return MessageCreateEvent.class;
	}

	@Override
	public Mono<Void> execute(final MessageCreateEvent event) {
		Message msg = event.getMessage();
		String resposta = messageProcessor.obterResposta(msg);
		return Mono.just(msg).flatMap(Message::getChannel)
							 .flatMap(channel -> resposta.isEmpty() ? Mono.empty() : channel.createMessage(resposta))
							 .then();
	}
	
}
