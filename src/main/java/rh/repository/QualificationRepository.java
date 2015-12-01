package rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rh.entities.Qualification;

public interface QualificationRepository extends JpaRepository<Qualification, Long> {

}
