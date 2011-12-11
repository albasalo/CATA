package br.usp.cata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Keyword {
	
	@Id
	@GeneratedValue
	private Long keywordID;
	
	@Column
	private String word;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Opinion opinion;
	
	public Keyword() {
	}

	public Long getKeywordID() {
		return keywordID;
	}

	public void setKeywordID(Long keywordID) {
		this.keywordID = keywordID;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}

}
