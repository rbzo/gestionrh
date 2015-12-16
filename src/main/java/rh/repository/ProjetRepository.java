package rh.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rh.entities.Projet;
import rh.entities.ProjetCollaborateur;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
   //rechercher un projet via son nom
	@Query("select p from ProjetCollaborateur p where p.projet.intitule=:x and p.collaborateur.id=:y")
	public ProjetCollaborateur findProjetByintitule(@Param("x") String nomprojet, @Param("y") Long idProjet);

 
}
