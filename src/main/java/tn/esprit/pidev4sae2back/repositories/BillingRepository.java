package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.Billing;
import tn.esprit.pidev4sae2back.entities.Duration;
import tn.esprit.pidev4sae2back.entities.TypeMembership;
import tn.esprit.pidev4sae2back.entities.TypeUser;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {

    @Query(" select b.totalCost from Billing b " +
            "where b.typeMembership =?1 " +
            " and b.duration =?2 " +
            "and b.typeUser = ?3 ")
    public int getTotalCostWhere(TypeMembership typeMembership, Duration duration, TypeUser typeUser);
}