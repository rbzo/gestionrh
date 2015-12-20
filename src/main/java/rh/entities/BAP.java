package rh.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("BAP")
public class BAP extends BilanPerformance{
	
	private String statut;
    private String decision;
	
	
	@OneToMany(mappedBy="bilanPerformance")
    private Collection<Feedback> feedbacks;

	public BAP() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	public BAP(Long id, Date dateDebut, Date dateFin) {
		super(id, dateDebut, dateFin);
		// TODO Auto-generated constructor stub
	}
    
	
	
   


	public String getDecision() {
		return decision;
	}




	public BAP(Long id, Date dateDebut, Date dateFin, String statut,
			String decision) {
		super(id, dateDebut, dateFin);
		this.statut = statut;
		this.decision = decision;
	}




	public void setDecision(String decision) {
		this.decision = decision;
	}
	

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}


	

	
	public Collection<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Collection<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}
	

}
