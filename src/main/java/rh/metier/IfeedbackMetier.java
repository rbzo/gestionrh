package rh.metier;

import java.util.List;
import java.util.Set;

import rh.entities.Feedback;
import rh.entities.PageQualificationGlobale;
import rh.entities.Theme;


public interface IfeedbackMetier {
	public Feedback ajouterFeedback(Long matriculeCollaborateur,  Feedback f );
	public void addThemeToFeedback(Long codeFeedback, Long codeTheme);
	public Feedback getFeedback(Long idfeedback);
	public List<Feedback> findFeedbacksByCollaborateur(Long idCollaborateur);
	public PageQualificationGlobale gethemesqualifies(Long idFeedback, int page, int size);
	public Set<Theme> getThemesByfeedback(Long idFeedback);

}
