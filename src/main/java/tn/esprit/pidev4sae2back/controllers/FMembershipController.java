package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.services.FMembershipServiceImp;
import tn.esprit.pidev4sae2back.services.RoomAssignmentServiceImp;
import tn.esprit.pidev4sae2back.services.RoomServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/fMembership")
public class FMembershipController {
    @Autowired
   FMembershipServiceImp fMembershipServiceImp;
    RoomServiceImp roomServiceImp;

    //http://localhost:8090/test/fmembership/retrieveAllFMembership
    @GetMapping("/retrieveFMembership")
    public List<FMembership> retrieveAllFoyers(){
        return fMembershipServiceImp.retrieveAllFMembership();
    }
    @PostMapping("/addFMembership/{idUser}/{idRoom}")
    public FMembership addFMembership (@RequestBody FMembership fm,@PathVariable ("idUser") Long idUser,@PathVariable ("idRoom") Long idRoom)
    {
        FMembership fmr=fMembershipServiceImp.addFMembership(fm,idUser);
        roomServiceImp.assignMembershiptoRoom(idRoom, fmr.getIdFMembership());

        return fmr ;}

    @PostMapping("/addFMembership/{idUser}")
    public FMembership addFMembership (@RequestBody FMembership fm,@PathVariable ("idUser") Long idUser) {return fMembershipServiceImp.addFMembership(fm,idUser);}
    @PostMapping("/addFMembership")
    public FMembership addFMembership (@RequestBody FMembership fm) {return fMembershipServiceImp.addFMembership(fm, 0L);}

    @PutMapping("/updateFMembership")
    public FMembership updateFMembership(@RequestBody FMembership fm) {return fMembershipServiceImp.updateFMembership(fm);
    }


    @GetMapping("/FMembership/{idFMembership}")
    public  FMembership retrieveFMembership(@PathVariable(value = "idFMembership") Long idFMembership) {
        return fMembershipServiceImp.retrieveFMembership(idFMembership);
    }

    @DeleteMapping("/deleteFMembership/{idFMembership}")
    public void deleteFMembership(@PathVariable("idFMembership")Long idFMembership){
        fMembershipServiceImp.deleteFMembership(idFMembership);
    }

}
