package br.ucsal.discordadapterapi.service.filter;

public interface Filter<T> {
	
	T process(T input);

}
