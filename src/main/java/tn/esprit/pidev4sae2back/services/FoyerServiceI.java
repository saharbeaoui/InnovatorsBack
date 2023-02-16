package tn.esprit.pidev4sae2back.services;
import java.util.List;
import tn.esprit.pidev4sae2back.entities.Foyer;
public interface FoyerServiceI {
    Foyer addFoyer(Foyer foyer);

    Foyer getFoyer(Long idFoyer);

    List<Foyer> getAllFoyers();

    Foyer updateFoyer(Foyer foyer);

    void removeFoyer(Long idFoyer);
}
