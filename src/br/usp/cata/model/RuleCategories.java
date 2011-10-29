package br.usp.cata.model;

import java.util.Arrays;
import java.util.List;


public enum RuleCategories {
	ERROR,
	WARNING;
	
	private final static List<String> ruleCategories =
		Arrays.asList("Problema", "Atenção");
	
	public String getCategoryDescription() {
		return ruleCategories.get(this.ordinal());
	}
	
}
