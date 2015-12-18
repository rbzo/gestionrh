package rh.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, length=3, name="TYPE_BILAN")

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include=JsonTypeInfo.As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(name="BAP", value=BAP.class),
	@Type(name="BIP", value=BIP.class)
})

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
public class BilanPerformance implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private Date dateDebut;
    private Date dateFin;
    private String statut;
    
    @OneToMany(mappedBy="bilanPerformance")
    private Collection<EvaluationObjectif> objectifs;
    
    @OneToMany(mappedBy="bilanPerformance")
    private Collection<Feedback> feedbacks;
    
    @ManyToOne
    @JoinColumn(name="ID_COLLABORATEUR")
    private Collaborateur collaborateur;

	public BilanPerformance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BilanPerformance(Long id, Date dateDebut, Date dateFin, String statut) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.statut = statut;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}


	public Collection<EvaluationObjectif> getObjectifs() {
		return objectifs;
	}

	public void setObjectifs(Collection<EvaluationObjectif> objectifs) {
		this.objectifs = objectifs;
	}

	public Collection<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(Collection<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}
	
	
    
    
}
