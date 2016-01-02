package rh.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rh.entities.BilanPerformance;
import rh.entities.Collaborateur;
import rh.entities.Projet;
import rh.entities.ProjetCollaborateur;
import rh.metier.interfaces.ICollaborateurMetier;
import rh.metier.interfaces.IProjetMetier;
import rh.repository.CollaborateurRepository;


@RestController
@RequestMapping("/collaborateurs")
public class collaborateurController {
	

	@Autowired
	private ICollaborateurMetier collaborateurMetier;
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	//@Secured(value={"ROLE_ADMIN"})
	public Object ajoutcollabo(@RequestBody @Valid Collaborateur c, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			Map<String, Object> errors  = new HashMap<>();
			errors.put("errors", true);
			for (FieldError fe : bindingResult.getFieldErrors()) {
				errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return errors;
		}
		collaborateurMetier.addCollaborateur(c);
		return c;
	
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	//@Secured(value={"ROLE_ADMIN"})
	public Page<Collaborateur> allcollabo(int page){
		return collaborateurMetier.getallcollaborateurs(page);
	}
	
	
	@RequestMapping("/{ref}")
	@ResponseBody
	   public Collaborateur getCollaborateur(@PathVariable Long ref){
		Collaborateur c = collaborateurMetier.getCollaborateur(ref);
		if(c==null){
			throw new NotFoundException("Collaborateur non trouvé avec le matricule:"+ref);
		}
		/*if (bindingResult.hasErrors()) {
			Map<String, Object> errors = new HashMap<>();
			errors.put("errors", true);
			for (FieldError fe : bindingResult.getFieldErrors()) {
				errors.put(fe.getField(), fe.getDefaultMessage());
			}
			return errors;
		}*/
		return c;
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
	@RequestMapping(value="/{IdCollaborateur}/projets", method=RequestMethod.POST)
	@ResponseBody
	public ProjetCollaborateur addProjetToCollaborateur(@RequestParam(value="projet") Long idProjet, @PathVariable Long IdCollaborateur, @RequestParam(value="role") String rolejoue, @RequestParam(value="jours") int joursvalorises) {
		return collaborateurMetier.addProjetToCollaborateur(idProjet, IdCollaborateur, rolejoue, joursvalorises);
	}
    @RequestMapping(value="/{idCollaborateur}/projets", method =RequestMethod.GET)
	public Set<ProjetCollaborateur> getProjetsByCollaborateur(@PathVariable Long idCollaborateur) {
		return collaborateurMetier.getProjetsByCollaborateur(idCollaborateur);
	}
    @RequestMapping(value="/{idCollaborateur}/poste" ,method=RequestMethod.PUT)
	@ResponseBody
	public boolean nouveauPoste(@RequestParam String poste, @PathVariable(value="idCollaborateur") Long matriculeCollaborateur) {
		 collaborateurMetier.nouveauPoste(poste, matriculeCollaborateur);
		 return true;
	}
    
  /*  @RequestMapping(value="/{idCollaborateur}/bilans", method = RequestMethod.GET)
	public Set<BilanPerformance> getBilansByCollaborateur(@PathVariable Long idCollaborateur) {
		return collaborateurMetier.getBilansByCollaborateur(idCollaborateur);
	}*/
    /*@RequestMapping(value="{idCollaborateur}/bilans/last", method=RequestMethod.POST)
	public Page<BilanPerformance> getLastBilanBycollaborateur(@PathVariable Long idCollaborateur) {
		return collaborateurMetier.getLastBilanBycollaborateur(idCollaborateur);
	}*/
    
    
    
    
    
    
	
}
	
