package rh.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rh.entities.Feedback;
import rh.entities.PageQualificationGlobale;
import rh.entities.Theme;
import rh.metier.interfaces.IfeedbackMetier;
import rh.repository.FeedbackRepository;

@RestController
@RequestMapping("/feedbacks")
public class feedbackController {
	@Autowired
	private IfeedbackMetier feedbackMetier;
	
	@RequestMapping("/testfb")
	@ResponseBody
	public String fbtest(){
		return "test feedback controler";
	}
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
	public Feedback ajouterFeedback(@RequestParam(value="collaborateur") Long matriculeCollaborateur,@RequestParam(value="projet") Long idProjet, @RequestBody Feedback f) {
		return feedbackMetier.ajouterFeedback(matriculeCollaborateur,idProjet, f);
	}
    @RequestMapping(value="/themesf", method=RequestMethod.POST)
    @ResponseBody
	public void addThemeToFeedback( @RequestParam(value="codeFeedback") Long codeFeedback, @RequestParam(value="codeTheme") Long codeTheme,@RequestParam(required=false) String remarque) {
		feedbackMetier.addThemeToFeedback(codeFeedback, codeTheme,remarque);
	
	}
    @RequestMapping("/{idfeedback}")
    @ResponseBody
	public Feedback getFeedback(@PathVariable Long idfeedback) {
		return feedbackMetier.getFeedback(idfeedback);
	}
    @RequestMapping(value="/collaborateurs/{idCollaborateur}")
    @ResponseBody
	public List<Feedback> findFeedbacksByCollaborateur(@PathVariable Long idCollaborateur) {
		return feedbackMetier.findFeedbacksByCollaborateur(idCollaborateur);
	}
    @RequestMapping(value="/themes", method=RequestMethod.GET)
    @ResponseBody
	public PageQualificationGlobale gethemesqualifies(@RequestParam Long idFeedback, @RequestParam int page, @RequestParam int size) {
		return feedbackMetier.gethemesqualifies(idFeedback, page, size);
	}
    
    @RequestMapping(value="/{idFeedback}/themes", method=RequestMethod.GET)
    @ResponseBody
    public Iterable<Theme> getThemesByfb(@PathVariable Long idFeedback){
    	return feedbackMetier.getThemesByfeedback(idFeedback);
    }
    
	
	


}
