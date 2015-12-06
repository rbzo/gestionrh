package rh.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rh.entities.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>{
	@Query("select t from Theme t where t.feedback.id=:x")
	public Page<Theme> getThemesByFeedback(@Param("x") Long idFeedback, Pageable p) ;

}
