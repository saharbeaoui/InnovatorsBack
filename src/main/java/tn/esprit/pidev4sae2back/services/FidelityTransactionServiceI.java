package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.FidelityTransaction;

import java.util.List;

public interface FidelityTransactionServiceI {
    FidelityTransaction addTransaction(FidelityTransaction transaction);

    List<FidelityTransaction> showTransactionByUser(Long userId);
}
