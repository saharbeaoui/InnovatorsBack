package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Thread;
import tn.esprit.pidev4sae2back.repositories.ThreadRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor

public class ThreadServiceImp implements ThreadServiceI{
    ThreadRepository threadRepository;
    @Override
    public List<Thread> retrieveAllThreads() {
        return threadRepository.findAll();
    }

    @Transactional
    public Thread addThread(Thread t) {
        return threadRepository.save(t);
    }

    @Override
    public Thread updateThread(Thread t) {
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
}
