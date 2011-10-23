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
public class Lemma implements Serializable {
	
	private static final long serialVersionUID = 943210483745023L;
	
	@Id
	@GeneratedValue
	private Long lemmaID;
	
	@OneToOne
    @Cascade({CascadeType.ALL})
	private PatternSuggestionPair pair;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Rule rule;

	public Lemma() {
	}

	public Long getLemmaID() {
		return lemmaID;
	}

	public void setLemmaID(Long lemmaID) {
		this.lemmaID = lemmaID;
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
