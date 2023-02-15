package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.entities.Restaurant;
import tn.esprit.pidev4sae2back.entities.TypeUser;

import java.util.List;

@Repository
public interface RMembershipRepository extends JpaRepository<RMembership, Long> {

    List<RMembership> findAllByRestaurantAndUser_TypeUser(Restaurant restau, TypeUser typeUser);
}