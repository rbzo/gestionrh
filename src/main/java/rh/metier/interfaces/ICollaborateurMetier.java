package rh.metier.interfaces;

import java.util.Set;

import org.springframework.data.domain.Page;

import rh.entities.Collaborateur;
import rh.entities.Projet;
import rh.entities.ProjetCollaborateur;

public interface ICollaborateurMetier {
	
	public Collaborateur addCollaborateur(Collaborateur c);
	public Page<Collaborateur> getallcollaborateurs(int page);
	public Collaborateur getCollaborateur(Long refCollaborateur);
	public void deleteCollaborateur(Long refCollaborateur);
	public boolean addProjetToCollaborateur(Long idProjet, Long IdCollaborateur, String rolejoue, int joursvalorises);
	public Set<ProjetCollaborateur> getProjetsByCollaborateur(Long idCollaborateur);

}
