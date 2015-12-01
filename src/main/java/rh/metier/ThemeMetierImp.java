package rh.metier;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rh.entities.Qualification;
import rh.entities.Theme;
import rh.repository.QualificationRepository;
import rh.repository.ThemeRepository;
@Service
public class ThemeMetierImp implements IthemeMetier{
    @Autowired
	private ThemeRepository themeRepository;
    @Autowired
    private QualificationRepository qualificationRepository;
    
	public Theme addTheme(Theme t) {
		themeRepository.save(t);
		return t;
	}
	

	@Override
	public List<Theme> getallThemes() {
		
		return themeRepository.findAll();
	}


	@Override
	public void addQualificationToTheme(Long codeQualification, Long codeTheme) {
		Qualification q = qualificationRepository.findOne(codeQualification);
		Theme t= themeRepository.findOne(codeTheme);
		t.setQualification(q);
		
	}


	@Override
	public Theme getTheme(Long idTheme) {
		return themeRepository.findOne(idTheme);
	}
	

}
