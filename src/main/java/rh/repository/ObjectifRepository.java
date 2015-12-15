package rh.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rh.entities.Evaluation;
import rh.entities.Feedback;
import rh.entities.Objectif;

public interface ObjectifRepository extends JpaRepository<Objectif, Long> {
   
	// liste des evaluations d'un objectif identifié par son id
	@Query("select eo.evaluation from EvaluationObjectif eo where eo.objectif.id=:x")
	Set<Evaluation> getevaluationsByObjectif(@Param("x") Long idObjectif);
	
	//les objectifs d'un collaborateur
	@Query("select o from Objectif o where o.collaborateur.id=:x")
	public Set<Objectif> findOjectifsByCollaborateur(@Param("x") Long idCollaborateur);

}
