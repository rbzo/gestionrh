package rh.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
/**
 * 
 * @author rabiou-rb
 *
 */
@Entity
@Table(name="OBJECTIFS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Objectif implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String categorie;
	private String intitule;
	private String description;
	
	private Date DateAjout;

	
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

    

	public Objectif(String categorie, String intitule,String description, Date dateAjout) {
		super();
		this.categorie = categorie;
		this.intitule = intitule;
		this.description=description;
		this.DateAjout = dateAjout;
	}



	public Objectif(Long id, String categorie, String intitule,  Date dateAjout,
			int pourcentageAvancement) {
		super();
		this.id = id;
		this.categorie = categorie;
		this.intitule = intitule;
		this.DateAjout = dateAjout;
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

	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    public Date getDateAjout() {
		return DateAjout;
	}



	public void setDateAjout(Date dateAjout) {
		DateAjout = dateAjout;
	}


@JsonIgnore
	public BilanPerformance getBilanPerformance() {
		return bilanPerformance;
	}



	public void setBilanPerformance(BilanPerformance bilanPerformance) {
		this.bilanPerformance = bilanPerformance;
	}



	@JsonIgnore
	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}


	


	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	

}
