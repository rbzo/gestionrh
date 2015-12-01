package rh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rh.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
	@Query("select f from Feedback f where f.collaborateur.id=:x")
	public List<Feedback> findFeedbacksByCollaborateur(Long idCollaborateur);

}
