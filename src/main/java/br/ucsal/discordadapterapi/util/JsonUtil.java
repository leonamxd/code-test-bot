package br.ucsal.discordadapterapi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	
	public JsonUtil() {
		throw new UnsupportedOperationException("Classe utilitaria!");
	}
	
	public static String toJson(Object o) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(o);
	}
	
	public static <T> T fromJson(String json, Class<T> valueType) throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, valueType);
	}

}
