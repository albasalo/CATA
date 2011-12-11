package br.usp.cata.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Opinion {
	
	@Id
	@GeneratedValue
	private Long opinionID;
	
	@OneToMany(mappedBy="opinion", fetch=FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    private Set<Keyword> keywords;
	
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

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}

	public PatternSuggestionPair getPatternSuggestionPair() {
		return patternSuggestionPair;
	}

	public void setPatternSuggestionPair(PatternSuggestionPair patternSuggestionPair) {
		this.patternSuggestionPair = patternSuggestionPair;
	}
	
}
