package tn.esprit.pidev4sae2back.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.ERole;
import tn.esprit.pidev4sae2back.entities.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	  Optional<Role> findByName(ERole name);

}
