package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.repositories.RMembershipRepository;

@Service
public class RMembershipServiceImp implements RMembershipServiceI{
    @Autowired
    RMembershipRepository rmr;

    @Override
    public RMembership addRMembership(RMembership rMembership){
        return rmr.save(rMembership);
    }
}
