package rh.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Qualification implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	//private Theme theme;
	private String valeur;
	private int poids;
	public Qualification() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Qualification(Long id, String valeur, int poids) {
		super();
		this.id = id;
		this.valeur = valeur;
		this.poids = poids;
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
	public int getPoids() {
		return poids;
	}
	public void setPoids(int poids) {
		this.poids = poids;
	}

	
}
