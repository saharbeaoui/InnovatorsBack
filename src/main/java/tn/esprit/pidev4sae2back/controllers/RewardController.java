package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.FidelityCard;
import tn.esprit.pidev4sae2back.entities.Reward;
import tn.esprit.pidev4sae2back.services.RewardServiceI;
import tn.esprit.pidev4sae2back.services.RewardServiceImp;

import javax.mail.MessagingException;
import java.util.List;

@RestController
public class RewardController {

    @Autowired
    RewardServiceI rsI;


    @PostMapping("/createReward")
    public Reward createReward(@RequestBody Reward reward) {
        return rsI.createReward(reward);
    }


    @GetMapping("/getRewardById/{rewardId}")
    public Reward getRewardById(@PathVariable("rewardId") Long rewardId) {
        return rsI.getRewardById(rewardId);
    }

    @GetMapping("/getAllRewards")
    public List<Reward> getAllRewards() {
        return rsI.getAllRewards();
    }

    @PutMapping("/updateReward/{rewardId}")
    public Reward updateReward(@PathVariable("rewardId") Long rewardId, @RequestBody Reward reward) {
        return rsI.updateReward(rewardId);
    }

    @DeleteMapping("/deleteReward/{rewardId}")
    public void deleteReward(@PathVariable("rewardId") Long rewardId) {
        rsI.deleteReward(rewardId);
    }

    @PostMapping("/addRewardIfGoldMembership")
    public void addRewardIfGoldMembership(@RequestParam("fidelityCardId") Long fidelityCardId) throws MessagingException {
        rsI.addRewardIfGoldMembership(fidelityCardId);
    }

    @PostMapping("/addRewardIfSILVERMembership")
    public void addRewardIfSILVERMembership(@RequestParam("fidelityCardId") Long fidelityCardId) throws MessagingException {
        rsI.addRewardIfSILVERMembership(fidelityCardId);
    }

    @PostMapping("/addRewardIfBRONZEMembership")
    public void addRewardIfBRONZEMembership(@RequestParam("fidelityCardId") Long fidelityCardId) throws MessagingException {
        rsI.addRewardIfBRONZEMembership(fidelityCardId);
    }

    @PostMapping("/giveRewards")
    public void giveRewards(@RequestParam("fidelityCardId") Long fidelityCardId) throws MessagingException {
        rsI.giveRewards(fidelityCardId);
    }



}
