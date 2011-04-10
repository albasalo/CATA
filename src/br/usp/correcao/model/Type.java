package br.usp.correcao.model;

public enum Type {
	ERROR("Erro"), WARNING("Ponto de atenção");
	
	private final String errorType;
	
	private Type(String errorType){
		this.errorType = errorType;
	}
	
	public String getErrorType() {
		return errorType;
	}
}
