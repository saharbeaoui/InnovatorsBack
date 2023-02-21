package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev4sae2back.services.RewardServiceI;
import tn.esprit.pidev4sae2back.services.RewardServiceImp;

@RestController
public class RewardController {

    @Autowired
    RewardServiceI rsI;



}
