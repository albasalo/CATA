package br.usp.cata.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class ExactMatching implements Serializable {
	
	private static final long serialVersionUID = 943210483745022L;
	
	@Id
	@GeneratedValue
	private Long exactMatchingID;
	
	@OneToOne
    @Cascade({CascadeType.ALL})
	private PatternSuggestionPair pair;
	
    @ManyToOne(fetch=FetchType.EAGER)
	private Rule rule;

	public ExactMatching() {
	}
	
	public Long getExactMatchingID() {
		return exactMatchingID;
	}

	public void setExactMatchingID(Long exactMatchingID) {
		this.exactMatchingID = exactMatchingID;
	}

	public PatternSuggestionPair getPair() {
		return pair;
	}

	public void setPair(PatternSuggestionPair pair) {
		this.pair = pair;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
	}

}
