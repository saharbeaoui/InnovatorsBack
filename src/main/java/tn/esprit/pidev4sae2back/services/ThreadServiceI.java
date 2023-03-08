package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Thread;
import tn.esprit.pidev4sae2back.exception.BadWordException;

import java.io.IOException;
import java.util.List;

public interface ThreadServiceI {
    List<Thread> retrieveAllThreads();
    public Thread addThread(Thread t, Long forumId)throws IOException, BadWordException;
    Thread updateThread (Thread t) throws IOException;
    Thread retrieveThread (Long idThread);
    void deleteThread(Long idThread);
    public List<Thread> getAllThreadsByForumId(Long forumId);
    public List<Thread> getAllThreadsByForumIdAndOwner(Long forumId, Long userId) ;
    public Thread replyThread(Thread thread, Long threadId) throws IOException, BadWordException;
    public List<Thread> getAllRepliesByThreadId(Long threadId);

    }
