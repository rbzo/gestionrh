package rh.metier;

import org.springframework.data.domain.Page;

import rh.entities.Projet;

public interface IProjetMetier {
	public Projet addProjet(Projet p);
	public Projet getProjet(Long refProjet);
	public Page<Projet> getAllprojets(int page);
	public void deleteProjet(Long refProjet);

}
