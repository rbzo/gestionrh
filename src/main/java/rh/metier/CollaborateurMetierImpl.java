package rh.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rh.entities.Collaborateur;
import rh.repository.CollaborateurRepository;
@Service
public class CollaborateurMetierImpl implements ICollaborateurMetier{
	@Autowired
	private CollaborateurRepository  collaborateurRepository;

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

}
