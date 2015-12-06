package rh.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import rh.entities.Qualification;
import rh.metier.IQualificationMetier;
import rh.repository.QualificationRepository;

@RestController
@RequestMapping("/qualifications")
public class qualificationController {
	@Autowired
	private IQualificationMetier qualificationMetier;

	
	@RequestMapping( method=RequestMethod.POST)
	@ResponseBody
	public Qualification addQualification(@RequestBody Qualification q){
		qualificationMetier.addQualification(q);
		return q;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public List<Qualification> getAllQualification(){
		return qualificationMetier.getAllQualification();
		
	}

}
