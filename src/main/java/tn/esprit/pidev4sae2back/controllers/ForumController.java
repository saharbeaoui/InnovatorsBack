package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Forum;
import tn.esprit.pidev4sae2back.services.ForumServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/forum")

public class ForumController {
    ForumServiceImp forumServiceImp;

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
        return forumServiceImp.addForum(f);
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
