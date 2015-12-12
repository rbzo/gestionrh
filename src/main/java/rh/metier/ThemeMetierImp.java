package rh.metier;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.ThemeSource;

import rh.entities.Feedback;
import rh.entities.FeedbackThemes;
import rh.entities.Qualification;
import rh.entities.Theme;
import rh.repository.FeedbackRepository;
import rh.repository.FeedbackThemesRepository;
import rh.repository.QualificationRepository;
import rh.repository.ThemeRepository;
@Service
public class ThemeMetierImp implements IthemeMetier{
    @Autowired
	private ThemeRepository themeRepository;
    @Autowired
    private QualificationRepository qualificationRepository;
    @Autowired
    private FeedbackThemesRepository feedbackThemesRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    
	public Theme addTheme(Theme t) {
		themeRepository.save(t);
		return t;
	}
	

	@Override
	public List<Theme> getallThemes() {
		
		return themeRepository.findAll();
	}


	@Override
	public boolean addQualificationToTheme(Long codeFeedback,Long codeTheme,Long codeQualification) {
		//on cherche le feedback
		Feedback f =feedbackRepository.findOne(codeFeedback);
		//on recupere la qualification à ajouter
		Qualification q = qualificationRepository.findOne(codeQualification);
		//on recupere le theme dans la liste de themes
		Theme ta = themeRepository.findOne(codeTheme);
		//on trouve son equivalent dans la liste des themes du feedback
		Set<Theme> ths= feedbackRepository.getThemes(codeFeedback);
		for (Theme t : ths) {
			if(t.getValeur().equals(ta.getValeur())){
				t.setQualification(q);
				//on enregistre le feedback mis à jour
				feedbackThemesRepository.save(new FeedbackThemes(f, t));
				
			}
			
		}return true;
		
		
	}


	@Override
	public Theme getTheme(Long idTheme) {
		return themeRepository.findOne(idTheme);
	}


	@Override
	public 	List<Theme> getthemeparfb(Long codefb) {
	
		/*return themeRepository.getThemesByFeedback(codefb);*/
		return null;
	}


	/*@Override
	public List<Theme> getthemeparfbcol(Long matcol) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

}
