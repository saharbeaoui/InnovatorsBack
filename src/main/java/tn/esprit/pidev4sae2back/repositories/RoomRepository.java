package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.BlockFoyer;
import tn.esprit.pidev4sae2back.entities.Room;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}