package rh.metier.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import rh.entities.Theme;

public interface IthemeMetier {
	public Theme addTheme(Theme t  );
	public List<Theme> getallThemes();
	public Theme getTheme(Long idTheme);
	public boolean addQualificationToTheme(Long codeQualification, Long codeTheme,Long codeFeedback);
	public List<Theme> getthemeparfb(Long codefb);
	//public List<Theme> getthemeparfbcol(Long matcol);

}
