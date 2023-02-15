package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Claim;

import java.util.List;

public interface ClaimServiceI {
    List<Claim> retrieveAllClaims();
    Claim addClaim(Claim c);
    Claim updateClaim (Claim c);
    Claim retrieveClaim (Long idClaim);
    void deleteClaim(Long idClaim);
}
