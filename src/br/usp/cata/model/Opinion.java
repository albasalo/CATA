package br.usp.cata.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Opinion {
	
	@Id
	@GeneratedValue
	private Long opinionID;
	
	@OneToOne
    @Cascade({CascadeType.ALL})
	private KeywordSet keywordSet;
	
	@OneToOne
	private PatternSuggestionPair patternSuggestionPair;
	
	public Opinion() {
	}

	public Long getOpinionID() {
		return opinionID;
	}

	public void setOpinionID(Long opinionID) {
		this.opinionID = opinionID;
	}

	public KeywordSet getKeywordSet() {
		return keywordSet;
	}

	public void setKeywordSet(KeywordSet keywordSet) {
		this.keywordSet = keywordSet;
	}

	public PatternSuggestionPair getPatternSuggestionPair() {
		return patternSuggestionPair;
	}

	public void setPatternSuggestionPair(PatternSuggestionPair patternSuggestionPair) {
		this.patternSuggestionPair = patternSuggestionPair;
	}
	
}
