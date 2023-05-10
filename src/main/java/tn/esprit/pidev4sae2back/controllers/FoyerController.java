package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Duration;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.entities.FNameBlock;
import tn.esprit.pidev4sae2back.entities.Foyer;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.services.FoyerServiceI;

import java.util.List;

@RestController
@RequestMapping("/foyer")
public class FoyerController {
    @Autowired
    FoyerServiceI foyerServiceI;


    @PostMapping("/addFoyer")
    public Foyer addFoyer(@RequestBody Foyer foyer) {
        return foyerServiceI.addFoyer(foyer);
    }

    @GetMapping("/getAllFoyers")
    public List<Foyer> getAllFoyers(){
        return foyerServiceI.getAllFoyers();
    }

    @GetMapping("/getFoyer/{idFoyer}")
    @ResponseBody
    public Foyer getFoyer(@PathVariable Long idFoyer){
        return foyerServiceI.getFoyer(idFoyer);
    }
    @PutMapping("/updateFoyer")
    public Foyer updateFoyer(@RequestBody Foyer foyer){
        return foyerServiceI.updateFoyer(foyer);
    }


    @DeleteMapping("/removefoyer/{idFoyer}")
    public void removeFoyer(@PathVariable Long idFoyer) {
        foyerServiceI.removeFoyer(idFoyer);
    }


    @GetMapping("/nbPlaceDisponiblePERBLOC/{idFoyer}/{idBlock}")
    public Integer nbPlaceDisponiblePERBLOC(Long idFoyer,Long idBlock){
        return foyerServiceI.nbPlaceDisponiblePERBLOC(idFoyer,idBlock);
    }
  
    @GetMapping("/isFullFoyer/{idFoyer}")
    public Boolean checkFoyerFullness(@PathVariable Long idFoyer){
        return foyerServiceI.isFullFoyer(idFoyer);
    }

    @GetMapping("/searchfoyer")
    public List<Foyer> searchFoyers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "capacity", required = false) Integer capacity) {
        return foyerServiceI.searchFoyers(name, capacity);
    }

    @GetMapping("/searchmembership")
    public ResponseEntity<List<FMembership>> searchMembershipByDuration(
            @RequestParam (value = "duration",required = false) Duration duration) {
        List<FMembership> fMemberships = foyerServiceI.searchMembershipByDuration(duration);
        return ResponseEntity.ok(fMemberships);
    }

}

