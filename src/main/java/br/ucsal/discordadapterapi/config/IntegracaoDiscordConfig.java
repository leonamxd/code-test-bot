package br.ucsal.discordadapterapi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.ucsal.discordadapterapi.model.EventListener;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;

@Configuration
public class IntegracaoDiscordConfig {
	
	@Value("${token}")
	private String token;
	
	@Bean
	public <T extends Event> GatewayDiscordClient getGatewayDiscordClient(final List<EventListener<T>> eventListeners) {
		
		final GatewayDiscordClient client = DiscordClientBuilder.create(token)
																.build()
																.login()
																.block();
		
		for(final EventListener<T> listener : eventListeners) {
			client.on(listener.getEventType())
				  .flatMap(listener::execute)
				  .onErrorResume(listener::handleError)
				  .subscribe();
		}
		
		return client;
	}

}
