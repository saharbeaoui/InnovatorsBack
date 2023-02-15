package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.repositories.RMembershipRepository;

import java.util.List;

@Service
public class RMembershipServiceImp implements RMembershipServiceI{
    @Autowired
    RMembershipRepository rmr;

    @Override
    public RMembership addRMembership(RMembership rMembership){
        return rmr.save(rMembership);
    }

    @Override
    public List<RMembership> retrieveAllRMemberships() {
        return rmr.findAll();
    }

    @Override
    public RMembership updateRMembership(RMembership rMembership) {
        return rmr.save(rMembership);
    }

    @Override
    public RMembership retrieveRMembership(Long idRMembership) {
        return rmr.findById(idRMembership).get();
    }

}
