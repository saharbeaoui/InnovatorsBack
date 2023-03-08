package tn.esprit.pidev4sae2back.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Reaction;
import tn.esprit.pidev4sae2back.entities.ReactionType;
import tn.esprit.pidev4sae2back.services.ReactionService;
import tn.esprit.pidev4sae2back.entities.Reaction;
import tn.esprit.pidev4sae2back.entities.ReactionType;
import tn.esprit.pidev4sae2back.services.ReactionService;

@RestController
@AllArgsConstructor
@RequestMapping("/reaction")
@Tag(name = "Reaction", description = "the Reaction API")
@Slf4j
public class ReactionController extends BaseController<Reaction, Long> {

    private final ReactionService reactionService;

    @Operation(summary = "add to post ")
    @PostMapping("/forum/{forumId}")
    ResponseEntity<Reaction> addReaction(@RequestBody Reaction reaction, @PathVariable Long forumId) {
        return new ResponseEntity<>(reactionService.addReactionToForum(reaction, forumId), HttpStatus.CREATED);
    }
    @Operation(summary = "add to comment ")
    @PostMapping("/thread/{threadId}")
    ResponseEntity<Reaction> addReactionToThread(@RequestBody Reaction reaction, @PathVariable Long threadId) {
        return new ResponseEntity<>(reactionService.addReactionToThread(reaction, threadId), HttpStatus.CREATED);
    }

    @Operation(summary = "get all  by post")
    @GetMapping("/forum/{id}")
    ResponseEntity<?> getAllReactionsByPost(@PathVariable Long id) {
        if (reactionService.getAllReactionsByForumId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reactionService.getAllReactionsByForumId(id), HttpStatus.OK);
    }

    @Operation(summary = "get all  by post and owner")
    @GetMapping("/forum/{forumId}/owner/{userId}")
    ResponseEntity<?> getAllReactionsByForumAndOwner(@PathVariable Long forumId, @PathVariable Long userId) {
        if (reactionService.getAllReactionsByForumIdAndOwner(forumId, userId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reactionService.getAllReactionsByForumIdAndOwner(forumId, userId), HttpStatus.OK);
    }
    @Operation(summary = "get all  by comment")
    @GetMapping("/thread/{id}")
    ResponseEntity<?> getAllReactionsByComment(@PathVariable Long id) {
        if (reactionService.getAllReactionsByThreadId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reactionService.getAllReactionsByThreadId(id), HttpStatus.OK);
    }
    @Operation(summary = "get all  by comment and owner")
    @GetMapping("/thread/{threadId}/owner/{userId}")
    ResponseEntity<?> getAllReactionsByThreadAndOwner(@PathVariable Long threadId, @PathVariable Long userId) {
        if (reactionService.getAllReactionsByThreadIdAndOwner(threadId, userId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(reactionService.getAllReactionsByThreadIdAndOwner(threadId, userId), HttpStatus.OK);
    }
    @Operation(summary = "get nbr of reactions by post and type")
    @GetMapping("/forum/{forumId}/type/{type}")
    ResponseEntity<?> getNbrOfReactionsByForumAndType(@PathVariable("forumId") Long postId, @PathVariable("type") ReactionType type) {
        return new ResponseEntity<>(reactionService.countAllByReactionTypeAndForumId(postId,type), HttpStatus.OK);
    }
    @Operation(summary = "get nbr of reactions by comment and type")
    @GetMapping("/thread/{threadId}/type/{type}")
    ResponseEntity<?> getNbrOfReactionsByThreadAndType(@PathVariable Long threadId, @PathVariable ReactionType type) {
        return new ResponseEntity<>(reactionService.countAllByReactionTypeAndThreadId(threadId,type), HttpStatus.OK);
    }
    @Operation(summary = "get post reactions for every type")
    @GetMapping("/details/forum/{forumId}")
    ResponseEntity<?> getPostReactionsForEveryType(@PathVariable Long forumId) {
        return new ResponseEntity<>(reactionService.getReactionCountByForumId(forumId), HttpStatus.OK);
    }
    @Operation(summary = "get comment reactions for every type")
    @GetMapping("/details/thread/{threadId}")
    ResponseEntity<?> getThreadReactionsForEveryType(@PathVariable Long threadId) {
        return new ResponseEntity<>(reactionService.getReactionCountByThreadId(threadId), HttpStatus.OK);
    }
}

