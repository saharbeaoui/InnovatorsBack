package tn.esprit.pidev4sae2back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.RMembershipRepository;
import tn.esprit.pidev4sae2back.repositories.RestaurantRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;
import tn.esprit.pidev4sae2back.utils.MailService;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Autowired
    private MailService ms;


    @Override
    public RMembership addRMembership(RMembership rMembership){
        rMembership = setEndDate(rMembership);
        rmr.save(rMembership);
        return rMembership;
    }

    public static boolean datesOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && end1.isAfter(start2);
    }

    public static RMembership setEndDate(RMembership rMembership){
        if (rMembership.getDuration()==Duration.DAY){
            rMembership.setEndDate(rMembership.getStartDate().plusDays(1));
        }
        if (rMembership.getDuration()==Duration.MONTH){
            rMembership.setEndDate(rMembership.getStartDate().plusMonths(1));
        }
        if (rMembership.getDuration()==Duration.SEMESTER){
            rMembership.setEndDate(rMembership.getStartDate().plusMonths(6));
        }
        if (rMembership.getDuration()==Duration.YEAR){
            rMembership.setEndDate(rMembership.getStartDate().plusYears(1));
        }return rMembership;
    }

    @Override
    public RMembership addRMembershipWithVerify(RMembership rMembership,long idStudent) {
        rMembership = setEndDate(rMembership);
        User u = ur.findById(idStudent).get();
        List<RMembership> lurm = rmr.findByUser(u);
        for (RMembership urm : lurm){
            if (datesOverlap(urm.getStartDate(),urm.getEndDate(),rMembership.getStartDate(),rMembership.getEndDate())){
                return null;
            }
        }
        rMembership.setUser(u);
        rmr.save(rMembership);
        return rMembership;
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
    public List<RMembership> retrieveAllRMembershipsValidated() {
        return rmr.findByValidatedIsTrue();
    }

    @Override
    public void validateRMembership(Long idRMembership) throws MessagingException {
        RMembership rMembership = rmr.findById(idRMembership).orElse(null);
        rMembership.validate();
        rmr.save(rMembership);
        ms.sendMembershipValidationEmail(rMembership.getUser().getEmail());
    }

    @Override
    public void unValidateRMembership(Long idRMembership) {
        RMembership rMembership = rmr.findById(idRMembership).orElse(null);
        rMembership.unvalidate();
        rmr.save(rMembership);
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
    @Override
    public List<RMembership> listeRMembershipsGUESTS(Long idRestaurant) {
        Restaurant r = rr.findById(idRestaurant).orElse(null);

        List<RMembership> RMembershipsGUESTS = rmr.findAllByRestaurantAndUser_TypeUser(r, TypeUser.GUEST);
        List<RMembership> RMembershipsTEACHERS = rmr.findAllByRestaurantAndUser_TypeUser(r, TypeUser.TEACHER);
        return RMembershipsGUESTS;
    }
    @Override
    public List<RMembership> listeRMembershipsSTUDENTS(Long idRestaurant) {
        Restaurant r = rr.findById(idRestaurant).orElse(null);

        List<RMembership> RMembershipsSTUDENTS = rmr.findAllByRestaurantAndUser_TypeUser(r, TypeUser.STUDENT);
        return RMembershipsSTUDENTS;
    }
    @Override
    public List<RMembership> listeRMembershipsTEACHERS(Long idRestaurant) {
        Restaurant r = rr.findById(idRestaurant).orElse(null);

        List<RMembership> RMembershipsTEACHERS = rmr.findAllByRestaurantAndUser_TypeUser(r, TypeUser.TEACHER);
        return RMembershipsTEACHERS;
    }

    @Override
    public List<RMembership> RMembershipValidesBETWEEN(LocalDateTime startDate, LocalDateTime endDate) {

        List<RMembership> listRMembership = rmr.findRMembershipBetweenTwoDates(startDate,endDate);
        return listRMembership;
    }

    @Override
    public long nbRMembershipValidesBETWEEN(LocalDateTime startDate, LocalDateTime endDate) {

        List<RMembership> listRMembership = rmr.findRMembershipBetweenTwoDates(startDate,endDate);

        return listRMembership.stream().filter(c-> !c.getValidated()).count();
    }

    @Scheduled(fixedRate = 30000)
    public void twoDaysLeftWarning(){
        List<RMembership> lrm = rmr.findAll();
        for (RMembership rm : lrm){
            if (rm.getEndDate().minusDays(2).toLocalDate().isEqual(LocalDate.now())){
                log.info("UserName : "+ rm.getUser().getFirstName()+""+rm.getUser().getLastName()+  " : " + rm.getEndDate() +"Last 2 days");
            }
        }
    }

    @Override
    public List<RMembership> findAllByUser(Long idUser) {
       return rmr.findAllByUserIdUser(idUser);
    }

}
