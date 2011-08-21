package br.usp.cata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class PatternSuggestionElement implements Serializable {
	
	private static final long serialVersionUID = 1937378649265L;

	@Id
	@GeneratedValue
	private Long patternSuggestionElementID;
	
	@Column(length=1000)
	private byte[] tokenizedPatternBytes;
	
	@Column(length=200)
	private String pattern;
	
	@Column(length=200)
	private String suggestion;
	
	public PatternSuggestionElement() {
	}

	public Long getPatternSuggestionElementID() {
		return patternSuggestionElementID;
	}

	public void setPatternSuggestionElementID(Long patternSuggestionElementID) {
		this.patternSuggestionElementID = patternSuggestionElementID;
	}

	public byte[] getTokenizedPatternBytes() {
		return tokenizedPatternBytes;
	}

	public void setTokenizedPatternBytes(byte[] tokenizedPatternBytes) {
		this.tokenizedPatternBytes = tokenizedPatternBytes;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	
}
