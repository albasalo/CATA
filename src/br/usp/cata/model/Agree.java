package br.usp.cata.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Agree {
	
	@Id
	@GeneratedValue
	private Long agreeID;
	
	@OneToOne
    @Cascade({CascadeType.ALL})
	private KeywordSet keywordSet;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Opinion opinion;
	
	public Agree() {
	}

	public Long getAgreeID() {
		return agreeID;
	}

	public void setAgreeID(Long agreeID) {
		this.agreeID = agreeID;
	}

	public KeywordSet getKeywordSet() {
		return keywordSet;
	}

	public void setKeywordSet(KeywordSet keywordSet) {
		this.keywordSet = keywordSet;
	}

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}
	
}
