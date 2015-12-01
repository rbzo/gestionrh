package rh.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import rh.entities.Projet;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

 
}
