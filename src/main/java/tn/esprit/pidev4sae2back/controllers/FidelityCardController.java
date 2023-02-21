package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev4sae2back.entities.FidelityCard;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.services.FidelityCardServiceI;
import tn.esprit.pidev4sae2back.services.FidelityCardServiceImp;

@RestController
public class FidelityCardController {

    @Autowired
    FidelityCardServiceI fcsI;


    @PostMapping("/addFidelityCard/{idUser}")
    public FidelityCard addFidelityCard(@RequestBody FidelityCard fidelityCard,@PathVariable Long idUser) {
        return fcsI.addFidelityCardAndAssToUser(fidelityCard,idUser);
    }


}
