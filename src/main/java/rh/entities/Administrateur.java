package rh.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Administrateur implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nom;
	private String prenom;
	
	@OneToMany(mappedBy="administrateur", cascade=CascadeType.ALL)
	private Collection<ManagerRh> managerRhs;

	public Administrateur() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrateur(Long id, String nom, String prenom) {
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

	public Collection<ManagerRh> getManagerRhs() {
		return managerRhs;
	}

	public void setManagerRhs(Collection<ManagerRh> managerRhs) {
		this.managerRhs = managerRhs;
	}
	

}
