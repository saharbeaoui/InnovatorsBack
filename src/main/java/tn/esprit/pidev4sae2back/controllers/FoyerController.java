package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Foyer;
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
}