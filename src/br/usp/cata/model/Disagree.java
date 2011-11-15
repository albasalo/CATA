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
public class Disagree {

	@Id
	@GeneratedValue
	private Long disagreeID;
	
	@OneToOne
    @Cascade({CascadeType.ALL})
	private KeywordSet keywordSet;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Opinion opinion;
	
	public Disagree() {
	}

	public Long getDisagreeID() {
		return disagreeID;
	}

	public void setDisagreeID(Long disagreeID) {
		this.disagreeID = disagreeID;
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
