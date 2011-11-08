package br.usp.cata.model;

import java.util.Arrays;
import java.util.List;


public enum Languages {
	PORTUGUESE,
	ENGLISH;
	
	private final static List<String> languages = 
		Arrays.asList("Português", "Inglês");
	
	public String getLanguageDescription() {
		return languages.get(this.ordinal());
	}

}
