package rh.metier;

import java.util.List;

import rh.entities.Theme;

public interface IthemeMetier {
	public Theme addTheme(Theme t  );
	public List<Theme> getallThemes();
	public Theme getTheme(Long idTheme);
	public boolean addQualificationToTheme(Long codeQualification, Long codeTheme);

}
