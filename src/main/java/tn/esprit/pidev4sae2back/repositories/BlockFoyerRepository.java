package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.BlockFoyer;

@Repository
public interface BlockFoyerRepository extends JpaRepository<BlockFoyer, Long> {
}