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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Evaluateur implements Serializable{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;
   private String nom;
   private String prenom;
   private String profil;
   
   @ManyToMany(cascade=CascadeType.ALL)
   @JoinTable(
		   name="COLLABORATEURS_EVALUTEUR",
		   joinColumns=@JoinColumn(name="ID_EVALUATEUR"),
		   inverseJoinColumns=@JoinColumn(name="ID_COLLABORATEUR")
		   )
   //@JsonManagedReference
   private Collection<Collaborateur> collaborateurs;

public Evaluateur() {
	super();
	// TODO Auto-generated constructor stub
}

public Evaluateur(Long id, String nom, String prenom, String profil) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.profil = profil;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getProfil() {
	return profil;
}

public void setProfil(String profil) {
	this.profil = profil;
}

public Collection<Collaborateur> getCollaborateurs() {
	return collaborateurs;
}

public void setCollaborateurs(Collection<Collaborateur> collaborateurs) {
	this.collaborateurs = collaborateurs;
}
   
   
}
