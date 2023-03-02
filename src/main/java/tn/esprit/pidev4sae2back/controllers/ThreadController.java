package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Thread;
import tn.esprit.pidev4sae2back.repositories.ThreadRepository;
import tn.esprit.pidev4sae2back.services.ThreadServiceImp;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/thread")

public class ThreadController {


    ThreadServiceImp threadServiceImp;
    ThreadRepository threadRepository;


    private boolean containsForbiddenWords(String input) {
        // Vérifier si la chaîne d'entrée contient des mots interdits
        List<String> forbiddenWords = Arrays.asList("fuck", "fuck you", "fml");
        for (String word : forbiddenWords) {
            if (input.toLowerCase().contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    //http://localhost:8082/test/thread/retrieveAllThreads
    @GetMapping("/retrieveAllThreads")
    public List<Thread> retrieveAllThread(){
        return threadServiceImp.retrieveAllThreads();
    }
    //http://localhost:8082/test/thread/addThread
    @PostMapping("/addThread")
    public Thread addThread(@RequestBody Thread thread) {
        String title = thread.getTitle();
        String description = thread.getDescription();

        // Vérification de la présence de mots interdits dans le titre et la description
        if (containsForbiddenWords(title) || containsForbiddenWords(description)) {
            // Lever une exception avec un message d'erreur personnalisé
            throw new IllegalArgumentException("Le titre ou la description contient un langage offensant.");
        }

        // Enregistrer le thread dans la base de données
        threadRepository.save(thread);

        // Retourner le thread enregistré
        return thread;
    }

    //http://localhost:8082/test/thread/updateThread
    @PutMapping("/updateThread")
    public Thread updateThread(@RequestBody Thread t) {
        return threadServiceImp.updateThread(t);
    }
    //http://localhost:8082/test/thread/retrieveThread/{idThread}
    @GetMapping("/retrieveThread/{idThread}")
    public Thread retrieveThread(@PathVariable(value = "idThread") Long idThread) {
        return threadServiceImp.retrieveThread(idThread) ;
    }
    //http://localhost:8082/test/thread/deleteThread/{Thread}
    @DeleteMapping("/deleteThread/{idThread}")
    public void deleteThread(@PathVariable("idThread")Long idThread){
        threadServiceImp.deleteThread(idThread);
    }


}
