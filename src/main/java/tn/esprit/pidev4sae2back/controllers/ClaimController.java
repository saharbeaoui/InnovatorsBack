package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Claim;
import tn.esprit.pidev4sae2back.services.ClaimServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/claim")
public class ClaimController {
    ClaimServiceImp claimServiceImp;

    //http://localhost:8082/test/claim/retrieveAllClaims
    @GetMapping("/retrieveAllClaims")
    public List<Claim> retrieveAllClaims(){
        return claimServiceImp.retrieveAllClaims();
    }

    //http://localhost:8082/test/claim/addClaim
    @PostMapping("/addClaim")
    public Claim addClaim(@RequestBody Claim c) {
        return claimServiceImp.addClaim(c);
    }
    //http://localhost:8082/test/claim/updateClaim
    @PutMapping("/updateClaim")
    public Claim updateClaim(@RequestBody Claim c) {
        return claimServiceImp.updateClaim(c);
    }
    //http://localhost:8082/test/claim/retrieveClaim/{idClaim}
    @GetMapping("/retrieveClaim/{idClaim}")
    public Claim retrieveClaim(@PathVariable(value = "idClaim") Long idClaim) {
        return claimServiceImp.retrieveClaim(idClaim) ;
    }
    //http://localhost:8082/test/claim/deleteClaim/{idClaim}
    @DeleteMapping("/deleteClaim/{idClaim}")
    public void deleteClaim(@PathVariable("idClaim")Long idClaim){
        claimServiceImp.deleteClaim(idClaim);
    }


}
