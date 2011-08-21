package br.usp.cata.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


// TODO: Melhorar a classe - tipo da fonte, autor, etc.
@Entity
public class Source implements Serializable {

	private static final long serialVersionUID = 748392748201L;

    @Id
    @GeneratedValue
	private Long sourceID;
    
    private String description;
    
    @ManyToOne
    @JoinColumn(name="userID", referencedColumnName="userID")
    @Cascade({CascadeType.ALL})
    private User user;
	
	public Source() {
	}

	public Long getSourceID() {
		return sourceID;
	}

	public void setSourceID(Long sourceID) {
		this.sourceID = sourceID;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}

	