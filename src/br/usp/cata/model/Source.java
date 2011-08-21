package br.usp.cata.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Source implements Serializable {

	private static final long serialVersionUID = 748392748201L;

    @Id
    @GeneratedValue
	private Long sourceID;
    
    @Column
    private TypesOfSources type;
    
    @Column(length=200)
    private String title;
    
    @Column(length=300)
    private String url;
    
    @Column(length=300)
    private String authors;
    
    @Column(length=100)
    private String publisher;
    
    @Column(length=100)
    private String institution;
    
    @Column(length=20)
    private String date;
    
    @Column(length=400)
    private String moreInformation;
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    
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

	public TypesOfSources getType() {
		return type;
	}

	public void setType(TypesOfSources type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMoreInformation() {
		return moreInformation;
	}

	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	
}

	