package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.FidelityCardRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;

import java.util.List;

@Service
public class FidelityCardServiceImp implements FidelityCardServiceI {


    @Autowired
    UserRepository ur;

    @Autowired
    FidelityCardRepository fcr;


    @Override
    public FidelityCard addFidelityCard(FidelityCard fidelityCard) {
        fidelityCard.setTotalPoints(0);
        return fcr.save(fidelityCard);
    }

    @Override
    public FidelityCard addFidelityCardAndAssToUser(FidelityCard fidelityCard, Long userId){
        User u = ur.findById(userId).orElse(null);
        u.setFidelityCard(fidelityCard);
        ur.save(u);
        fidelityCard.setUser(u);
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

    @Override
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

    @Override
    public MembershipLevel getMembershipLevelByUser(Long userId) {
        User u = ur.findById(userId).orElse(null);
        if (u.getFidelityCard().getTotalPoints() >= 1000) {
            return MembershipLevel.GOLD;
        } else if (u.getFidelityCard().getTotalPoints() >= 500) {
            return MembershipLevel.SILVER;
        } else if (u.getFidelityCard().getTotalPoints() >= 300) {
            return MembershipLevel.BRONZE;
        } else {
            return MembershipLevel.NONE;
        }
    }

    @Override
    public FidelityCard updateMemberShipLevelFidelityCard(FidelityCard fidelityCard){
        fidelityCard.setMembershipLevel(getMembershipLevel(fidelityCard));
        return fcr.save(fidelityCard);
    }

    @Override
    public int showTotalPointsByUser(Long userId){
        User u = ur.findById(userId).orElse(null);
        return u.getFidelityCard().getTotalPoints();
    }



}
