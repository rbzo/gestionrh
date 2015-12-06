package rh.controllers;

import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rh.entities.Collaborateur;
import rh.metier.ICollaborateurMetier;
import rh.repository.CollaborateurRepository;


@RestController
@RequestMapping("/collaborateurs")
public class collaborateurController {
	@Autowired
	private ICollaborateurMetier collaborateurMetier;
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Collaborateur ajoutcollabo(@RequestBody Collaborateur c){
		collaborateurMetier.addCollaborateur(c);
		return c;
	
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Page<Collaborateur> allcollabo(int page){
		return collaborateurMetier.getallcollaborateurs(page);
	}
	
	
	@RequestMapping("/{ref}")
	@ResponseBody
	   public Collaborateur getCollaborateur(@PathVariable Long ref){
		return collaborateurMetier.getCollaborateur(ref);
	}
	
	@RequestMapping(value="/{ref}", method=RequestMethod.DELETE)
	@ResponseBody
	public void supprimerCollaborateur(@PathVariable("ref") Long ref){
	      try {
	    	  collaborateurMetier.deleteCollaborateur(ref);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	