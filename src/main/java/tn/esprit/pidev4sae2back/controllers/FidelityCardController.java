package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.FidelityCard;
import tn.esprit.pidev4sae2back.entities.MembershipLevel;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.FidelityCardRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;
import tn.esprit.pidev4sae2back.services.FidelityCardServiceI;
import tn.esprit.pidev4sae2back.services.FidelityCardServiceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/FidelityCard")
public class FidelityCardController {

    @Autowired
    FidelityCardServiceI fcsI;

    @Autowired
    FidelityCardRepository fcr;

    @Autowired
    UserRepository ur;


    @GetMapping("/getFidelityCardByUser/{idUser}")
    public FidelityCard getFidelityCardByUser(@PathVariable Long idUser){
        User user = ur.findById(idUser).orElse(null);
        user.getFidelityCard();
        return user.getFidelityCard();
    }

    @PostMapping("/addFidelityCard/{idUser}")
    public FidelityCard addFidelityCard(@RequestBody FidelityCard fidelityCard,@PathVariable Long idUser) {
        return fcsI.addFidelityCardAndAssToUser(fidelityCard,idUser);
    }

    @GetMapping("/retrieveFidelityCard/{idFidelityCard}")
    public FidelityCard retrieveFidelityCard(@PathVariable Long idFidelityCard) {
        return fcsI.retrieveFidelityCard(idFidelityCard);
    }

    @GetMapping("/retrieveAllFidelityCard")
    public List<FidelityCard> retrieveAllFidelityCard() {
        return fcsI.retrieveAllFidelityCard();
    }

    @PutMapping("/updateFidelityCard/{userId}")
    public FidelityCard updateFidelityCard(@RequestBody FidelityCard fidelityCard,@PathVariable Long userId) {
        return fcsI.updateFidelityCard(fidelityCard,userId);
    }

    @DeleteMapping("/removeFidelityCard/{idFidelityCard}")
    public void removeFidelityCard(@PathVariable Long idFidelityCard) {
        fcsI.removeFidelityCard(idFidelityCard);
    }


    @GetMapping("/getMembershipLevel/{fidelityCardId}")
    public MembershipLevel getMembershipLevel(@PathVariable Long fidelityCardId) {
       return fcsI.getMembershipLevel(fidelityCardId);
    }


    @GetMapping("/getMembershipLevelByUser/{userId}")
    public MembershipLevel getMembershipLevelByUser(@PathVariable Long userId) {
        return fcsI.getMembershipLevelByUser(userId);
    }

    @GetMapping("/getTotalPointsByUser/{userId}")
    public int getTotalPointsByUser(@PathVariable Long userId) {
        return fcsI.getTotalPointsByUser(userId);
    }

    @GetMapping("/getHashmapLevelAndPointsParUser/{userId}")
    public Map<String, String> getHashmapLevelAndPointsParUser(@PathVariable Long userId) {
        User user = ur.findById(userId).orElse(null);
        Map<String, String> map= new HashMap<>();
        map.put("User : " ,user.getFirstName());
        map.put("Level : ",getMembershipLevelByUser(userId).toString());
        map.put("Points : ", String.valueOf(getTotalPointsByUser(userId)));
        return map;
    }

    @PutMapping("/updateMemberShipLevelFidelityCard/{fidelityCardId}")
    public FidelityCard updateMemberShipLevelFidelityCard(@PathVariable Long fidelityCardId){
      return fcsI.updateMemberShipLevelFidelityCard(fidelityCardId);
    }

    @GetMapping("/showTotalPointsByUser/{userId}")
    public int showTotalPointsByUser(@PathVariable Long userId){
       return fcsI.showTotalPointsByUser(userId);
    }


    @GetMapping("/showUserFullNameOfAFideli tyCard/{fidelityCardId}")
    public String showUserFullNameOfAFidelityCard(@PathVariable Long fidelityCardId){
        return fcsI.showUserFullNameOfAFidelityCard(fidelityCardId);
    }


}
