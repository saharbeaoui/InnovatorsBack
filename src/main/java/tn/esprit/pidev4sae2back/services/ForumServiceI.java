package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Forum;
import tn.esprit.pidev4sae2back.entities.ForumStatisticsDTO;
import tn.esprit.pidev4sae2back.entities.Thread;

import java.io.IOException;
import java.util.List;

public interface ForumServiceI {
    public List<Forum> searchForums(String query);
    List<Forum> retrieveAllForums();
    Forum addForum(Forum f) throws IOException;
    Forum updateForum (Forum f) throws IOException;
    Forum retrieveForum (Long idForum);
    void deleteForum(Long idForum);
    public ForumStatisticsDTO getForumStatistics();
    public Thread addThreadToForum(Long forumId,  String description);
}
