package rh.metier.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rh.entities.Projet;
import rh.entities.ProjetCollaborateur;
import rh.metier.interfaces.IProjetMetier;
import rh.repository.ProjetCollaborateurRepository;
import rh.repository.ProjetRepository;
@Service
public class projetMetierImpl implements IProjetMetier{
	@Autowired
   private ProjetRepository projetRepository;
	@Autowired
	private ProjetCollaborateurRepository projetCollaborateurRepository;
	@Override
	public Projet addProjet(Projet p) {
		
		projetRepository.save(p);
		//projetCollaborateurRepository.save(new ProjetCollaborateur(null, 0, null, p));
		 return p;
	}

	@Override
	public Projet getProjet(Long refProjet) {
		return projetRepository.findOne(refProjet);
	}

	@Override
	public Page<Projet> getAllprojets(int page) {
		return projetRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public void deleteProjet(Long refProjet) {
		 projetRepository.delete(refProjet);
	}

	@Override
	public ProjetCollaborateur findByIntitule(String nomprojet, Long idCollaborateur) {
		// TODO Auto-generated method stub
		return projetRepository.findProjetByintitule(nomprojet, idCollaborateur);
	}

}
