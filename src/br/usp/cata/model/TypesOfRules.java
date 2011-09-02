package br.usp.cata.model;

import java.util.Arrays;
import java.util.List;


public enum TypesOfRules {
	CLICHE,
	FOREIGN_TERM,
	POOR_TRANSLATION,
	OTHER;
	
	private final static List<String> typesOfRules = 
		Arrays.asList("Clichê", "Estrangeirismo", "Tradução incorreta", "Outro");
	
	public String getTypeDescription() {
		return typesOfRules.get(this.ordinal());
	}
	
}
