package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import tn.esprit.pidev4sae2back.entities.BaseEntity;


import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> {
    List<T> findAllByOwner(ID id);

}
