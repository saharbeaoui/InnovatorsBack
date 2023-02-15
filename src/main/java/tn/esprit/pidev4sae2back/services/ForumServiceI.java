package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Forum;

import java.util.List;

public interface ForumServiceI {
    List<Forum> retrieveAllForums();
    Forum addForum(Forum f);
    Forum updateForum (Forum f);
    Forum retrieveForum (Long idForum);
    void deleteForum(Long idForum);
}
