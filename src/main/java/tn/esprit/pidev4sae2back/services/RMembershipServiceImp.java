package tn.esprit.pidev4sae2back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.*;
import tn.esprit.pidev4sae2back.utils.MailService;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static tn.esprit.pidev4sae2back.entities.TypeUser.STUDENT;
import static tn.esprit.pidev4sae2back.entities.TypeUser.GUEST;
import static tn.esprit.pidev4sae2back.entities.TypeUser.TEACHER;

@Slf4j
@Service
public class RMembershipServiceImp implements RMembershipServiceI{
    @Autowired
    RMembershipRepository rmr;


    @Autowired
    RestaurantRepository rr;

    @Autowired
    FidelityCardRepository fcr;

    @Autowired
    UserRepository ur;

    @Autowired
    BillingRepository br;

    @Autowired
    FidelityCardServiceI fidelityCardServiceI;

    @Autowired
    RUserTransactionRepository utr;

    @Autowired
    FidelityTransactionRepository ftr;


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
        User u = ur.findById(idStudent).orElse(null);
        List<RMembership> lurm = rmr.findByUser(u);
        for (RMembership urm : lurm){
            if (datesOverlap(urm.getStartDate(),urm.getEndDate(),rMembership.getStartDate(),rMembership.getEndDate())){
                return null;
            }
        }
        Restaurant r = rr.findAll(). get(0);
        rMembership.setRestaurant(r);
        rMembership.setUser(u);
        rMembership.setValidated(false);
        rMembership.setHasRenewed(false);
        rMembership.setPrice(br.getTotalCostWhere(rMembership.getTypeMembership(),rMembership.getDuration(),u.getTypeUser()));
        rmr.save(rMembership);


        FidelityCard fidelityCard = new FidelityCard();
        fidelityCard.setUser(u);
        fidelityCard.setCardNumber();
        fidelityCard.setExpirationDate(LocalDate.now().plusYears(2));
        fidelityCard.setTotalPoints(0);
        fidelityCard.setMembershipLevel(MembershipLevel.NONE);
        fcr.save(fidelityCard);

        RUserTransaction rUserTransaction = new RUserTransaction();
        rUserTransaction.setTransactionDate(rMembership.getStartDate());
        rUserTransaction.setTransactionAmount(rMembership.getPrice());
        rUserTransaction.setUserId(idStudent);
        utr.save(rUserTransaction);
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
    public RMembership updateRMembership(Long idRMembership ,LocalDateTime startDate,TypeMembership tm,Duration d) {
        RMembership r =rmr.findById(idRMembership).orElse(null);
        r.setDuration(d);
        r.setTypeMembership(tm);
        r.setStartDate(startDate);
        r=setEndDate(r);
        r.setPrice(br.getTotalCostWhere(r.getTypeMembership(),r.getDuration(),r.getUser().getTypeUser()));
        return rmr.save(r);
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
        //ms.sendMembershipValidationEmail(rMembership.getUser().getEmail());
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
        return RMembershipsGUESTS;
    }
    @Override
    public List<RMembership> listeRMembershipsSTUDENTS(Long idRestaurant) {
        Restaurant r = rr.findById(idRestaurant).orElse(null);

        List<RMembership> RMembershipsSTUDENTS = rmr.findAllByRestaurantAndUser_TypeUser(r, STUDENT);
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
    public int nbRMembershipValidesBETWEEN(LocalDateTime startDate, LocalDateTime endDate) {

       return rmr.findAllByStartDateBetween(startDate,endDate).size();
    }

    @Transactional
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

    @Override
    public RMembership getLastMembership(Long userId) {
        User user = ur.findById(userId).orElse(null);
        Set<RMembership> memberships = user.getRMembership();
        if (memberships == null || memberships.isEmpty()) {
            log.info("User have no Membership");
            return null;
        }
        List<RMembership> membershipList = new ArrayList<>(memberships);
        Collections.sort(membershipList, Comparator.comparing(RMembership::getStartDate).reversed());
        return membershipList.get(0);
    }


    @Override
    public RMembership renewMembership(Long membershipId, Duration duration, TypeMembership typeMembership) {
        RMembership rMembership = rmr.findById(membershipId).orElse(null);

        LocalDateTime today = LocalDateTime.now();

        if (rMembership.getEndDate().isBefore(today)) {
            throw new IllegalStateException("Cannot renew an expired membership");
        }


        rMembership.setTypeMembership(typeMembership);
        rMembership.setDuration(duration);
        rMembership.setStartDate(today);
        if (duration == Duration.DAY) {
            rMembership.setEndDate(today.plusDays(1));
            rMembership.getUser().getFidelityCard().setTotalPoints(rMembership.getUser().getFidelityCard().getTotalPoints()+10);
            FidelityTransaction fidelityTransaction = new FidelityTransaction();
            fidelityTransaction.setTransactionType(TransactionType.EARN_POINTS);
            fidelityTransaction.setPoints(10);
            fidelityTransaction.setFidelityCard(rMembership.getUser().getFidelityCard());
            fidelityTransaction.setTransactionDate(LocalDateTime.now());


            rMembership.getUser().getFidelityCard().getTransactions().add(fidelityTransaction);
        }
        if (duration == Duration.MONTH) {
            rMembership.setEndDate(today.plusMonths(1));
            FidelityTransaction fidelityTransaction = new FidelityTransaction();
            fidelityTransaction.setTransactionType(TransactionType.EARN_POINTS);
            fidelityTransaction.setPoints(40);
            fidelityTransaction.setFidelityCard(rMembership.getUser().getFidelityCard());
            fidelityTransaction.setTransactionDate(LocalDateTime.now());


            rMembership.getUser().getFidelityCard().getTransactions().add(fidelityTransaction);
        }
        if (duration == Duration.SEMESTER) {
            rMembership.setEndDate(today.plusMonths(6));
            FidelityTransaction fidelityTransaction = new FidelityTransaction();
            fidelityTransaction.setTransactionType(TransactionType.EARN_POINTS);
            fidelityTransaction.setPoints(70);
            fidelityTransaction.setFidelityCard(rMembership.getUser().getFidelityCard());
            fidelityTransaction.setTransactionDate(LocalDateTime.now());


            rMembership.getUser().getFidelityCard().getTransactions().add(fidelityTransaction);
        }
        if (duration == Duration.YEAR) {
            rMembership.setEndDate(today.plusYears(1));
            FidelityTransaction fidelityTransaction = new FidelityTransaction();
            fidelityTransaction.setTransactionType(TransactionType.EARN_POINTS);
            fidelityTransaction.setPoints(130);
            fidelityTransaction.setFidelityCard(rMembership.getUser().getFidelityCard());
            fidelityTransaction.setTransactionDate(LocalDateTime.now());


            rMembership.getUser().getFidelityCard().getTransactions().add(fidelityTransaction);
        }

            rMembership.setValidated(false);
            rMembership.setPrice(br.getTotalCostWhere(typeMembership, duration, rMembership.getUser().getTypeUser()));

            rMembership.setRenewalCount(rMembership.getRenewalCount() + 1);
            rMembership.setHasRenewed(true);

        rmr.save(rMembership);

        RUserTransaction rUserTransaction = new RUserTransaction();
        rUserTransaction.setTransactionDate(rMembership.getStartDate());
        rUserTransaction.setTransactionAmount(rMembership.getPrice());
        rUserTransaction.setUserId(rMembership.getUser().getIdUser());
        utr.save(rUserTransaction);


            return rMembership;
        }



    @Override
    public double calculateRenewalRate(LocalDateTime startDate, LocalDateTime endDate) {
        List<RMembership> rMembershipList = rmr.findRMembershipBetweenTwoDates(startDate, endDate);
        int totalRenewed = 0;
        int totalMemberships = rMembershipList.size();

        for (RMembership rMembership : rMembershipList) {
            if (rMembership.isHasRenewed()) {
                totalRenewed=totalRenewed+rMembership.getRenewalCount(); //totalRenewed++
                if (rMembership.getRenewalCount() > 5) {
                    double discountPercentage = 10.0; // 10% discount
                    double discountedPrice = rMembership.getPrice() * (1 - discountPercentage / 100.0);
                   rMembership.setPrice((float) discountedPrice);
                   rmr.save(rMembership);
                } else {
                    log.info("Reduction for the Membership of :" + rMembership.getUser().getLastName() + rMembership.getUser().getFirstName());
                }
            }
        }

        if (totalMemberships == 0) {
            return 0.0;
        } else {
            return (double) totalRenewed / totalMemberships * 100.0;
        }
    }


    @Override
    public Map<Long, Double>  getRevenuePerUSER() {
        List<RUserTransaction> transactions = utr.findAll();
        Map<Long, Double> revenuePerUSER = new HashMap<>();

        for (RUserTransaction transaction : transactions) {
            Long userId = transaction.getUserId();
            double transactionAmount = transaction.getTransactionAmount();
            if (revenuePerUSER.containsKey(userId)) {
                revenuePerUSER.put(userId, revenuePerUSER.get(userId) + transactionAmount);
            } else {
                revenuePerUSER.put(userId, transactionAmount);
            }
        }
        return revenuePerUSER;
    }

    @Override
    public Map<TypeUser, Double> getRevenuePerROLE() {
        List<User> studentList = ur.getAllByTypeUser(STUDENT);
        List<User> teacherList = ur.getAllByTypeUser(TEACHER);
        List<User> guesttList = ur.getAllByTypeUser(GUEST);
        Map<TypeUser, Double> revenuePerRole = new HashMap<>();
        List<RUserTransaction> transactionsFirst = utr.findAll();

        Double vide = 0.0;

        for (RUserTransaction transaction : transactionsFirst) {
            for (User user : studentList) {
                    if (user.getIdUser().equals(transaction.getUserId())) {
                        double transactionAmount = transaction.getTransactionAmount();
                        log.info("" + transactionAmount);
                        if (revenuePerRole.containsKey(STUDENT)) {
                            revenuePerRole.put(STUDENT, revenuePerRole.get(STUDENT) + transactionAmount);
                        }else revenuePerRole.put(STUDENT, transactionAmount);
                    }
            }

            for (User user : teacherList) {
                if (user.getIdUser().equals(transaction.getUserId())) {
                    double transactionAmount = transaction.getTransactionAmount();
                    log.info("" + transactionAmount);
                    if (revenuePerRole.containsKey(TEACHER)) {
                        revenuePerRole.put(TEACHER, revenuePerRole.get(TEACHER) + transactionAmount);
                    }else revenuePerRole.put(TEACHER, transactionAmount);
                }
            }


            for (User user : guesttList) {
                if (user.getIdUser().equals(transaction.getUserId())) {
                    double transactionAmount = transaction.getTransactionAmount();
                    log.info("" + transactionAmount);
                    if (revenuePerRole.containsKey(GUEST)) {
                        revenuePerRole.put(GUEST, revenuePerRole.get(GUEST) + transactionAmount);
                    }else revenuePerRole.put(GUEST, transactionAmount);
                }
            }
        }

        if (guesttList.isEmpty()){
            revenuePerRole.put(GUEST,vide);
        }


        return revenuePerRole;
    }

    @Override
    public int getRevenueTOTAL() {
        List<RUserTransaction> transactions = utr.findAll();
          int total = 0;
        for (RUserTransaction transaction : transactions) {
            double transactionAmount = transaction.getTransactionAmount();
          total+=transactionAmount;

        }
        return total;
    }


}
