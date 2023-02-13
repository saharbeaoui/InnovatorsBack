package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.RMembership;

@Repository
public interface RMembershipRepository extends JpaRepository<RMembership, Long> {
}