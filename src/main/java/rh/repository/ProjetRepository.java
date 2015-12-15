package rh.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rh.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {
   //rechercher un projet via son nom
	@Query("select p from Projet p where p.intitule=:x")
	public Projet findProjetByintitule(@Param("x") String nomprojet);

 
}
