package br.usp.cata.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
public class Opinion {
	
	@Id
	@GeneratedValue
	private Long opinionID;
	
	@OneToMany(mappedBy="opinion", fetch=FetchType.EAGER)
    @Cascade({CascadeType.ALL})
	private Set<Agree> agree;
	
	@OneToMany(mappedBy="opinion", fetch=FetchType.EAGER)
    @Cascade({CascadeType.ALL})
	private Set<Disagree> disagree;
	
	public Opinion() {
	}

	public Long getOpinionID() {
		return opinionID;
	}

	public void setOpinionID(Long opinionID) {
		this.opinionID = opinionID;
	}

	public Set<Agree> getAgree() {
		return agree;
	}

	public void setAgree(Set<Agree> agree) {
		this.agree = agree;
	}

	public Set<Disagree> getDisagree() {
		return disagree;
	}

	public void setDisagree(Set<Disagree> disagree) {
		this.disagree = disagree;
	}
	
	public int getAgreeSize() {
		if(agree != null)
			return agree.size();
		return 0;
	}

	public int getDisagreeSize() {
		if(disagree != null)
			return disagree.size();
		return 0;
	}
	
}
