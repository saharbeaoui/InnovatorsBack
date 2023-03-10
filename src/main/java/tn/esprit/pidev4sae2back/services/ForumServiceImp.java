package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Forum;
import tn.esprit.pidev4sae2back.entities.ForumStatisticsDTO;
import tn.esprit.pidev4sae2back.entities.Thread;
import tn.esprit.pidev4sae2back.exception.BadWordException;
import tn.esprit.pidev4sae2back.helpers.BadWordFilter;
import tn.esprit.pidev4sae2back.repositories.ForumRepository;
import tn.esprit.pidev4sae2back.repositories.ThreadRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ForumServiceImp extends BaseService<Forum, Long> implements ForumServiceI {
    ForumRepository forumRepository;
    ThreadRepository threadRepository;
    BadWordFilter badWordFilter;

    public List<Forum> searchForums(String query) {
        return forumRepository.findByTitleContainingIgnoreCaseOrTopicContainingIgnoreCaseOrderByTitleAsc(query, query);
    }
    @Override
    public List<Forum> retrieveAllForums() {
        return forumRepository.findAll() ;
    }

    @Transactional
    public Forum addForum(Forum f)throws IOException {
        if (badWordFilter.checkBadWord(f.getTopic()))
            throw new BadWordException("Bad word detected");

        return forumRepository.save(f);
    }

    @Override
    public Forum updateForum(Forum f)throws IOException {
        if (badWordFilter.checkBadWord(f.getTopic()))
            throw new BadWordException("Bad word detected");

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

    @Override
    public ForumStatisticsDTO getForumStatistics() {
        Long totalThreads = threadRepository.count();
        Long totalForums = forumRepository.count();
        double averageThreadsPerForum = totalForums == 0 ? 0 : (double) totalThreads / totalForums;

        ForumStatisticsDTO statisticsDTO = new ForumStatisticsDTO();
        statisticsDTO.setTotalThreads(totalThreads);
        statisticsDTO.setTotalForums(totalForums);
        statisticsDTO.setAverageThreadsPerForum(averageThreadsPerForum);

        return statisticsDTO;
    }

    @Override
    public Thread addThreadToForum(Long forumId,  String description) {
        Forum forum = forumRepository.findById(forumId).orElseThrow(() -> new RuntimeException("Forum not found"));
        Thread thread = new Thread();

        thread.setDescription(description);
        thread.setForum(forum);
        return threadRepository.save(thread);
    }
}


