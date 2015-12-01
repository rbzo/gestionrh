package rh.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("DLG")
public class Delegue extends Administrateur {

	public Delegue() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Delegue(Long id, String nom, String prenom) {
		super(id, nom, prenom);
		// TODO Auto-generated constructor stub
	}
	

}
