package rh.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Theme implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String valeur;
	

	private String remarque;
	
	/*@ManyToOne
	@JoinColumn(name="ID_FEEDBACK")
	private Feedback feedback;
	*/
	@ManyToOne
	@JoinColumn(name="ID_QUALIFICATION")
	private Qualification qualification;

	public Theme() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Theme(Long id, String valeur, String remarque) {
		super();
		this.id = id;
		this.valeur = valeur;
		this.remarque = remarque;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}
	
	

}
