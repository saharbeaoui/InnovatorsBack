package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.FidelityCard;
import tn.esprit.pidev4sae2back.entities.MembershipLevel;
import tn.esprit.pidev4sae2back.repositories.FidelityCardRepository;

import java.util.List;

@Service
public class FidelityCardServiceImp implements FidelityCardServiceI {



    @Autowired
    FidelityCardRepository fcr;


    @Override
    public FidelityCard addFidelityCard(FidelityCard fidelityCard) {
        fidelityCard.setTotalPoints(0);
        return fcr.save(fidelityCard);
    }

    @Override
    public FidelityCard retrieveFidelityCard(Long idFidelityCard) {
        return fcr.findById(idFidelityCard).orElse(null);
    }

    @Override
    public List<FidelityCard> retrieveAllFidelityCard() {
        return fcr.findAll();
    }

    @Override
    public FidelityCard updateFidelityCard(FidelityCard fidelityCard) {
        return fcr.save(fidelityCard);
    }

    @Override
    public void removeFidelityCard(Long idFidelityCard) {
        fcr.deleteById(idFidelityCard);
    }


    public MembershipLevel getMembershipLevel(FidelityCard fd) {
        if (fd.getTotalPoints() >= 1000) {
            return MembershipLevel.GOLD;
        } else if (fd.getTotalPoints() >= 500) {
            return MembershipLevel.SILVER;
        } else if (fd.getTotalPoints() >= 300) {
            return MembershipLevel.BRONZE;
        } else {
            return MembershipLevel.NONE;
        }
    }


}
