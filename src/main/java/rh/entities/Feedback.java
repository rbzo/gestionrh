package rh.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Feedback implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private int nbreJoursValorises;
	private String commentaire;
	@ManyToOne
	@JoinColumn(name="ID_COLLABORATEUR")
	private Collaborateur collaborateur;
	
	@ManyToOne
	@JoinColumn(name="ID_BILAN")
	private BilanPerformance bilanPerformance;
	
	@OneToMany(mappedBy="feedback", cascade=CascadeType.ALL)
	private Collection<Theme> themes;

	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(Long id, int nbreJoursValorises, String commentaire) {
		super();
		this.id = id;
		this.nbreJoursValorises = nbreJoursValorises;
		this.commentaire = commentaire;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNbreJoursValorises() {
		return nbreJoursValorises;
	}

	public void setNbreJoursValorises(int nbreJoursValorises) {
		this.nbreJoursValorises = nbreJoursValorises;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	public BilanPerformance getBilanPerformance() {
		return bilanPerformance;
	}

	public void setBilanPerformance(BilanPerformance bilanPerformance) {
		this.bilanPerformance = bilanPerformance;
	}

	public Collection<Theme> getThemes() {
		return themes;
	}

	public void setThemes(Collection<Theme> themes) {
		this.themes = themes;
	}
	
	

}
