package tn.esprit.pidev4sae2back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.entities.Restaurant;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.RMembershipRepository;
import tn.esprit.pidev4sae2back.repositories.RestaurantRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;

import java.util.List;

@Slf4j
@Service
public class RMembershipServiceImp implements RMembershipServiceI{
    @Autowired
    RMembershipRepository rmr;

    @Autowired
    RestaurantRepository rr;

    @Autowired
    UserRepository ur;

    @Override
    public RMembership addRMembership(RMembership rMembership){
        return rmr.save(rMembership);
    }

    @Override
    public RMembership retrieveRMembership(Long idRMembership) {
        return rmr.findById(idRMembership).orElse(null);
    }
    @Override
    public List<RMembership> retrieveAllRMemberships() {
        return rmr.findAll();
    }

    @Override
    public RMembership updateRMembership(RMembership rMembership) {
        return rmr.save(rMembership);
    }

    @Override
    public void removeRMembership(Long idRMembership) {
        rmr.deleteById(idRMembership);
    }

    @Override
    public RMembership addRMembershipAndAssToUserAndRestau(RMembership rMembership,Long userId){
        Restaurant r = rr.findAll().get(0);
        User u = ur.findById(userId).orElse(null);
        if (r != null) {
            rMembership.setRestaurant(r);
            rMembership.setUser(u);
        }else log.info("Restaurant n'existe pas");
        return rmr.save(rMembership);
    }

}
