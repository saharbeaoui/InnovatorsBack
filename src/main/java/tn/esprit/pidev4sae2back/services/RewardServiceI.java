package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.FidelityCard;
import tn.esprit.pidev4sae2back.entities.Reward;

import javax.mail.MessagingException;
import java.util.List;

public interface RewardServiceI {
    Reward createReward(Reward reward);

    void addRewardIfSILVERMembership(Long fidelityCardId) throws MessagingException;

    Reward getRewardById(Long id);

    List<Reward> getAllRewards();


    Reward updateReward(Long rewardId);

    void deleteReward(Long id);

    void addRewardIfGoldMembership(Long fidelityCardId) throws MessagingException;

    void addRewardIfBRONZEMembership(Long fidelityCardId) throws MessagingException;

    void giveRewards(Long fidelityCardId) throws MessagingException;


}
