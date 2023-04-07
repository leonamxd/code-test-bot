package br.ucsal.discordadapterapi.model;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public abstract class MessageListener {

	private String autor = "Desconhecido";

	public Mono<Void> processMessage(final Message message) {
		return Mono.just(message)
				   .filter(msg -> {

			final Boolean isNotBot = msg.getAuthor()
										.map(user -> !user.isBot())
										.orElse(false);
			if (isNotBot) {
				msg.getAuthor().ifPresent(user -> autor = user.getUsername());
			}
			return isNotBot;
		})
		.flatMap(Message::getChannel)
		.flatMap(channel -> channel.createMessage(String.format("Olá, %s!", autor)))
		.then();
	}

}
