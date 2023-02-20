package tn.esprit.pidev4sae2back.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.services.RMembershipServiceI;

import javax.mail.MessagingException;
import javax.websocket.server.PathParam;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/RMembership")
public class RMembershipController {

    @Autowired
    RMembershipServiceI rmI;


    @PostMapping("/addRMembership")
    public RMembership addRMembership(@RequestBody RMembership rMembership) {
        return rmI.addRMembership(rMembership);
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

    @PutMapping("/updateRMembership")
    public RMembership updateRMembership(@RequestBody RMembership rMembership){
       return rmI.updateRMembership(rMembership);
    }


    @DeleteMapping("/removeRMembership/{idRMembership}")
    public void removeEquipe(@PathVariable Long idRMembership) {
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
    public long nbRMembershipValides(
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


}
