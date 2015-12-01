package rh.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Administrateur_RH extends Administrateur{

	public Administrateur_RH() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Administrateur_RH(Long id, String nom, String prenom) {
		super(id, nom, prenom);
		// TODO Auto-generated constructor stub
	}
	

}
