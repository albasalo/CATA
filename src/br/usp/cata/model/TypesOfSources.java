package br.usp.cata.model;

import java.util.Arrays;
import java.util.List;


public enum TypesOfSources {
	ACADEMIC_PUBLISHING,
	BOOK,
	HANDBOOK,
	INTERNET,
	OTHER;
	
	private final static List<String> typesOfSources = 
		Arrays.asList("Artigo acadêmico", "Livro", "Manual", "URL - Internet", "Outro");
	
	public String getTypeDescription() {
		return (typesOfSources.get(this.ordinal()));
	}
	
}
