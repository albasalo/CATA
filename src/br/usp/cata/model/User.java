package br.usp.cata.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class User implements Serializable
{
	private static final long serialVersionUID = 2412356395065413898L;

    @Id
    @GeneratedValue
    private Long userID;
    
    @Column(unique = true, length = 100)
    private String email;
    
    @Column(length = 100)
    private String name;
    
    @Column(length = 32)
    private String password;

    @Column
    private String activationKey;
    
    @Column
    private boolean active;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;
    
    public User() {
    }
    
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	} 
}
