package rh.metier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import rh.entities.Projet;
import rh.repository.ProjetRepository;
@Service
public class projetMetierImpl implements IProjetMetier{
	@Autowired
   private ProjetRepository projetRepository;
	@Override
	public Projet addProjet(Projet p) {
		projetRepository.save(p);
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

}
