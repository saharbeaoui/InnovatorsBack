package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.FidelityCard;
import tn.esprit.pidev4sae2back.entities.FidelityTransaction;
import tn.esprit.pidev4sae2back.entities.TransactionType;
import tn.esprit.pidev4sae2back.repositories.FidelityCardRepository;
import tn.esprit.pidev4sae2back.repositories.FidelityTransactionRepository;

import java.time.LocalDateTime;

@Service
public class FidelityTransactionServiceImp implements FidelityTransactionServiceI{

    @Autowired
    FidelityTransactionRepository ftr;

    @Autowired
    FidelityCardRepository fcr;

    @Override
    public FidelityTransaction addTransaction(FidelityTransaction transaction) {
        FidelityCard card = fcr.findById(transaction.getFidelityCard().getIdFidelityCard()).orElse(null);
        if (card != null) {
            if (transaction.getTransactionType() == TransactionType.EARN_POINTS) {
                card.setTotalPoints(card.getTotalPoints() + transaction.getPoints());
            } else if (transaction.getTransactionType() == TransactionType.SPEND_POINTS) {
                int newPoints = card.getTotalPoints() - transaction.getPoints();
                if (newPoints < 0) {
                    throw new IllegalArgumentException("Not enough points");
                } else {
                    card.setTotalPoints(newPoints);
                }
            }
            fcr.save(card);
            transaction.setTransactionDate(LocalDateTime.now());
            return ftr.save(transaction);
        } else {
            throw new IllegalArgumentException("Invalid fidelity card");
        }
    }

}
