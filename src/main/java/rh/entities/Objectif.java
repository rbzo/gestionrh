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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Objectif implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String intitule;
	private String annee;
	private int pourcentageAvancement;
	private int poids;
	
	@ManyToOne
	@JoinColumn(name="ID_COLLABORATEUR")
	private Collaborateur collaborateur;
	
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "RESULTATS_OBJECTIFS",
            joinColumns = @JoinColumn(name = "ID_OBJ"),
            inverseJoinColumns = @JoinColumn(name = "ID_RESULTAT")
    )
	
	//@JsonManagedReference
	private Collection<Resultat> resultats;
	@ManyToOne
	@JoinColumn(name="ID_BILAN")
	private BilanPerformance bilanPerformance;
	
	public Objectif() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Objectif(Long id, String intitule, String annee,
			int pourcentageAvancement, int poids) {
		super();
		this.id = id;
		this.intitule = intitule;
		this.annee = annee;
		this.pourcentageAvancement = pourcentageAvancement;
		this.poids = poids;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public int getPoids() {
		return poids;
	}

	public void setPoids(int poids) {
		this.poids = poids;
	}

	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	public Collection<Resultat> getResultats() {
		return resultats;
	}

	public void setResultats(Collection<Resultat> resultats) {
		this.resultats = resultats;
	}

	public BilanPerformance getBilanPerformance() {
		return bilanPerformance;
	}

	public void setBilanPerformance(BilanPerformance bilanPerformance) {
		this.bilanPerformance = bilanPerformance;
	}
	
	
	
	

}
