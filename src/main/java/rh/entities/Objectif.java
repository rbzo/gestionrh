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
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="OBJECTIFS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Objectif implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String categorie;
	private String intitule;
	
	private String annee;
	private int pourcentageAvancement;
	
	@ManyToOne
	@JoinColumn(name="ID_COLLABORATEUR")
	private Collaborateur collaborateur;
	
	@ManyToOne
	@JoinColumn(name="ID_BILAN")
	private BilanPerformance bilanPerformance;
	
	public Objectif() {
		super();
		// TODO Auto-generated constructor stub
	}

    

	public Objectif(String categorie, String intitule, String annee) {
		super();
		this.categorie = categorie;
		this.intitule = intitule;
		this.annee = annee;
	}



	public Objectif(Long id, String categorie, String intitule, String annee,
			int pourcentageAvancement) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.intitule = intitule;
		this.annee = annee;
		this.pourcentageAvancement = pourcentageAvancement;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}


	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public int getPourcentageAvancement() {
		return pourcentageAvancement;
	}

	public void setPourcentageAvancement(int pourcentageAvancement) {
		this.pourcentageAvancement = pourcentageAvancement;
	}

    @JsonIgnore
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
	
	
	
	

}
