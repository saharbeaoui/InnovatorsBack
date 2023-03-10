package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.FidelityTransaction;

import java.util.List;

public interface FidelityTransactionServiceI {
    FidelityTransaction addTransaction(FidelityTransaction transaction, Long FdId);

    List<FidelityTransaction> showTransactionByUser(Long userId);

    List<FidelityTransaction> showTransactionByFidelityCard(Long fidelityCardId);
}
