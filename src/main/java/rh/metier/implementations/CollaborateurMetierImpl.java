package rh.metier.implementations;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rh.entities.Collaborateur;
import rh.entities.Projet;
import rh.metier.interfaces.ICollaborateurMetier;
import rh.repository.CollaborateurRepository;
import rh.repository.ProjetRepository;
@Service
public class CollaborateurMetierImpl implements ICollaborateurMetier{
	@Autowired
	private CollaborateurRepository  collaborateurRepository;
	@Autowired
	private ProjetRepository projetRepository;

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
	public boolean addProjetToCollaborateur(Long idProjet, Long IdCollaborateur) {
		Projet p = projetRepository.findOne(idProjet);
		Collaborateur c =collaborateurRepository.findOne(IdCollaborateur);
		Set<Projet> projets = c.getProjets();
		Set<Collaborateur> collaborateurs = p.getCollaborateurs();
		projets.add(p);
		collaborateurs.add(c);
		collaborateurRepository.save(c);
		projetRepository.save(p);
		return true;
		
	}

}
