package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.RUserTransaction;

import java.util.List;

@Repository
public interface RUserTransactionRepository extends JpaRepository<RUserTransaction, Long> {

    public List<RUserTransaction> findAllByUserId(Long userId);
}