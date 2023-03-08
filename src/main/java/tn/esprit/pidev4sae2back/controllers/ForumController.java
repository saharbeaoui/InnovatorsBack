package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Forum;
import tn.esprit.pidev4sae2back.entities.ForumStatisticsDTO;
import tn.esprit.pidev4sae2back.entities.Thread;
import tn.esprit.pidev4sae2back.repositories.ForumRepository;
import tn.esprit.pidev4sae2back.services.ForumServiceImp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/forum")

public class ForumController extends BaseController<Forum, Long>{
    ForumServiceImp forumServiceImp;
    ForumRepository forumRepository;


    private boolean containsForbiddenWords(String input) {
        // Vérifier si la chaîne d'entrée contient des mots interdits
        List<String> forbiddenWords = Arrays.asList("fuck", "fuck you", "fml","asba");
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
    ResponseEntity<Forum> addForum(@RequestBody Forum f)throws IOException {
        System.out.printf(f.toString());
        return new ResponseEntity<>(forumServiceImp.addForum(f), HttpStatus.CREATED);

    }
    //http://localhost:8082/test/forum/updateForum
    @PutMapping("/updateForum")
    ResponseEntity<Forum> updateForum(@RequestBody Forum f) throws IOException {
        return new ResponseEntity<>(forumServiceImp.updateForum(f), HttpStatus.OK);
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
    @GetMapping("/forum/statistics")
    public ForumStatisticsDTO getForumStatistics() {
        return forumServiceImp.getForumStatistics();
    }
    @PostMapping("/{forumId}/threads")
    public Thread addThreadToForum(@PathVariable Long forumId, @RequestBody Thread threadRequest) {
        return forumServiceImp.addThreadToForum(forumId,  threadRequest.getDescription());
    }

}
