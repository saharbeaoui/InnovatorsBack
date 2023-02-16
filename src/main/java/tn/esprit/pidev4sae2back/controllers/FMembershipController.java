package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.entities.Foyer;
import tn.esprit.pidev4sae2back.services.FMembershipServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/fMembership")
public class FMembershipController {
    @Autowired
    FMembershipServiceImp fMembershipServiceImp;

    //http://localhost:8082/test/fmembership/retrieveAllFMembership
    @GetMapping("/retrieveFMembership")
    public List<FMembership> retrieveAllFoyers(){
        return fMembershipServiceImp.retrieveAllFMembership();
    }
    //http://localhost:8082/test/fmembership/addFmembership
    @PostMapping("/addFmembership")
    public FMembership addFMembership (@RequestBody FMembership fm) {return fMembershipServiceImp.addFMembership(fm);}
    //http://localhost:8082/test/fmembership/updatefmembership

    @PutMapping("/updateFMembership")
    public FMembership updateFMembership(@RequestBody FMembership fm) {return fMembershipServiceImp.updateFMembership(fm);
    }

    //http://localhost:8082/test/fmembership/Fmembership/{idFMembership}
    @GetMapping("/Fmembership/{idFMembership}")
    public  FMembership retrieveFMembership(@PathVariable(value = "idFMembership") Long idFMembership) {
        return fMembershipServiceImp.retrieveFMembership(idFMembership);
    }
    //http://localhost:8082/test/fmembership/deleteFMmembership/{idFMembership}
    @DeleteMapping("/deleteFmembership/{idFMmembership}")
    public void deleteFmembership(@PathVariable("idFMmembership")Long idFMmembership){
        fMembershipServiceImp.deleteFMembership(idFMmembership);
    }

}
