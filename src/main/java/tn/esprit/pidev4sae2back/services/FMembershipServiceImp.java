package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.repositories.FMembershipRepository;

import java.util.List;

@Service
public class FMembershipServiceImp implements FMembershipServiceI{
    @Autowired
    FMembershipRepository fMembershipRepository;
    @Override
    public List<FMembership> retrieveAllFMembership() {
        return fMembershipRepository.findAll();
    }

    @Override
    public FMembership addFMembership(FMembership fm) {
        return fMembershipRepository.save(fm);
    }

    @Override
    public FMembership updateFMembership(FMembership fm) {
        return fMembershipRepository.save(fm);
    }

    @Override
    public FMembership retrieveFMembership(Long idFMembership) {
        return fMembershipRepository.findById((Long) idFMembership).orElse(null);
    }

    @Override
    public void deleteFMembership(Long idFMembership) {
        fMembershipRepository.deleteById( idFMembership);
    }
}
