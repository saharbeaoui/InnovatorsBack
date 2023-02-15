package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Thread;
import tn.esprit.pidev4sae2back.services.ThreadServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/thread")

public class ThreadController {
    ThreadServiceImp threadServiceImp;
    //http://localhost:8082/test/thread/retrieveAllThreads
    @GetMapping("/retrieveAllThreads")
    public List<Thread> retrieveAllThread(){
        return threadServiceImp.retrieveAllThreads();
    }
    //http://localhost:8082/test/thread/addThread
    @PostMapping("/addThread")
    public Thread addThread(@RequestBody Thread t) {
        return threadServiceImp.addThread(t);
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
    @DeleteMapping("/deleteThread/{Thread}")
    public void deleteThread(@PathVariable("idThread")Long idThread){
        threadServiceImp.deleteThread(idThread);
    }


}
