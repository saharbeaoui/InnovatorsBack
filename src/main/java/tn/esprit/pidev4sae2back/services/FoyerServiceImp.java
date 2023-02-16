package tn.esprit.pidev4sae2back.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tn.esprit.pidev4sae2back.entities.Foyer;
import tn.esprit.pidev4sae2back.repositories.FoyerRepository;

import java.util.List;

@Service
public class FoyerServiceImp implements FoyerServiceI{
    @Autowired
    FoyerRepository fr;

    @Override
    public Foyer addFoyer(Foyer foyer){
        return fr.save(foyer);
    }



    @Override
    public Foyer getFoyer(Long idFoyer) {
        return fr.findById(idFoyer).orElse(null);
    }
    @Override
    public List<Foyer> getAllFoyers() {
        return fr.findAll();
    }
    @Override
    public Foyer updateFoyer(Foyer foyer) {
        return fr.save(foyer);
    }

    @Override
    public void removeFoyer(Long idFoyer) {
        fr.deleteById(idFoyer);
    }
}
