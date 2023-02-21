package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.FidelityCard;

import java.util.List;

public interface FidelityCardServiceI {
    FidelityCard addFidelityCard(FidelityCard fidelityCard);

    FidelityCard retrieveFidelityCard(Long idFidelityCard);

    List<FidelityCard> retrieveAllFidelityCard();

    FidelityCard updateFidelityCard(FidelityCard fidelityCard);

    void removeFidelityCard(Long idFidelityCard);
}
