package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.Foyer;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
    List<Foyer> findByCapacityGreaterThan(int i);

    List<Foyer> findByBlockFoyers_NameBlock(String blockFoyerName);

    List<Foyer> findByBlockFoyers_NameBlockAndCapacityGreaterThan(String blockFoyerName, int i);

    List<Foyer> findAllByCapacityGreaterThan(int i);

    List<Foyer> findByCapacityNotNull();
}