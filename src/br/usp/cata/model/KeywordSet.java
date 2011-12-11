package br.usp.cata.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class KeywordSet {
	
	@Id
	@GeneratedValue
	private Long keywordSetID;
	
	@Column
	@OneToMany(mappedBy="keywordSetID", fetch=FetchType.EAGER)
    @Cascade({CascadeType.ALL})
	private Set<Keyword> keywords;

	public Long getKeywordSetID() {
		return keywordSetID;
	}

	public void setKeywordSetID(Long keywordsID) {
		this.keywordSetID = keywordsID;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}
	
}
