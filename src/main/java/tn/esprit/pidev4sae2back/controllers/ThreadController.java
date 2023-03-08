package tn.esprit.pidev4sae2back.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Thread;
import tn.esprit.pidev4sae2back.repositories.ThreadRepository;
import tn.esprit.pidev4sae2back.services.ThreadServiceImp;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/thread")

public class ThreadController extends BaseController<Thread, Long>{


    ThreadServiceImp threadServiceImp;
    ThreadRepository threadRepository;



    //http://localhost:8082/test/thread/retrieveAllThreads
    @GetMapping("/retrieveAllThreads")
    public List<Thread> retrieveAllThread(){
        return threadServiceImp.retrieveAllThreads();
    }
    //http://localhost:8082/test/thread/addThread
    @PostMapping("/addThread/{forumId}")
    ResponseEntity<Thread>  addThread(@RequestBody Thread thread, @PathVariable("forumId") Long forumId) throws IOException {
        return new ResponseEntity<>(threadServiceImp.addThread(thread, forumId), HttpStatus.CREATED);
    }

    //http://localhost:8082/test/thread/updateThread
    @PutMapping("/updateThread/{threadId}")
    ResponseEntity<Thread> updateThread(@RequestBody Thread thread, @PathVariable("threadId")Long threadId)throws  IOException {
        return new ResponseEntity<>(threadServiceImp.updateThread(thread), HttpStatus.OK);
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

    @Operation(summary = "reply ")
    @PostMapping(value = "reply/{threadId}", consumes = "application/json")
    ResponseEntity<Thread> replyComment(@RequestBody Thread thread, @PathVariable("threadId") Long threadId) throws IOException {
        return new ResponseEntity<>(threadServiceImp.replyThread(thread, threadId), HttpStatus.CREATED);
    }
    @Operation(summary = "get all by post")
    @GetMapping("/forum/{id}")
    ResponseEntity<?> getAllThreadsByForum(@PathVariable Long id) {
        if (threadServiceImp.getAllThreadsByForumId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(threadServiceImp.getAllThreadsByForumId(id), HttpStatus.OK);
    }
    @Operation(summary = "get all by post and owner")
    @GetMapping("/forum/{forumId}/owner/{userId}")
    ResponseEntity<?> getAllThreadsByForumAndOwner(@PathVariable Long forumId, @PathVariable Long userId) {
        if (threadServiceImp.getAllThreadsByForumIdAndOwner(forumId, userId).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(threadServiceImp.getAllThreadsByForumIdAndOwner(forumId, userId), HttpStatus.OK);
    }
    @Operation(summary = "get all replies by comment")
    @GetMapping("/comment/{id}")
    ResponseEntity<?> getAllRepliesByThreads(@PathVariable Long id) {
        if (threadServiceImp.getAllRepliesByThreadId(id).isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(threadServiceImp.getAllRepliesByThreadId(id), HttpStatus.OK);
    }


}
