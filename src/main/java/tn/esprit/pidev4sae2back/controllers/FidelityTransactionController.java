package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.FidelityCard;
import tn.esprit.pidev4sae2back.entities.FidelityTransaction;
import tn.esprit.pidev4sae2back.entities.TransactionType;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.services.FidelityTransactionServiceI;
import tn.esprit.pidev4sae2back.services.FidelityTransactionServiceImp;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class FidelityTransactionController {

    @Autowired
    FidelityTransactionServiceI ftsI;


    @PostMapping("/addTransaction/{FdId}")
    public FidelityTransaction addTransaction(@RequestBody FidelityTransaction transaction,@PathVariable Long FdId) {
        return ftsI.addTransaction(transaction,FdId);
    }

    @GetMapping("/showTransactionByUser/{userId}")
    public List<FidelityTransaction> showTransactionByUser(@PathVariable Long userId){
        return ftsI.showTransactionByUser(userId);
    }

    @GetMapping("/showTransactionByFidelityCard/{fidelityCardId}")
    public List<FidelityTransaction> showTransactionByFidelityCard(@PathVariable Long fidelityCardId){
        return ftsI.showTransactionByFidelityCard(fidelityCardId);
    }





}
