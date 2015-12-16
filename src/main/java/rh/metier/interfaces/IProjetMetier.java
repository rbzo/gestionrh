package rh.metier.interfaces;

import org.springframework.data.domain.Page;

import rh.entities.Projet;
import rh.entities.ProjetCollaborateur;

public interface IProjetMetier {
	public Projet addProjet(Projet p);
	public Projet getProjet(Long refProjet);
	public Page<Projet> getAllprojets(int page);
	public void deleteProjet(Long refProjet);
    public ProjetCollaborateur findByIntitule(String nomprojet, Long idcollaborateur);
}
