package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.pidev4sae2back.entities.FidelityTransaction;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.services.FidelityTransactionServiceI;
import tn.esprit.pidev4sae2back.services.FidelityTransactionServiceImp;

import java.util.List;

@RestController
public class FidelityTransactionController {

    @Autowired
    FidelityTransactionServiceI ftsI;







}
