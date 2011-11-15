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
	private KeywordSet keywordSet;
	
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

	public KeywordSet getKeywordSet() {
		return keywordSet;
	}

	public void setKeywordSet(KeywordSet keywordSet) {
		this.keywordSet = keywordSet;
	}

}
