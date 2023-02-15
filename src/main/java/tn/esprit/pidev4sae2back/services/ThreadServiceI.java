package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Thread;

import java.util.List;

public interface ThreadServiceI {
    List<Thread> retrieveAllThreads();
    Thread addThread(Thread t);
    Thread updateThread (Thread t);
    Thread retrieveThread (Long idThread);
    void deleteThread(Long idThread);
}
