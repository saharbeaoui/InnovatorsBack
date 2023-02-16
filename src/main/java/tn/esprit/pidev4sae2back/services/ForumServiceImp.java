package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Forum;
import tn.esprit.pidev4sae2back.repositories.ForumRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ForumServiceImp implements ForumServiceI{
    ForumRepository forumRepository;
    @Override
    public List<Forum> retrieveAllForums() {
        return forumRepository.findAll() ;
    }

    @Transactional
    public Forum addForum(Forum f) {
        return forumRepository.save(f);
    }

    @Override
    public Forum updateForum(Forum f) {
        return forumRepository.save(f);
    }

    @Override
    public Forum retrieveForum(Long idForum) {
        return forumRepository.findById((long)idForum).orElse(null);
    }

    @Override
    public void deleteForum(Long idForum) {
        forumRepository.deleteById(idForum);
    }

}
