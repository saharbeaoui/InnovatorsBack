package tn.esprit.pidev4sae2back.services;
import java.util.List;

import tn.esprit.pidev4sae2back.entities.FNameBlock;
import tn.esprit.pidev4sae2back.entities.Foyer;
import tn.esprit.pidev4sae2back.entities.User;

public interface FoyerServiceI {
    Foyer addFoyer(Foyer foyer);

    Foyer getFoyer(Long idFoyer);

    List<Foyer> getAllFoyers();

    Foyer updateFoyer(Foyer foyer);

    void removeFoyer(Long idFoyer);

    Integer nbPlaceDisponiblePERBLOC(Long idFoyer, Long idBlock);


    Boolean isFullFoyer(Long idFoyer);

    void handleFMembershipRequest(User user);
    }