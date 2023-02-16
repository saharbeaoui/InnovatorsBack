package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Foyer;
import tn.esprit.pidev4sae2back.services.FoyerServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerController {
    @Autowired
    FoyerServiceImp foyerServiceImp;

    //http://localhost:8082/test/foyer/retrieveAllFoyes
    @GetMapping("/retrieveAllFoyers")
    public List<Foyer> retrieveAllFoyers(){
        return foyerServiceImp.retrieveAllFoyers();
    }

    //http://localhost:8082/test/foyer/addFoyer
    @PostMapping("/addFoyer")
    public Foyer addFoyer(@RequestBody Foyer f) {return foyerServiceImp.addFoyer(f);}

    //http://localhost:8082/test/foyer/updateFoyer
    @PutMapping("/updateFoyer")
    public Foyer updateFoyer(@RequestBody Foyer f) {
        return foyerServiceImp.updateFoyer(f);
    }
    //http://localhost:8082/test/foyer/Foyer/{idFoyer}
    @GetMapping("/Foyer/{idFoyer}")
    public  Foyer retrieveFoyer(@PathVariable(value = "idFoyer") Long idFoyer) {
        return foyerServiceImp.retrieveFoyer(idFoyer);
    }
    //http://localhost:8082/test/foyer/deleteFoyer/{idFoyer}
    @DeleteMapping("/deleteFoyer/{idFoyer}")
    public void deleteFoyer(@PathVariable("idFoyer")Long idFoyer){
        foyerServiceImp.deleteFoyer(idFoyer);
    }

}

