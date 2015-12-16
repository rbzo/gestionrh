package rh.metier.implementations;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
	public boolean addProjetToCollaborateur(Long idProjet, Long IdCollaborateur, String rolejoue, int joursvalorises) {
		Projet p = projetRepository.findOne(idProjet);
		Collaborateur c =collaborateurRepository.findOne(IdCollaborateur);
		projetCollaborateurRepository.save(new ProjetCollaborateur(rolejoue, joursvalorises, c, p));
		return true;
		
	}

	@Override
	public Set<ProjetCollaborateur> getProjetsByCollaborateur(Long idCollaborateur) {
		
		return collaborateurRepository.getProjetsByCollaborateur(idCollaborateur);
	}

}
