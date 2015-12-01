package rh.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BAP")
public class BAP extends BilanPerformance{

	public BAP() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BAP(Long id, Date dateDebut, Date dateFin, String statut) {
		super(id, dateDebut, dateFin, statut);
		// TODO Auto-generated constructor stub
	}
	

}
