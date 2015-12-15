package rh.metier.interfaces;

import org.springframework.data.domain.Page;

import rh.entities.Collaborateur;

public interface ICollaborateurMetier {
	
	public Collaborateur addCollaborateur(Collaborateur c);
	public Page<Collaborateur> getallcollaborateurs(int page);
	public Collaborateur getCollaborateur(Long refCollaborateur);
	public void deleteCollaborateur(Long refCollaborateur);
	public boolean addProjetToCollaborateur(Long idProjet, Long IdCollaborateur);

}
