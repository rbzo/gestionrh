package rh.metier.implementations;



import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rh.entities.BilanPerformance;
import rh.entities.Collaborateur;
import rh.entities.Feedback;
import rh.entities.FeedbackThemes;
import rh.entities.PageQualificationGlobale;
import rh.entities.ProjetCollaborateur;
import rh.entities.Qualification;
import rh.entities.Theme;
import rh.metier.interfaces.IProjetMetier;
import rh.metier.interfaces.IfeedbackMetier;
import rh.repository.BilanRepository;
import rh.repository.CollaborateurRepository;
import rh.repository.FeedbackThemesRepository;
import rh.repository.ProjetCollaborateurRepository;
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
	@Autowired
	private ProjetCollaborateurRepository projetCollaborateurRepository;
	@Autowired
	private BilanRepository bilanRepository;
	
	
	/**
	 * ajouter un theme à un feedback lors de la qualification globale
	 * 
	 * @param codeFeedback identifiant du feedback
	 * @param codeTheme idetifiant du theme
	 * @param remarque
	 */
	@Override
	public void addThemeToFeedback(Long codeFeedback, Long codeTheme, String remarque) {
		
		Theme th=themeRepository.findOne(codeTheme); //on cherche le theme correspondant a lid
		Feedback f = feedbackRepository.findOne(codeFeedback);  //le feedback existe til deja?
		System.out.println(f);
		Set<FeedbackThemes> ft = feedbackRepository.findByFeedback(codeFeedback);
		feedbackThemesRepository.save(new FeedbackThemes(f, th, null,remarque));
		
	}
	
	/**
	 * renseigner un feedback
	 * @param matriculeCollaborateur 
	 * @param idProjet
	 * @param f feedback
	 * @return Feedback
	 */
	public Feedback ajouterFeedback(Long matriculeCollaborateur, Long idProjet, Feedback f) {
		     Collaborateur c = collaborateurRepository.findOne(matriculeCollaborateur);
		     ProjetCollaborateur pc = projetCollaborateurRepository.findOne(idProjet);
		     f.setCollaborateur(c);
		     f.setProjetCollaborateur(pc);
		     c.getFeedbacks().add(f);
		     feedbackRepository.save(f);
		     //feedbackThemesRepository.save(new FeedbackThemes(f, null));
		     collaborateurRepository.saveAndFlush(c);
		     return f;
	}
	
	/**
	 * recuperer un feedback
	 * @param idfeedback
	 * @return Feedback
	 */
	@Override
	public Feedback getFeedback(Long idfeedback) {
		return feedbackRepository.findOne(idfeedback);
	}
	
	/**
	 * recuperer tous les feedback d'un collaborateur
	 * 
	 * @param idCollaborateur matricule du collaborateur
	 * @return {@link List} Feedback
	 */
	@Override
	public List<Feedback> findFeedbacksByCollaborateur(Long idCollaborateur) {
		return feedbackRepository.findFeedbacksByCollaborateur(idCollaborateur);
		
	}
	
	/**
	 * recuperer tous les themes qualifies lors d'un feedback
	 * 
	 * @param idFeedback
	 * @param p numero de la page
	 * @param s taille de la page
	 * 
	 */
	@Override
	public PageQualificationGlobale gethemesqualifies(Long idFeedback, int p, int s) {
	
		Page<FeedbackThemes> themesByFeedback = feedbackRepository.getThemesV2(idFeedback, new PageRequest(p,s));//on recupere tous les themes du feedback 
		
		PageQualificationGlobale pqp= new PageQualificationGlobale();//on cree une page de qualification globale
		int poidsglobal = pqp.getTotalPoids();
		int nbreThemesQualifies = pqp.getNbreThemesQualifies();
		Set<FeedbackThemes> ft =feedbackRepository.findByFeedback(idFeedback);
		//on lui assigne les themes
	    Set<Qualification> qualifs = new HashSet<Qualification>() ;
	    for (FeedbackThemes ftcourant : ft) {
			qualifs.add(ftcourant.getQualification());
	    }
	
		pqp.setThemes(themesByFeedback.getContent());
		for (Qualification qualification : qualifs) {
			if(!qualification.getValeur().equals("NA")){
				
			nbreThemesQualifies++;
			}
		}
		pqp.setNbreThemesQualifies(nbreThemesQualifies);
		for (Qualification qualification : qualifs) {
			int poidsCourant = qualification.getPoids();
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
	
	/**
	 * ajouter un feedback à un bilan
	 * 
	 * @param idBilan
	 * @param idFeedbak
	 * @return boolean
	 */
	@Override
	public boolean addBilanToFeedback(Long idBilan, Long idFeedbak) {
		BilanPerformance b = bilanRepository.findOne(idBilan);
		Feedback f = feedbackRepository.findOne(idFeedbak);
		f.setBilanPerformance(b);
		feedbackRepository.save(f);
		return true;
	}
	
	

}
