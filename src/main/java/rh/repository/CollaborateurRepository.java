package rh.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rh.entities.Collaborateur;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long>{
	
	@Query("select c  from Collaborateur c ")
	

	public Set<Collaborateur> allcollabo();
	
   
}
