package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.services.FMembershipServiceI;
import tn.esprit.pidev4sae2back.services.FMembershipServiceImp;
import tn.esprit.pidev4sae2back.services.RoomServiceI;
import tn.esprit.pidev4sae2back.services.RoomServiceImp;


import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/fMembership")
public class FMembershipController {
    @Autowired

    FMembershipServiceI fMembershipServiceI;
    RoomServiceI roomServiceI;

    //http://localhost:8090/test/fmembership/retrieveAllFMembership
    @GetMapping("/retrieveFMembership")
    public List<FMembership> retrieveAllFoyers(){
        return fMembershipServiceI.retrieveAllFMembership();
    }
    @PostMapping("/addFMembership/{idUser}/{idFoyer}")
    public FMembership addFMembership (@RequestBody FMembership fm,@PathVariable ("idFoyer") Long idFoyer,@PathVariable ("idUser") Long idUser)
    {
        FMembership fmr=fMembershipServiceI.addFMembership(fm,idUser,idFoyer);

        return fmr ;}


    @PutMapping("/updateFMembership")
    public FMembership updateFMembership(@RequestBody FMembership fm) {return fMembershipServiceI.updateFMembership(fm);
    }


    @GetMapping("/FMembership/{idFMembership}")
    public  FMembership retrieveFMembership(@PathVariable(value = "idFMembership") Long idFMembership) {
        return fMembershipServiceI.retrieveFMembership(idFMembership);
    }

    @DeleteMapping("/deleteFMembership/{idFMembership}")
    public void deleteFMembership(@PathVariable("idFMembership")Long idFMembership){
        fMembershipServiceI.deleteFMembership(idFMembership);
    }
    @PutMapping("/freeBedAndRoomAfterMembershipEnds/{idFMembership}")
    public void freeBedAndRoomAfterMembershipEnds(@PathVariable("idFMembership") Long idFMembership) {
        fMembershipServiceI.freeBedAndRoomAfterMembershipEnds(idFMembership);
    }

}
