package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.Duration;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.entities.Foyer;

import java.util.List;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
    List<Foyer> findByCapacityGreaterThan(int i);

    List<Foyer> findByBlockFoyers_NameBlock(String blockFoyerName);

    List<Foyer> findByBlockFoyers_NameBlockAndCapacityGreaterThan(String blockFoyerName, int i);

    List<Foyer> findAllByCapacityGreaterThan(int i);

    List<Foyer> findByCapacityNotNull();

    @Query("SELECT f FROM Foyer f WHERE f.nameFoyer LIKE %:name%")
    List<Foyer> findByNameContaining(@Param("name") String name);
    @Query("SELECT f FROM Foyer f WHERE f.capacity = :capacity")
    List<Foyer> findByCapacity(@Param("capacity") Integer capacity);

    @Query("SELECT f FROM Foyer f WHERE f.nameFoyer LIKE %:name% AND f.capacity = :capacity")
    List<Foyer> findByNameContainingAndCapacity(@Param("name") String name, @Param("capacity") Integer capacity);
    @Query("SELECT fm FROM FMembership fm WHERE fm.duration = :duration")
    List<FMembership> findByDuration(@Param("duration") Duration duration);
}