package br.usp.cata.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Rule implements Serializable {

	private static final long serialVersionUID = 8581290083742L;
	
    @Id
    @GeneratedValue
    private Long ruleID;
	
    @Column
	private boolean defaultRule;
	
    @Column
	private TypesOfRules type;
    
    @Column
	private RuleCategories category;
    
    @OneToMany(mappedBy="rule", fetch=FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    private Set<Lemma> lemmas;

    @OneToMany(mappedBy="rule", fetch=FetchType.EAGER)
    @Cascade({CascadeType.ALL})
	private Set<ExactMatching> exactMatchings;
    
    @Column(length=400)
	private String explanation;
    
    @ManyToOne
    @JoinColumn(name="sourceID", referencedColumnName="sourceID")
	private Source source;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
	private Date date;
    
    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName="userID")
	private User user;
    
    public Rule() {
    }

	public Long getRuleID() {
		return ruleID;
	}

	public void setRuleID(Long ruleID) {
		this.ruleID = ruleID;
	}

	public boolean isDefaultRule() {
		return defaultRule;
	}

	public void setDefaultRule(boolean defaultRule) {
		this.defaultRule = defaultRule;
	}

	public TypesOfRules getType() {
		return type;
	}

	public void setType(TypesOfRules type) {
		this.type = type;
	}

	public RuleCategories getCategory() {
		return category;
	}

	public void setCategory(RuleCategories category) {
		this.category = category;
	}
	
	public Set<Lemma> getLemmas() {
		return lemmas;
	}

	public void setLemmas(Set<Lemma> lemmas) {
		this.lemmas = lemmas;
	}

	public Set<ExactMatching> getExactMatchings() {
		return exactMatchings;
	}

	public void setExactMatchings(Set<ExactMatching> exactMatchings) {
		this.exactMatchings = exactMatchings;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
