package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.FMembership;

import java.util.List;

public interface FMembershipServiceI {
    List<FMembership> retrieveAllFMembership();



    FMembership addFMembership(FMembership fm, Long idUser, Long idFoyer);


    FMembership updateFMembership(FMembership fm);

    FMembership retrieveFMembership(Long idFMembership);

    void deleteFMembership(Long idFMembership);

    void freeBedAndRoomAfterMembershipEnds(Long idFMembership);

    FMembership F_MEMBERSHIPStatistics(Long idFMembership);

}
