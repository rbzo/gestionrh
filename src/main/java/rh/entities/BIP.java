package rh.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("BIP")
public class BIP extends BilanPerformance{
	private String planAmelioration;
	

	public String getPlanAmelioration() {
		return planAmelioration;
	}


	public void setPlanAmelioration(String planAmelioration) {
		this.planAmelioration = planAmelioration;
	}


	public BIP() {
		super();
	}


	public BIP(Long id, Date dateDebut, Date dateFin, String planAmelioration) {
		super(id, dateDebut, dateFin);
		this.planAmelioration = planAmelioration;
	}


	public BIP(Long id, Date dateDebut, Date dateFin) {
		super(id, dateDebut, dateFin);
		// TODO Auto-generated constructor stub
	}
	
	

	

}
