package tn.esprit.pidev4sae2back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.FidelityCardRepository;
import tn.esprit.pidev4sae2back.repositories.RewardRepository;
import tn.esprit.pidev4sae2back.utils.MailServiceReward;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Set;

import static tn.esprit.pidev4sae2back.services.RMembershipServiceImp.setEndDate;

@Slf4j
@Service
public class RewardServiceImp implements RewardServiceI{


    @Autowired
    RewardRepository rr;

    @Autowired
    FidelityCardRepository fcr;

    @Autowired
    private MailServiceReward mailServiceReward;

    @Autowired
    FidelityCardServiceI fidelityCardServiceI;

    @Autowired
    RMembershipServiceI rMembershipServiceI;


    @Override
    public Reward createReward(Reward reward) {return rr.save(reward);}


    @Override
    public Reward getRewardById(Long id) {
        return rr.findById(id).orElse(null);
    }

    @Override
    public List<Reward> getAllRewards() {
        return rr.findAll();
    }


    @Override
    public Reward updateReward(Long rewardId) {
        Reward reward= rr.findById(rewardId).orElse(null);
        return rr.save(reward);
    }

    @Override
    public void deleteReward(Long id) {
        Reward reward = getRewardById(id);
        rr.delete(reward);
    }
    @Override
    public void addRewardIfGoldMembership(Long fidelityCardId) throws MessagingException {
        FidelityCard fidelityCard= fcr.findById(fidelityCardId).orElseThrow(()-> new RuntimeException("Fidelity Card not found"));
        if (fidelityCard.getMembershipLevel() == MembershipLevel.GOLD) {
            Reward reward = new Reward();
            reward.setNameReward("GOLD reward");
            reward.setDescription("Congratulations, you have earned a GOLD reward! You have extra 6 MONTHES Membership IN OUR RESTAURANT" );
            reward.setPointValue(1000);
            reward.setFidelityCard(fidelityCard);
            mailServiceReward.sendEmailForRewards(fidelityCard.getUser().getEmail(),"Congratulations, you have earned a GOLD reward! You have extra 6 MONTHES Membership IN OUR RESTAURANT");
            rr.save(reward);
            fidelityCard.setTotalPoints(fidelityCard.getTotalPoints() - reward.getPointValue());
            fidelityCard.getRewards().add(reward);
            fcr.save(fidelityCard);
        }
    }

    @Override
    public void addRewardIfSILVERMembership(Long fidelityCardId) throws MessagingException {
        FidelityCard fidelityCard= fcr.findById(fidelityCardId).orElseThrow(()-> new RuntimeException("Fidelity Card not found"));
        if (fidelityCard.getMembershipLevel() == MembershipLevel.SILVER) {
            Reward reward = new Reward();
            reward.setNameReward("SILVER reward");
            reward.setDescription("Congratulations, you have earned a SILVER reward! You have an extra month Membership" );
            reward.setPointValue(500);
            reward.setFidelityCard(fidelityCard);
            mailServiceReward.sendEmailForRewards(fidelityCard.getUser().getEmail(),"Congratulations, you have earned a SILVER reward! You have 1 extra MONTH Membership IN OUR RESTAURANT");
            rr.save(reward);
            fidelityCard.setTotalPoints(fidelityCard.getTotalPoints() - reward.getPointValue());
            fidelityCard.setMembershipLevel(fidelityCardServiceI.getMembershipLevel(fidelityCard.getIdFidelityCard()));
            fidelityCard.getRewards().add(reward);
            fcr.save(fidelityCard);
        }
    }

    @Override
    public void addRewardIfBRONZEMembership(Long fidelityCardId) throws MessagingException {
        FidelityCard fidelityCard= fcr.findById(fidelityCardId).orElseThrow(()-> new RuntimeException("Fidelity Card not found"));
        if (fidelityCard.getMembershipLevel() == MembershipLevel.BRONZE) {
            Reward reward = new Reward();
            reward.setNameReward("BRONZE reward");
            reward.setDescription("Congratulations, you have earned a BRONZE reward! You have 1 extra DAY Membership" );
            reward.setPointValue(300);
            reward.setFidelityCard(fidelityCard);
            mailServiceReward.sendEmailForRewards(fidelityCard.getUser().getEmail(),"Congratulations, you have earned a GOLD reward! You have 1 extra DAY Membership IN OUR RESTAURANT");
            rr.save(reward);
            fidelityCard.setTotalPoints(fidelityCard.getTotalPoints() - reward.getPointValue());
            fidelityCard.getRewards().add(reward);
            fcr.save(fidelityCard);
        }
    }

    @Override
    public void giveRewards(Long fidelityCardId) throws MessagingException {
        FidelityCard fidelityCard= fcr.findById(fidelityCardId).orElseThrow(()-> new RuntimeException("Fidelity Card not found"));
        if (fidelityCard.getMembershipLevel()==MembershipLevel.GOLD){
            addRewardIfGoldMembership(fidelityCardId);
            RMembership rMembership= rMembershipServiceI.getLastMembership(fidelityCard.getUser());
            rMembership.setEndDate(rMembership.getEndDate().plusMonths(6));
            fidelityCardServiceI.updateMemberShipLevelFidelityCard(fidelityCardId);
        }
        if (fidelityCard.getMembershipLevel()==MembershipLevel.SILVER){
            addRewardIfSILVERMembership(fidelityCardId);
            RMembership rMembership= rMembershipServiceI.getLastMembership(fidelityCard.getUser());
            rMembership.setEndDate(rMembership.getEndDate().plusMonths(1));
            fidelityCardServiceI.updateMemberShipLevelFidelityCard(fidelityCardId);
        }
        if (fidelityCard.getMembershipLevel()==MembershipLevel.BRONZE){
            addRewardIfBRONZEMembership(fidelityCardId);
            RMembership rMembership= rMembershipServiceI.getLastMembership(fidelityCard.getUser());
            rMembership.setEndDate(rMembership.getEndDate().plusDays(1));
            fidelityCardServiceI.updateMemberShipLevelFidelityCard(fidelityCardId);
        }else{
            log.info("None Level");
        }
    }

}
