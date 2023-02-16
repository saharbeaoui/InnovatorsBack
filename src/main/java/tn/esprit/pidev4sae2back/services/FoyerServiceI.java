package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.BlockFoyer;
import tn.esprit.pidev4sae2back.entities.Foyer;

import java.util.List;

public interface FoyerServiceI {

    List<Foyer> retrieveAllFoyers();
    Foyer addFoyer(Foyer f);
    Foyer updateFoyer (Foyer f);
    Foyer retrieveFoyer (Long idFoyer);
    void deleteFoyer(Long idFoyer);
}
