package br.ucsal.discordadapterapi.to.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
	
	private Long id;
	private String token;
	private String tipo;

}
