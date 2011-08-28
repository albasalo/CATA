package br.usp.cata.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
    
    @ManyToOne
    @JoinColumn(name="lemmaElementID", referencedColumnName="patternSuggestionElementID")
    @Cascade({CascadeType.ALL})
	private PatternSuggestionElement lemmaElement;
    
    @ManyToMany
    @JoinColumn(name="lemmaElementsID", referencedColumnName="patternSuggestionElementID")
    @Cascade({CascadeType.ALL})
    private Set<PatternSuggestionElement> lemmaElements;
    
    @ManyToOne
    @JoinColumn(name="exactMacthingElementID", referencedColumnName="patternSuggestionElementID")
    @Cascade({CascadeType.ALL})
	private PatternSuggestionElement exactMatchingElement;

    @ManyToMany
    @JoinColumn(name="exactMatchingElementsID", referencedColumnName="patternSuggestionElementID")
    @Cascade({CascadeType.ALL})
	private Set<PatternSuggestionElement> exactMatchingElements;
    
    @Column(length=400)
	private String explanation;
    
    @ManyToOne
    @JoinColumn(name="sourceID", referencedColumnName="sourceID")
    @Cascade({CascadeType.ALL})
	private Source source;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
	private Date date;
    
    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName="userID")
    @Cascade({CascadeType.ALL})
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

	public PatternSuggestionElement getLemmaElement() {
		return lemmaElement;
	}

	public void setLemmaElement(PatternSuggestionElement lemmaElement) {
		this.lemmaElement = lemmaElement;
	}
	
	public Set<PatternSuggestionElement> getLemmaElements() {
		return lemmaElements;
	}
	
	public void setLemmaElements(Set<PatternSuggestionElement> lemmaElements) {
		this.lemmaElements = lemmaElements;
	}
	
	public PatternSuggestionElement getExactMatchingElement() {
		return exactMatchingElement;
	}
	
	public void setExactMatchingElement(PatternSuggestionElement exactMatchingElement) {
		this.exactMatchingElement = exactMatchingElement;
	}

	public Set<PatternSuggestionElement> getExactMatchingElements() {
		return exactMatchingElements;
	}

	public void setExactMatchingElements(
			Set<PatternSuggestionElement> exactMatchingElements) {
		this.exactMatchingElements = exactMatchingElements;
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
