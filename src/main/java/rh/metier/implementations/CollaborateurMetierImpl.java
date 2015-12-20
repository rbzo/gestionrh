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
    /**
     * ajouter un collaborateur
     * 
     * @param Collaborateur 
     * @return Collaborateur
     */
	@Override
	public Collaborateur addCollaborateur(Collaborateur c) {
		collaborateurRepository.save(c);
		return c;
	}
    /**
     * recuperer la liste des collaborateurs sous forme de page(pour faciliter la pagination avec spring data jpa)
     * @param page
     */
	@Override
	public Page<Collaborateur> getallcollaborateurs(int page) {
		return collaborateurRepository.findAll(new PageRequest(page, 10));
	}
	
	/**
	 * recuperer un collaborateur
	 * 
	 * @param refCollaborateur 
	 * @return Collaborateur
	 */

	@Override
	public Collaborateur getCollaborateur(Long refCollaborateur) {
		return collaborateurRepository.findOne(refCollaborateur);
	}
	
	/**
	 * supprimer un collaborateur(en entrée le matricule du collaborateur)
	 * 
	 * @param refCollaborateur 
	 * @return void
	 */

	@Override
	public void deleteCollaborateur(Long refCollaborateur) {
		collaborateurRepository.delete(refCollaborateur);
	}
	/**
	 * attribuer un projet à un collaborateur
	 * 
	 * @param  idProjet identifiant du projet à ajouter
	 * @param IdCollaborateur matricule du collaborateur
	 * @param rolejoue Role joué par le collaborateur
	 * @param joursvalorises
	 */

	@Override
	public ProjetCollaborateur addProjetToCollaborateur(Long idProjet, Long IdCollaborateur, String rolejoue, int joursvalorises) {
		Projet p = projetRepository.findOne(idProjet);
		Collaborateur c =collaborateurRepository.findOne(IdCollaborateur);
		ProjetCollaborateur pc = new ProjetCollaborateur(rolejoue, joursvalorises, c, p);
		projetCollaborateurRepository.save(pc);
		return pc;
		 
		
	}
	
	/**
	 * lister tous les projets d'un collaborateur
	 * 
	 * @param idCollaborateur
	 * @return {@link Set} ProjetCollaborateur
	 */

	@Override
	public Set<ProjetCollaborateur> getProjetsByCollaborateur(Long idCollaborateur) {
		
		return collaborateurRepository.getProjetsByCollaborateur(idCollaborateur);
	}

	/**
	 * recuperer le dernier bilan d'un collaborateur
	 * on trie par ordre decroissant puis on recupere le premier element
	 * 
	 * @param matriculeCollaborateur
	 */
	@Override
	public Page<BilanPerformance> getLastBilanBycollaborateur(
			Long matriculeCollaborateur) {
		
		return collaborateurRepository.getLastbilanBycollaborateur(matriculeCollaborateur, new PageRequest(0, 1, Sort.Direction.DESC, "dateFin"));
	}
	
	/**
	 * attribuer  un nouveau poste à un collaborateur
	 * 
	 * @param poste
	 * @param matriculeCollaborateur
	 * @return {@link String} poste
	 */

	@Override
	public String nouveauPoste(String poste, Long matriculeCollaborateur) {
		Collaborateur c = collaborateurRepository.findOne(matriculeCollaborateur);
		c.setPoste(poste);
		collaborateurRepository.save(c);
		return poste;
	}



	

}
