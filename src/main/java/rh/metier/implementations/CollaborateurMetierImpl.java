package rh.metier.implementations;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import rh.entities.BilanPerformance;
import rh.entities.Collaborateur;
import rh.entities.Projet;
import rh.entities.ProjetCollaborateur;
import rh.metier.interfaces.ICollaborateurMetier;
import rh.repository.CollaborateurRepository;
import rh.repository.ProjetCollaborateurRepository;
import rh.repository.ProjetRepository;
@Service
public class CollaborateurMetierImpl implements ICollaborateurMetier{
	@Autowired
	private CollaborateurRepository  collaborateurRepository;
	@Autowired
	private ProjetRepository projetRepository;
	@Autowired
	private ProjetCollaborateurRepository projetCollaborateurRepository;

	@Override
	public Collaborateur addCollaborateur(Collaborateur c) {
		collaborateurRepository.save(c);
		return c;
	}

	@Override
	public Page<Collaborateur> getallcollaborateurs(int page) {
		return collaborateurRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Collaborateur getCollaborateur(Long refCollaborateur) {
		return collaborateurRepository.findOne(refCollaborateur);
	}

	@Override
	public void deleteCollaborateur(Long refCollaborateur) {
		collaborateurRepository.delete(refCollaborateur);
	}

	@Override
	public ProjetCollaborateur addProjetToCollaborateur(Long idProjet, Long IdCollaborateur, String rolejoue, int joursvalorises) {
		Projet p = projetRepository.findOne(idProjet);
		Collaborateur c =collaborateurRepository.findOne(IdCollaborateur);
		ProjetCollaborateur pc = new ProjetCollaborateur(rolejoue, joursvalorises, c, p);
		projetCollaborateurRepository.save(pc);
		return pc;
		 
		
	}

	@Override
	public Set<ProjetCollaborateur> getProjetsByCollaborateur(Long idCollaborateur) {
		
		return collaborateurRepository.getProjetsByCollaborateur(idCollaborateur);
	}

	/*@Override
	public Set<BilanPerformance> getBilansByCollaborateur(Long idCollaborateur) {
		return collaborateurRepository.getAllBilansByCollbaorateur(idCollaborateur);
	}*/

	@Override
	public Page<BilanPerformance> getLastBilanBycollaborateur(
			Long matriculeCollaborateur) {
		
		return collaborateurRepository.getLastbilanBycollaborateur(matriculeCollaborateur, new PageRequest(0, 1, Sort.Direction.DESC, "dateFin"));
	}

	@Override
	public String nouveauPoste(String poste, Long matriculeCollaborateur) {
		Collaborateur c = collaborateurRepository.findOne(matriculeCollaborateur);
		c.setPoste(poste);
		collaborateurRepository.save(c);
		return poste;
	}



	

}
