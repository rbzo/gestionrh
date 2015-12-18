package rh.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class Collaborateur implements Serializable{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;
   private String nom;
   private String prenom;
   private Date dateNaissance;
   private Date dateEmbauche;
   private String poste;
   private String email;
   
   
   @ManyToOne
   @JoinColumn(name="ID_MANAGER")
   private ManagerRh managerRh;
   
   @OneToMany(mappedBy="collaborateur", cascade=CascadeType.ALL)
   private Set<Feedback> feedbacks;
   
   @OneToMany(mappedBy="collaborateur", cascade=CascadeType.ALL)
   private Set<Objectif> objectifs;

   
   @OneToMany(mappedBy="collaborateur", cascade=CascadeType.ALL)
   private Set<BilanPerformance> bilanPerformances;
   
   
 /*  
   @ManyToMany(cascade=CascadeType.ALL)
   @JoinTable(
		   name="COLLABORATEUR_PROJECTS",
		   joinColumns=@JoinColumn(name="ID_COLLABORATEUR"),
		   inverseJoinColumns=@JoinColumn(name="ID_PROJET")
		   )
  // @JsonManagedReference
   private Set<Projet> projets=new HashSet<Projet>();*/
   
  /* @JsonBackReference
   @ManyToMany(mappedBy="collaborateurs")
   private Set<Evaluateur> evaluateurs;*/
   
   
   
   
public Collaborateur() {
	super();
	// TODO Auto-generated constructor stub
}
public Collaborateur(String nom, String prenom,Date dateNaissance, Date dateEmbauche,String poste, String email) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.dateNaissance=dateNaissance;
	this.dateEmbauche = dateEmbauche;
	this.poste = poste;
	this.email = email;
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
public Date getDateNaissance() {
	return dateNaissance;
}
public void setDateNaissance(Date dateNaissance) {
	this.dateNaissance = dateNaissance;
}
public Date getDateEmbauche() {
	return dateEmbauche;
}
public void setDateEmbauche(Date dateEmbauche) {
	this.dateEmbauche = dateEmbauche;
}


public String getPoste() {
	return poste;
}
public void setPoste(String poste) {
	this.poste = poste;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public ManagerRh getManagerRh() {
	return managerRh;
}
public void setManagerRh(ManagerRh managerRh) {
	this.managerRh = managerRh;
}
public Set<Feedback> getFeedbacks() {
	return feedbacks;
}
public void setFeedbacks(Set<Feedback> feedbacks) {
	this.feedbacks = feedbacks;
}
//@JsonIgnore
public Set<Objectif> getObjectifs() {
	return objectifs;
}
public void setObjectifs(Set<Objectif> objectifs) {
	this.objectifs = objectifs;
}
public Set<BilanPerformance> getBilanPerformances() {
	return bilanPerformances;
}
public void setBilanPerformances(Set<BilanPerformance> bilanPerformances) {
	this.bilanPerformances = bilanPerformances;
}

/*public Set<Evaluateur> getEvaluateurs() {
	return evaluateurs;
}
public void setEvaluateurs(Set<Evaluateur> evaluateurs) {
	this.evaluateurs = evaluateurs;
}
*/


   
}
