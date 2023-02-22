package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Forum;
import tn.esprit.pidev4sae2back.repositories.ForumRepository;
import tn.esprit.pidev4sae2back.services.ForumServiceImp;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/forum")

public class ForumController {
    ForumServiceImp forumServiceImp;
    ForumRepository forumRepository;


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

    @GetMapping("/search")
    public List<Forum> searchForums(@RequestParam("query") String query) {
        return forumServiceImp.searchForums(query);
    }


    //http://localhost:8082/test/forum/retrieveAllForums
    @GetMapping("/retrieveAllForums")
    public List<Forum> retrieveAllForum() {
        return forumServiceImp.retrieveAllForums();
    }

    //http://localhost:8082/test/forum/addForum
    @PostMapping("/addForum")
    public Forum addForum(@RequestBody Forum f) {

        String title = f.getTitle();
        String topic = f.getTopic();

        // Vérification de la présence de mots interdits dans le titre et le sujet
        if (containsForbiddenWords(title) || containsForbiddenWords(topic)) {
            // Lever une exception avec un message d'erreur personnalisé
            throw new IllegalArgumentException("Le titre ou le sujet contient un langage offensant.");
        }

        // Enregistrer le forum dans la base de données
        forumRepository.save(f);

        // Retourner le forum enregistré
        return f;
    }
    //http://localhost:8082/test/forum/updateForum
    @PutMapping("/updateForum")
    public Forum updateForum(@RequestBody Forum f) {
        return forumServiceImp.updateForum(f);
    }
    //http://localhost:8082/test/forum/retrieveForum/{idForum}
    @GetMapping("/retrieveForum/{idForum}")
    public Forum retrieveForum(@PathVariable(value = "idForum") Long idForum) {
        return forumServiceImp.retrieveForum(idForum);
    }
    //http://localhost:8082/test/forum/deleteForum/{idForum}
    @DeleteMapping("/deleteForum/{idForum}")
    public void deleteForum(@PathVariable("idForum") Long idForum) {
        forumServiceImp.deleteForum(idForum);

    }
}
