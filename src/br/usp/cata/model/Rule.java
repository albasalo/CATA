package br.usp.cata.model;

public class Rule {

	private String category;
	private String type;
	private String pattern, suggestion;
	private int length;
	private String reference;
	
	/* O XML segue esse esquema. */
	public static final String rulesTag = "Rule";
	public static final String[] ruleProperties = 
		{ "Category", "Type", "Pattern", "Suggestion", "Reference" };
	
	//FIXME verificar o array - tamanho etc.
	public Rule(String[] properties) {
		category = properties[0];
		type = properties[1];
		pattern = properties[2];
		suggestion = properties[3];
		reference = properties[4];
		setLength();
	}
	
	/*
	 * Importante: sempre chamar essa funcao ao setar 'pattern'.
	 */
	private void setLength() {
		length = (" " + pattern + " ").getBytes().length;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
		setLength();
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public int getLength() {
		return length;
	}
	
}
