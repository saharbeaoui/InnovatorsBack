package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.RMembership;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface RMembershipServiceI {

    RMembership addRMembership(RMembership rMembership);
    RMembership addRMembershipWithVerify(RMembership rMembership,long idStudent);
    List<RMembership> retrieveAllRMemberships();
    RMembership updateRMembership(RMembership rMembership);
    RMembership retrieveRMembership(Long idRMembership);
    void removeRMembership(Long idRMembership);
    List<RMembership> retrieveAllRMembershipsValidated();
    void validateRMembership(Long idRMembership) throws MessagingException;
    void unValidateRMembership(Long idRMembership);
    RMembership addRMembershipAndAssToUserAndRestau(RMembership rMembership, Long userId);
    List<RMembership> listeRMembershipsGUESTS(Long idRestaurant);
    List<RMembership> listeRMembershipsSTUDENTS(Long idRestaurant);
    List<RMembership> listeRMembershipsTEACHERS(Long idRestaurant);
    List<RMembership> RMembershipValidesBETWEEN(LocalDateTime startDate, LocalDateTime endDate);
    long nbRMembershipValidesBETWEEN(LocalDateTime startDate, LocalDateTime endDate);


}
