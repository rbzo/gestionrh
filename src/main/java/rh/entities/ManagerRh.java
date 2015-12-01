package rh.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class ManagerRh implements Serializable{
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Long id;
   private String nom;
   private String prenom;
   @OneToMany(mappedBy="managerRh",cascade=CascadeType.ALL)
   private Collection<Collaborateur> collaborateurs;
   
   @ManyToOne
   @JoinColumn(name="ID_ADMINISTRATEUR")
   private Administrateur administrateur;

public ManagerRh() {
	super();
	// TODO Auto-generated constructor stub
}

public ManagerRh(Long id, String nom, String prenom) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
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

public Collection<Collaborateur> getCollaborateurs() {
	return collaborateurs;
}

public void setCollaborateurs(Collection<Collaborateur> collaborateurs) {
	this.collaborateurs = collaborateurs;
}

public Administrateur getAdministrateur() {
	return administrateur;
}

public void setAdministrateur(Administrateur administrateur) {
	this.administrateur = administrateur;
}
   
   
   
}
