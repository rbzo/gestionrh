package rh.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rh.entities.Collaborateur;
import rh.entities.Projet;
import rh.entities.ProjetCollaborateur;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long>{
	
	@Query("select c  from Collaborateur c ")
	

	  public Set<Collaborateur> allcollabo();
	//liste des projets d'un collaborateur identifié par son id
		@Query("select pc from ProjetCollaborateur pc where pc.collaborateur.id=:x")
		Set<ProjetCollaborateur> getProjetsByCollaborateur(@Param("x") Long idCollaborateur);
		
	//role et nombre de jours valorises d'un collaborateur sur un projet
		
	
   
}
