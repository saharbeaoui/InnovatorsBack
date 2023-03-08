package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.pidev4sae2back.entities.Reaction;
import tn.esprit.pidev4sae2back.entities.ReactionType;


import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends BaseRepository<Reaction, Long> {
    List<Reaction> findAllByForumId(Long forumId);

    Optional<Reaction> findByForumIdAndOwner(Long forumId, Long ownerId);
    List<Reaction> findAllByThreadId(Long threadId);
    List<Reaction> findAllByThreadIdAndOwner(Long threadId, Long ownerId);
   //get the nombre of reaction by type
    Integer countAllByReactionTypeAndForumId(ReactionType reactionType, Long forumId);
    Integer countAllByReactionTypeAndThreadId(ReactionType reactionType, Long threadId);



}
