package rh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rh.entities.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
	

}
