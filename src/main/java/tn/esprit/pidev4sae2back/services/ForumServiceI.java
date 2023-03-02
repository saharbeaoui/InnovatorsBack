package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Forum;
import tn.esprit.pidev4sae2back.entities.ForumStatisticsDTO;
import tn.esprit.pidev4sae2back.entities.Thread;

import java.util.List;

public interface ForumServiceI {
    public List<Forum> searchForums(String query);
    List<Forum> retrieveAllForums();
    Forum addForum(Forum f);
    Forum updateForum (Forum f);
    Forum retrieveForum (Long idForum);
    void deleteForum(Long idForum);
    public ForumStatisticsDTO getForumStatistics();
    public Thread addThreadToForum(Long forumId, String title, String description);
}
