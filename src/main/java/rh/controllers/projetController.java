package rh.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rh.entities.Projet;
import rh.metier.interfaces.IProjetMetier;


@RestController
@RequestMapping("/projets")
public class projetController {
	@Autowired
   private IProjetMetier projetMetier;
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Page<Projet> allProjects(int page){
		return projetMetier.getAllprojets(page);
	}
	
	@RequestMapping(method=RequestMethod.POST )
	@ResponseBody
	public Projet addProjet(@RequestBody Projet p){
		 projetMetier.addProjet(p);
		 return p;
	}
	
	@RequestMapping(value="/{ref}", method=RequestMethod.GET)
	@ResponseBody
	public Projet getProjet(@PathVariable Long ref){
		return projetMetier.getProjet(ref);
	}
	
	@RequestMapping(value="/{ref}", method=RequestMethod.DELETE)
	@ResponseBody
	public void supprimerProjet(@PathVariable("ref") Long ref){
	      try {
	    	  projetMetier.deleteProjet(ref);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    @RequestMapping("/recherche")
	public Projet findByIntitule(@RequestParam String nomprojet) {
		return projetMetier.findByIntitule(nomprojet);
	}
}
