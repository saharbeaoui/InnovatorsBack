package tn.esprit.pidev4sae2back.controllers;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.RMembershipRepository;
import tn.esprit.pidev4sae2back.repositories.RestaurantRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;
import tn.esprit.pidev4sae2back.services.RMembershipServiceI;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/RMembership")
public class RMembershipController {

    @Autowired
    RMembershipServiceI rmI;

    @Autowired
    RestaurantRepository rr;

    @Autowired
    UserRepository ur;


    @PostMapping("/addRMembership")
    public RMembership addRMembership(@RequestBody RMembership rMembership) {
        return rmI.addRMembership(rMembership);
    }

    @GetMapping("/getLastMembership{userId}")
    public RMembership getLastMembership(@PathVariable Long userId) {
       return rmI.getLastMembership(userId);
    }

        @PostMapping("/addRMembershipWithVerify/{idStudent}")
        public RMembership addRMembershipWithVerify(@RequestBody RMembership rMembership,@PathVariable long idStudent){
            if (rmI.addRMembershipWithVerify(rMembership, idStudent) == null) {
                log.info("User Already have a membership for the duration");
                return null;
            }
            return  rmI.addRMembershipWithVerify(rMembership, idStudent);
        }
    @GetMapping("/retrieveRMembership/{idRMembership}")
    @ResponseBody
    public RMembership retrieveRMembership(@PathVariable Long idRMembership){
        return rmI.retrieveRMembership(idRMembership);
    }

    @GetMapping("/retrieveAllRMemberships")
    public List<RMembership> getAllRMemberships(){
        return rmI.retrieveAllRMemberships();
    }

    @PutMapping("/updateRMembership/{idRMembership}/{startDate}/{tm}/{d}")
    public RMembership updateRMembership(@PathVariable Long idRMembership ,@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,@PathVariable TypeMembership tm,@PathVariable Duration d){
       return rmI.updateRMembership(idRMembership,startDate,tm,d);
    }


    @DeleteMapping("/removeRMembership/{idRMembership}")
    public void removeRMembership(@PathVariable Long idRMembership) {
        rmI.removeRMembership(idRMembership);
    }

    @GetMapping("/retrieveAllRMembershipsValidated")
    public List<RMembership> getAllRMembershipsValidated(){
        return rmI.retrieveAllRMembershipsValidated();
    }

    @PostMapping("/validateRMembership/{idRMembership}")
    @ResponseBody
    public void validateRMembership(@PathVariable Long idRMembership) throws MessagingException {
        rmI.validateRMembership(idRMembership);

    }
    @PostMapping("/unValidateRMembership/{idRMembership}")
    @ResponseBody
    public void unValidateRMembership(@PathVariable Long idRMembership){
        rmI.unValidateRMembership(idRMembership);

    }
    @PostMapping("/addRMembershipAndAssToUserAndRestau/{userId}")
    public RMembership addRMembershipAndAssToUserAndRestau(@RequestBody RMembership rMembership,@PathVariable Long userId) {
        return rmI.addRMembershipAndAssToUserAndRestau(rMembership,userId);
    }
    @GetMapping("/listeRMembershipsGUESTS/{idRestau}")
    public List<RMembership> listeRMembershipsGUESTS(@PathVariable Long idRestau){
        return rmI.listeRMembershipsGUESTS(idRestau);
    }

    @GetMapping("/listeRMembershipsTEACHERS/{idRestau}")
    public List<RMembership> listeRMembershipsTEACHERS(@PathVariable Long idRestau){
        return rmI.listeRMembershipsTEACHERS(idRestau);
    }

    @GetMapping("/listeRMembershipsSTUDENTS/{idRestau}")
    public List<RMembership> listeRMembershipsSTUDENTS(@PathVariable Long idRestau){
        return rmI.listeRMembershipsSTUDENTS(idRestau);
    }
    @GetMapping("/nbRMembershipValides/{startDate}/{endDate}")
    public int nbRMembershipValides(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {

        return rmI.nbRMembershipValidesBETWEEN(startDate,endDate);
    }

    @GetMapping("/RMembershipValides/{startDate}/{endDate}")
    public List<RMembership> RMembershipValides(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {

        log.info(startDate+"   "+endDate);
        return rmI.RMembershipValidesBETWEEN(startDate,endDate);
    }
    @GetMapping("/findAllByUser/{idUser}")
    @ResponseBody
    public List<RMembership> findAllByUser(@PathVariable Long idUser){
        return rmI.findAllByUser(idUser);
    }




    @PutMapping("/renewMembership/{membershipId}/{duration}/{typeMembership}")
    public RMembership renewMembership(@PathVariable("membershipId") Long membershipId ,@PathVariable("duration") Duration duration,@PathVariable("typeMembership") TypeMembership typeMembership) {
        return rmI.renewMembership(membershipId, duration, typeMembership);
    }

    @GetMapping("/getRenewalRate/{startDate}/{endDate}")
    public Map<String, Double> getRenewalRate(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {

        double renewalRate = rmI.calculateRenewalRate(startDate, endDate);

        Map<String, Double> response = new HashMap<>();
        response.put("renewalRate", renewalRate);

        return response;
    }

    @GetMapping("/statsAboutUsersMemberships")
    public Map<String, Double> statsAboutUsersMemberships() {
        Restaurant restaurant= rr.findAll().get(0);
        int nbRMembershipsSTUDENTS = rmI.listeRMembershipsSTUDENTS(restaurant.getIdRestau()).size()*100;
        int nbRMembershipsGUESTS = rmI.listeRMembershipsGUESTS(restaurant.getIdRestau()).size()*100;
        int nbRMembershipsTEACHERS = rmI.listeRMembershipsTEACHERS(restaurant.getIdRestau()).size()*100;
        Map<String, Double> response = new HashMap<>();
        response.put("STUDENTS", (double) nbRMembershipsSTUDENTS);
        response.put("GUESTS", (double) nbRMembershipsGUESTS);
        response.put("TEACHERS", (double) nbRMembershipsTEACHERS);

        return response;
    }

    @GetMapping("/WhosTheBestUsers")
    public User getWhosTheBestUsers() {
        Map<Long, Double> map = rmI.getRevenuePerUSER();
        Optional<Map.Entry<Long, Double>> maxEntry = map.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue));

        if (maxEntry.isPresent()) {
            Long userId = maxEntry.get().getKey();
            User bestUser = ur.findById(userId).orElse(null);
            bestUser.getFidelityCard().setTotalPoints(bestUser.getFidelityCard().getTotalPoints()+1000);
            return bestUser;
        } else {
            throw new RuntimeException("No users found");
        }
    }


    @GetMapping("/getWhosTheBestRole")
    public String getWhosTheBestRole() {
        Map<String, Double> map = statsAboutUsersMemberships();
        Optional<Map.Entry<String, Double>> maxEntry = map.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue));

        if (maxEntry.isPresent()) {
            String userRole = maxEntry.get().getKey();
            return userRole;
        } else {
            throw new RuntimeException("No users found");
        }
    }


    @GetMapping("/getRevenuePerUSER")
    public Map<Long, Double> getRevenuePerUSER() {
        return rmI.getRevenuePerUSER();
    }

    @GetMapping("/getRevenuePerROLE")
    public Map<TypeUser, Double> getRevenuePerROLE() {
        return rmI.getRevenuePerROLE();
    }

    @GetMapping("/getRevenueTOTAL")
    public int getRevenueTOTAL() {
        return rmI.getRevenueTOTAL();
    }
}
