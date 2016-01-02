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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	private String intitule;
    private Date dateDebut;
    private Date dateFin;
    
    
    @OneToMany(mappedBy="bilanPerformance")
    private Collection<EvaluationObjectif> objectifsAnneecourante;
    
    @OneToMany(mappedBy="bilanPerformance")
    private Collection<Objectif> objectifsAnneeProchaine;

    @ManyToOne
    @JoinColumn(name="ID_COLLABORATEUR")
    private Collaborateur collaborateur;
    

	public BilanPerformance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BilanPerformance(Long id, Date dateDebut, Date dateFin) {
		super();
		this.id = id;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}


	public Collaborateur getCollaborateur() {
		return collaborateur;
	}

	public void setCollaborateur(Collaborateur collaborateur) {
		this.collaborateur = collaborateur;
	}

	public Collection<EvaluationObjectif> getObjectifsAnneecourante() {
		return objectifsAnneecourante;
	}

	public void setObjectifsAnneecourante(
			Collection<EvaluationObjectif> objectifsAnneecourante) {
		this.objectifsAnneecourante = objectifsAnneecourante;
	}

	public Collection<Objectif> getObjectifsAnneeProchaine() {
		return objectifsAnneeProchaine;
	}

	public void setObjectifsAnneeProchaine(
			Collection<Objectif> objectifsAnneeProchaine) {
		this.objectifsAnneeProchaine = objectifsAnneeProchaine;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	
	
	
	
	
    
    
}
