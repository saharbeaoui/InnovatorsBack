package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev4sae2back.entities.FidelityTransaction;

public interface FidelityTransactionRepository extends JpaRepository<FidelityTransaction, Long> {
}