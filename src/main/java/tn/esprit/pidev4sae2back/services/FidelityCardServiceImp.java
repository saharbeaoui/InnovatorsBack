package tn.esprit.pidev4sae2back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.FidelityCardRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;

import java.util.List;

@Slf4j
@Service
public class FidelityCardServiceImp implements FidelityCardServiceI {


    @Autowired
    UserRepository ur;

    @Autowired
    FidelityCardRepository fcr;


    @Override
    public FidelityCard addFidelityCard(FidelityCard fidelityCard) {
        fidelityCard.setTotalPoints(0);
        fidelityCard.setMembershipLevel(MembershipLevel.NONE);
        return fcr.save(fidelityCard);
    }

    @Override
    public FidelityCard addFidelityCardAndAssToUser(FidelityCard fidelityCard, Long userId){
        fidelityCard.setTotalPoints(0);
        fidelityCard.setMembershipLevel(MembershipLevel.NONE);
        User u = ur.findById(userId).orElse(null);

        if (u.getFidelityCard()==null) {
            fidelityCard.setUser(u);
            return fcr.save(fidelityCard);
        } else
            log.info("User have already a Fidelity Card !!");
        return null;

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
    public FidelityCard updateFidelityCard(FidelityCard fidelityCard,Long userId) {
           User u = ur.findById(userId).orElse(null);
           fidelityCard.setUser(u);
        return fcr.save(fidelityCard);
    }

    @Override
    public void removeFidelityCard(Long idFidelityCard) {
        fcr.deleteById(idFidelityCard);
    }

    @Override
    public MembershipLevel getMembershipLevel(Long fidelityCardId) {
        FidelityCard f = fcr.findById(fidelityCardId).orElse(null);
        if (f.getTotalPoints() >= 1000) {
            return MembershipLevel.GOLD;
        } else if (f.getTotalPoints() >= 500) {
            return MembershipLevel.SILVER;
        } else if (f.getTotalPoints() >= 300) {
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
    public int getTotalPointsByUser(Long userId) {
        User u = ur.findById(userId).orElse(null);
        return u.getFidelityCard().getTotalPoints();
    }

    @Override
    public FidelityCard updateMemberShipLevelFidelityCard(Long fidelityCardId){
        FidelityCard f = fcr.findById(fidelityCardId).orElse(null);
        f.setMembershipLevel(getMembershipLevel(fidelityCardId));
        return fcr.save(f);
    }

    @Override
    public int showTotalPointsByUser(Long userId){
        User u = ur.findById(userId).orElse(null);
        return u.getFidelityCard().getTotalPoints();
    }


    @Override
    public String showUserFullNameOfAFidelityCard(Long fidelityCardId){
        FidelityCard f = fcr.findById(fidelityCardId).orElse(null);
        return f.getUser().getLastName()+" "+f.getUser().getFirstName();
    }

}
