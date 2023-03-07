package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.FNameBlock;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.entities.WaitingList;
import tn.esprit.pidev4sae2back.services.UserServiceI;
import tn.esprit.pidev4sae2back.services.WaitingListServiceI;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping("/waiting-list")
public class WaitingListController {
    @Autowired
     WaitingListServiceI waitingListService;
    UserServiceI userServiceI;

    @PostMapping("/addToWaitingList/{userId}")
    public WaitingList addToWaitingList(@RequestBody WaitingList waitingList,@PathVariable Long userId) {
        return  waitingListService.addToWaitingList(waitingList,userId);
    }

    @DeleteMapping("/removeFromWaitingList/{waitingListId}")
    public void removeFromWaitingList(@PathVariable Long waitingListId) {
        waitingListService.removeFromWaitingList(waitingListId);
    }

    @GetMapping("/getFirstOfWaitingList")
    public WaitingList getFirstOfWaitingList() {
        return  waitingListService.getFirstOfWaitingList();
    }

    @PostMapping("/notifyFirstUserOnWaitingList")
    public void notifyFirstUserOnWaitingList() throws MessagingException {
        waitingListService.notifyFirstUserOnWaitingList();
    }
    @GetMapping("/countWaitingList")
    int countWaitingList(){
       return waitingListService.countWaitingList();
    }

    @GetMapping("/getWaitingListByFoyerAndBlock/{idFoyer}/{idBlock}")
    public List<WaitingList> getWaitingListByFoyerAndBlock(@PathVariable Long idFoyer,@PathVariable Long idBlock) {
        return waitingListService.getWaitingListByFoyerAndBlock(idFoyer,idBlock);
    }

    @GetMapping("/getAllWaitingList")
    public List<WaitingList> getAllWaitingList() {
        return waitingListService.getAllWaitingList();
    }

    @GetMapping("/estimateWaitTimes")
    public void estimateWaitTimes() {
        waitingListService.estimateWaitTimes();
    }
}
