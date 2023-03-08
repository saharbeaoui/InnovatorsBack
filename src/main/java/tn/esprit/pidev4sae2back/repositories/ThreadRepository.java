package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.CommentType;
import tn.esprit.pidev4sae2back.entities.Thread;

import java.util.List;

@Repository
public interface ThreadRepository extends BaseRepository<Thread, Long> {
    long count();
    List<Thread> findAllByForumIdAndOwnerAndType(Long forumId, Long ownerId, CommentType commentType);

    List<Thread> findAllByTypeAndParentId(CommentType commentType,Long parentId);
    List <Thread> findAllByTypeAndForumId(CommentType commentType, Long forumId);
}
