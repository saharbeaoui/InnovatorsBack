package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.Forum;

import java.util.List;

@Repository
public interface ForumRepository extends JpaRepository<Forum, Long> {
    public List<Forum> findByTitleContainingIgnoreCaseOrTopicContainingIgnoreCaseOrderByTitleAsc(String title, String topic);
    long count();
}
