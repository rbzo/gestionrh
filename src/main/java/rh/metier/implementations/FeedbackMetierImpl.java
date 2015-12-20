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
	
	
	
	@Override
	//faire ca comme une mise à jour
	public void addThemeToFeedback(Long codeFeedback, Long codeTheme, String remarque) {
		//on cherche le theme correspondant a lid
		Theme th=themeRepository.findOne(codeTheme);
		//le feedback existe til deja?
		Feedback f = feedbackRepository.findOne(codeFeedback);
		System.out.println(f);
		Set<FeedbackThemes> ft = feedbackRepository.findByFeedback(codeFeedback);

		for (FeedbackThemes feedbackThemes : ft) {
			
			System.out.println("les themes: "+feedbackThemes.getTheme());
			
		}
		
		feedbackThemesRepository.save(new FeedbackThemes(f, th, null,remarque));
		// s'il n'existe pas on genere lexception
		/*if(f==null){
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
					 
					 ft.add(new FeedbackThemes(f, th, null,remarque));
				 ft.setFeedback(f);
				 ft.setTheme(th);
				 feedbackThemesRepository.save(new FeedbackThemes(f, th, null,remarque));
				 }
			 }
		}*/
		
	}
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
		  /*int poidsglobal =0;
		int nbreThemesQualifies=0;*/
		
		//on recupere tous les themes du feedback 
		Page<FeedbackThemes> themesByFeedback = feedbackRepository.getThemesV2(idFeedback, new PageRequest(p,s));
		//on cree une page de qualification globale
		PageQualificationGlobale pqp= new PageQualificationGlobale();
		int poidsglobal = pqp.getTotalPoids();
		int nbreThemesQualifies = pqp.getNbreThemesQualifies();
		
		
		//
		Set<FeedbackThemes> ft =feedbackRepository.findByFeedback(idFeedback);
		//on lui assigne les themes
	    Set<Qualification> qualifs = new HashSet<Qualification>() ;
	    for (FeedbackThemes ftcourant : ft) {
			qualifs.add(ftcourant.getQualification());
	    }
	
		pqp.setThemes(themesByFeedback.getContent());
		//on recupere le nombre total de themes qualifies
		/*for ( FeedbackThemes ftcourant : ft) {
			if (!ftcourant.getQualification().getValeur().equals("NA")) {
				nbreThemesQualifies++;
			}
		
		}*/
		for (Qualification qualification : qualifs) {
			if(!qualification.getValeur().equals("NA")){
				
			nbreThemesQualifies++;
			}
		}
		
		
		pqp.setNbreThemesQualifies(nbreThemesQualifies);
		System.out.println(pqp.getNbreThemesQualifies());
		for (Qualification qualification : qualifs) {
			int poidsCourant = qualification.getPoids();
			poidsglobal=poidsglobal+poidsCourant;
			
		}
		/*for (FeedbackThemes ftcourant : ft) {
			
			int poidsCourant  =ftcourant.getQualification().getPoids();
			poidsglobal=poidsglobal+poidsCourant;
		}*/
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
	@Override
	public boolean addBilanToFeedback(Long idBilan, Long idFeedbak) {
		BilanPerformance b = bilanRepository.findOne(idBilan);
		Feedback f = feedbackRepository.findOne(idFeedbak);
		f.setBilanPerformance(b);
		feedbackRepository.save(f);
		return true;
	}
	
	

}
