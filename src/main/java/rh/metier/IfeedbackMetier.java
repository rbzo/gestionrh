package rh.metier;

import java.util.List;

import rh.entities.Feedback;


public interface IfeedbackMetier {
	public Feedback ajouterFeedback(Long matriculeCollaborateur,  Feedback f );
	public void addThemeToFeedback(Long codeFeedback, Long codeTheme);
	public Feedback getFeedback(Long idfeedback);
	public List<Feedback> findFeedbacksByCollaborateur(Long idCollaborateur);

}
