package tn.esprit.pidev4sae2back.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.MembershipLevel;
import tn.esprit.pidev4sae2back.repositories.RewardRepository;

import java.util.List;

@Service
public class RewardServiceImp implements RewardServiceI{


    @Autowired
    RewardRepository rr;


}
