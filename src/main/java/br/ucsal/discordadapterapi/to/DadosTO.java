package br.ucsal.discordadapterapi.to;

import discord4j.core.object.entity.Message;
import lombok.Data;

@Data
public class DadosTO {
	
	private Message message;
	private String retorno = "";
	
	public DadosTO(Message msg) {
		this.message = msg;
	}

}
