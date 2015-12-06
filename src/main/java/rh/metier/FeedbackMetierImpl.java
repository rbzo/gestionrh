package rh.metier;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rh.entities.Collaborateur;
import rh.entities.Feedback;
import rh.entities.PageQualificationGlobale;
import rh.entities.Theme;
import rh.repository.CollaborateurRepository;
import rh.repository.ThemeRepository;
import rh.repository.FeedbackRepository;
@Service
public class FeedbackMetierImpl implements IfeedbackMetier{
	@Autowired
    private CollaborateurRepository  collaborateurRepository;
	@Autowired
	private ThemeRepository themeRepository;
	@Autowired
	private FeedbackRepository feedbackRepository;
	public int poidsglobal = 0;
	
	@Override
	//faire ca comme une mise à jour
	public void addThemeToFeedback(Long codeFeedback, Long codeTheme) {
		Theme th=themeRepository.findOne(codeTheme);
		Feedback f = feedbackRepository.findOne(codeFeedback);
		f.getThemes().add(th);
		feedbackRepository.saveAndFlush(f);
		
	}
	public Feedback ajouterFeedback(Long matriculeCollaborateur, Feedback f) {
		     Collaborateur c = collaborateurRepository.findOne(matriculeCollaborateur);
		     f.setCollaborateur(c);
		     feedbackRepository.save(f);
		     return f;
	}
	@Override
	public Feedback getFeedback(Long idfeedback) {
		return feedbackRepository.findOne(idfeedback);
	}
	@Override
	public List<Feedback> findFeedbacksByCollaborateur(Long idCollaborateur) {
		return feedbackRepository.findFeedbacksByCollaborateur(idCollaborateur);
		
	}
	@Override
	public PageQualificationGlobale gethemesqualifies(Long idFeedback, int p, int s) {
		Page<Theme> themesByFeedback = themeRepository.getThemesByFeedback(idFeedback, new PageRequest(p,s));
		PageQualificationGlobale pqp= new PageQualificationGlobale();
		pqp.setThemes(themesByFeedback.getContent());
		pqp.setNbreThemesQualifies((int) themesByFeedback.getTotalElements());
		for (Theme theme : pqp.getThemes()) {
			
			int poidsCourant  =theme.getQualification().getPoids();
			poidsglobal=poidsglobal+poidsCourant;
		}
		pqp.setTotalPoids(poidsglobal);
		while(pqp.getNbreThemesQualifies()!=0){
		pqp.setNoteGlobale(pqp.getTotalPoids()/pqp.getNbreThemesQualifies());
		}
		return pqp;
	}
	
	

}
