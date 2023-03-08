package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.CommentType;
import tn.esprit.pidev4sae2back.entities.Forum;
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

public class ThreadServiceImp extends BaseService<Thread, Long> implements ThreadServiceI{
    ThreadRepository threadRepository;
    BadWordFilter badWordFilter;
    ForumRepository forumRepository;

    @Override
    public List<Thread> retrieveAllThreads() {
        return threadRepository.findAll();
    }

    @Transactional
    public Thread addThread(Thread t, Long forumId)throws IOException, BadWordException {
        if (badWordFilter.checkBadWord(t.getDescription()))
            throw new BadWordException("Bad word detected");
        Forum forum = forumRepository.findById(forumId).get();
        t.setType(CommentType.COMMENT);
        t.setForum(forum);
        return threadRepository.save(t);
    }

    @Override
    public Thread updateThread(Thread t)throws IOException, BadWordException {
        if(badWordFilter.checkBadWord(t.getDescription()))
            throw new BadWordException("Bad word detected");
        return threadRepository.save(t);
    }

    @Override
    public Thread retrieveThread(Long idThread) {
        return threadRepository.findById((long)idThread).orElse(null);
    }

    @Override
    public void deleteThread(Long idThread) {
        threadRepository.deleteById(idThread);
    }

    public List<Thread> getAllThreadsByForumId(Long forumId) {
        return threadRepository.findAllByTypeAndForumId(CommentType.COMMENT, forumId);
    }


    public List<Thread> getAllThreadsByForumIdAndOwner(Long forumId, Long userId) {
        return threadRepository.findAllByForumIdAndOwnerAndType(forumId, userId, CommentType.COMMENT);
    }
    public Thread replyThread(Thread thread, Long threadId) throws IOException, BadWordException{
        if(badWordFilter.checkBadWord(thread.getDescription()))
            throw new BadWordException("Bad word detected");
        thread.setType(CommentType.REPLY);
        Thread parent=threadRepository.findById(threadId).get();
        thread.setParent(parent);
        return threadRepository.save(thread);
    }
    public List<Thread> getAllRepliesByThreadId(Long threadId){
        return threadRepository.findAllByTypeAndParentId(CommentType.REPLY, threadId);
    }

}
