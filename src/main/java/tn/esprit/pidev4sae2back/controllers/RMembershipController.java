package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.services.RMembershipServiceI;

@RestController
public class RMembershipController {

    @Autowired
    RMembershipServiceI rmI;


    @PostMapping("/addRMembership")
    public RMembership addRMembership(@RequestBody RMembership rMembership)
    {
        return rmI.addRMembership(rMembership);
    }

}
