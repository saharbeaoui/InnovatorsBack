package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.services.RMembershipServiceI;

import java.util.List;

@RestController
@RequestMapping("/RMembership")
public class RMembershipController {

    @Autowired
    RMembershipServiceI rmI;


    @PostMapping("/addRMembership")
    public RMembership addRMembership(@RequestBody RMembership rMembership) {
        return rmI.addRMembership(rMembership);
    }

    @GetMapping("/retrieveRMembership/{idRMembership}")
    @ResponseBody
    public RMembership retrieveRMembership(@PathVariable Long idRMembership){
        return rmI.retrieveRMembership(idRMembership);
    }

    @GetMapping("/retrieveAllRMemberships")
    public List<RMembership> getAllRMemberships(){
        return rmI.retrieveAllRMemberships();
    }

    @PutMapping("/updateRMembership")
    public RMembership updateRMembership(@RequestBody RMembership rMembership){
       return rmI.updateRMembership(rMembership);
    }


    @DeleteMapping("/removeRMembership/{idRMembership}")
    public void removeEquipe(@PathVariable Long idRMembership) {
        rmI.removeRMembership(idRMembership);
    }

    @PostMapping("/addRMembershipAndAssToUserAndRestau/{userId}")
    public RMembership addRMembershipAndAssToUserAndRestau(@RequestBody RMembership rMembership,@PathVariable Long userId) {
        return rmI.addRMembershipAndAssToUserAndRestau(rMembership,userId);
    }


}
