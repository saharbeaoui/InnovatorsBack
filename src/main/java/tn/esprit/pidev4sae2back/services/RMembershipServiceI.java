package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.RMembership;

import java.util.List;

public interface RMembershipServiceI {
    RMembership addRMembership(RMembership rMembership);


    List<RMembership> retrieveAllRMemberships();

    RMembership updateRMembership(RMembership rMembership);

    RMembership retrieveRMembership(Long idRMembership);
}
