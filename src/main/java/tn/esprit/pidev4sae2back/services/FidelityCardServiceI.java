package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.FidelityCard;
import tn.esprit.pidev4sae2back.entities.MembershipLevel;

import java.util.List;

public interface FidelityCardServiceI {
    FidelityCard addFidelityCard(FidelityCard fidelityCard);

    FidelityCard addFidelityCardAndAssToUser(FidelityCard fidelityCard, Long userId);

    FidelityCard retrieveFidelityCard(Long idFidelityCard);

    List<FidelityCard> retrieveAllFidelityCard();

    FidelityCard updateFidelityCard(FidelityCard fidelityCard);

    void removeFidelityCard(Long idFidelityCard);

    FidelityCard updateMemberShipLevelFidelityCard(FidelityCard fidelityCard);

    MembershipLevel getMembershipLevel(FidelityCard fd);

    MembershipLevel getMembershipLevelByUser(Long userId);

    int showTotalPointsByUser(Long userId);
}
