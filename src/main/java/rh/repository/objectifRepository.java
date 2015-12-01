package rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rh.entities.Objectif;

public interface objectifRepository extends JpaRepository<Objectif, Long> {
   
}
