package rh.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BIP")
public class BIP extends BilanPerformance{

	public BIP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BIP(Long id, Date dateDebut, Date dateFin, String statut) {
		super(id, dateDebut, dateFin, statut);
		// TODO Auto-generated constructor stub
	}
	

}
