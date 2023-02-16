package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Claim;
import tn.esprit.pidev4sae2back.repositories.ClaimRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ClaimServiceImp implements ClaimServiceI{
    ClaimRepository claimRepository;
    @Override
    public List<Claim> retrieveAllClaims() {
        return claimRepository.findAll();
    }

    @Transactional
    public Claim addClaim(Claim c) {
        return claimRepository.save(c);
    }

    @Override
    public Claim updateClaim(Claim c) {
        return claimRepository.save(c);
    }

    @Override
    public Claim retrieveClaim(Long idClaim) {
        return claimRepository.findById((long) idClaim).orElse(null);
    }

    @Override
    public void deleteClaim(Long idClaim) {
        claimRepository.deleteById(idClaim);
    }
}
