package rh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rh.entities.Theme;
import rh.metier.IthemeMetier;

@RestController
@RequestMapping("/themes")
public class themeController {
	@Autowired
	private IthemeMetier themeMetier;
    
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Theme> allThemes(){
		return themeMetier.getallThemes();
	}
	
	@RequestMapping(method=RequestMethod.POST )
	@ResponseBody
	public Theme addTheme(@RequestBody Theme t){
		 themeMetier.addTheme(t);
		 return t;
	}
	
	@RequestMapping(value="/{ref}", method=RequestMethod.GET)
	@ResponseBody
	public Theme getTheme(@PathVariable Long ref){
		return themeMetier.getTheme(ref);
	}
	
	@RequestMapping(value="/feedback/{refTheme}", method=RequestMethod.POST)
	@ResponseBody
	public boolean addqualificationTotheme( @RequestParam(value="feedback") Long codeFeedback, @PathVariable Long refTheme, @RequestParam(value="qualification") Long codequalification){
		 return themeMetier.addQualificationToTheme(codeFeedback, refTheme, codequalification);
	}
	
	//
	@RequestMapping(value="/feedbacks/{ref}", method=RequestMethod.GET)
	@ResponseBody
	public List<Theme> getThemeparfb(@PathVariable Long ref){
		return themeMetier.getthemeparfb(ref);
	}

	

}
