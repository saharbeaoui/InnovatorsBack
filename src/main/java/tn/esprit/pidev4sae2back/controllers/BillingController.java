package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Billing;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.services.BillingServiceI;
import tn.esprit.pidev4sae2back.services.RMembershipServiceI;

import java.util.List;

@RestController
@RequestMapping("/Billing")
public class BillingController {
    @Autowired
    BillingServiceI bsI;


    @PostMapping("/addBilling")
    public Billing addBilling(@RequestBody Billing billing) {
        return bsI.addBilling(billing);
    }

    @GetMapping("/retrieveBilling/{idBilling}")
    @ResponseBody
    public Billing retrieveBilling(@PathVariable Long idBilling){
        return bsI.retrieveBilling(idBilling);
    }

    @GetMapping("/retrieveAllBillings")
    public List<Billing> retrieveAllBillings(){
        return bsI.retrieveAllBillings();
    }

    @PutMapping("/updateBilling")
    public Billing updateBilling(@RequestBody Billing billing){
        return bsI.updateBilling(billing);
    }


    @DeleteMapping("/removebilling/{idBilling}")
    public void removebilling(@PathVariable Long idBilling) {
        bsI.removebilling(idBilling);
    }
}
