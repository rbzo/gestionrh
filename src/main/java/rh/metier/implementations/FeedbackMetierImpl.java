package rh.metier.implementations;



import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rh.entities.Collaborateur;
import rh.entities.Feedback;
import rh.entities.FeedbackThemes;
import rh.entities.PageQualificationGlobale;
import rh.entities.Theme;
import rh.metier.interfaces.IfeedbackMetier;
import rh.repository.CollaborateurRepository;
import rh.repository.FeedbackThemesRepository;
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
	@Autowired
	private FeedbackThemesRepository feedbackThemesRepository;
	public int poidsglobal = 0;
	public int nbreThemesQualifies;
	
	
	
	@Override
	//faire ca comme une mise à jour
	public void addThemeToFeedback(Long codeFeedback, Long codeTheme) {
		//on cherche le theme correspondant a lid
		Theme th=themeRepository.findOne(codeTheme);
		//le feedback existe til deja?
		Feedback f = feedbackRepository.findOne(codeFeedback);
		FeedbackThemes ft = feedbackThemesRepository.findByFeedback(codeFeedback);
		// s'il n'existe pas on genere lexception
		if(f==null){
			throw new RuntimeException("feedback inexistant");
		}
		else{
			// le feedback existe déjà- a-t-il le theme demandé ?
			boolean trouvé = false;
			 for (Theme t : feedbackRepository.getThemes(f.getId())){
				 
				 if (t.getValeur().equals(th.getValeur())) {
					  trouvé = true;
					 break;
					 }
			  // si pas trouvé, on crée la relation avec le theme
				 if (!trouvé) {
				 ft.setFeedback(f);
				 ft.setTheme(th);
				 feedbackThemesRepository.save(ft);
				 }
			 }
		}
		
	}
	public Feedback ajouterFeedback(Long matriculeCollaborateur, Feedback f) {
		     Collaborateur c = collaborateurRepository.findOne(matriculeCollaborateur);
		     f.setCollaborateur(c);
		     c.getFeedbacks().add(f);
		     feedbackRepository.save(f);
		     collaborateurRepository.saveAndFlush(c);
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
		//on recupere tous les themes du feedback 
		Page<Theme> themesByFeedback = feedbackRepository.getThemesV2(idFeedback, new PageRequest(p,s));
		//on cree une page de qualification globale
		PageQualificationGlobale pqp= new PageQualificationGlobale();
		//on lui assigne les themes
		pqp.setThemes(themesByFeedback.getContent());
		//on recupere le nombre total de themes qualifies
		for (Theme t1 : pqp.getThemes()) {
			if (!t1.getQualification().getValeur().equals("NA")) {
				nbreThemesQualifies++;
			}
		}
		
		
		pqp.setNbreThemesQualifies(nbreThemesQualifies);
		System.out.println(pqp.getNbreThemesQualifies());
		for (Theme theme : pqp.getThemes()) {
			
			int poidsCourant  =theme.getQualification().getPoids();
			poidsglobal=poidsglobal+poidsCourant;
		}
		pqp.setTotalPoids(poidsglobal);
		System.out.println(pqp.getTotalPoids());
		if(pqp.getNbreThemesQualifies()!=0){
		pqp.setNoteGlobale((float)pqp.getTotalPoids()/pqp.getNbreThemesQualifies());
		}
		
		System.out.println(pqp.getNoteGlobale());
		return pqp;
	}
	@Override
	public Set<Theme> getThemesByfeedback(Long idFeedback) {
		
		return feedbackRepository.getThemes(idFeedback);
	}
	
	

}
