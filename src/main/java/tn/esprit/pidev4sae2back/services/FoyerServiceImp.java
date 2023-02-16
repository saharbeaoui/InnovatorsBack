package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Foyer;
import tn.esprit.pidev4sae2back.repositories.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class FoyerServiceImp implements FoyerServiceI{
@Autowired
    FoyerRepository foyerRepository;
    @Override
    public List<Foyer> retrieveAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {return foyerRepository.save(f);

    }

    @Override
    public Foyer updateFoyer(Foyer f) {return foyerRepository.save(f);

    }

    @Override
    public Foyer retrieveFoyer(Long idFoyer) {
        return foyerRepository.findById((Long)idFoyer).orElse(null);
    }

    @Override
    public void deleteFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
}
