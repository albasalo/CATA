package br.usp.cata.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class PatternSuggestionPair implements Serializable {
	
	private static final long serialVersionUID = 943210483745024L;

	@Id
	@GeneratedValue
	private Long patternSuggestionPairID;
	
	@Column(length=1000)
	private byte[] tokenizedPatternBytes;
	
	@Column(length=200)
	private String pattern;
	
	@Column(length=200)
	private String suggestions;
	
	@Column
	private boolean defaultPair;
	
	@OneToOne
	@Cascade({ CascadeType.ALL })
	private Opinion opinion;
	
	public PatternSuggestionPair() {
	}

	public Long getPatternSuggestionPairID() {
		return patternSuggestionPairID;
	}

	public void setPatternSuggestionPairID(Long patternSuggestionPairID) {
		this.patternSuggestionPairID = patternSuggestionPairID;
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

	public String getSuggestions() {
		return suggestions;
	}

	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}

	public boolean isDefaultPair() {
		return defaultPair;
	}

	public void setDefaultPair(boolean defaultPair) {
		this.defaultPair = defaultPair;
	}
	
	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
	
	public Opinion getOpinion() {
		return opinion;
	}
	
}
