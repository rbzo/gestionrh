package rh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rh.entities.Feedback;
import rh.metier.IfeedbackMetier;
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
	public Feedback ajouterFeedback(Long matriculeCollaborateur, @RequestBody Feedback f) {
		return feedbackMetier.ajouterFeedback(matriculeCollaborateur, f);
	}
    @RequestMapping(value="/{codeFeedback}", method=RequestMethod.POST)
    @ResponseBody
	public void addThemeToFeedback(@PathVariable Long codeFeedback, Long codeTheme) {
		feedbackMetier.addThemeToFeedback(codeFeedback, codeTheme);
	}
    @RequestMapping("/{idfeedback")
    @ResponseBody
	public Feedback getFeedback(@PathVariable Long idfeedback) {
		return feedbackMetier.getFeedback(idfeedback);
	}
    @RequestMapping(value="/collaborateur/{idCollaborateur}")
	public List<Feedback> findFeedbacksByCollaborateur(@PathVariable Long idCollaborateur) {
		return feedbackMetier.findFeedbacksByCollaborateur(idCollaborateur);
	}
	
	


}
