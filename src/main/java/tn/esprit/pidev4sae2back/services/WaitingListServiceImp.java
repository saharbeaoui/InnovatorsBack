package tn.esprit.pidev4sae2back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.BlockFoyerRepository;
import tn.esprit.pidev4sae2back.repositories.FoyerRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;
import tn.esprit.pidev4sae2back.repositories.WaitingListRepository;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WaitingListServiceImp implements WaitingListServiceI {
    @Autowired
    WaitingListRepository wr;
    @Autowired
    UserRepository ur;

    @Autowired
    FoyerRepository fr;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    BlockFoyerRepository blockFoyerRepository;

    @Override
    public WaitingList addToWaitingList(WaitingList waitingList, Long userId) {
        User u= ur.findById(userId).orElse(null);
        waitingList.setUser(u);
        waitingList.setPriorityLevel(countWaitingList()+1);
        return wr.save(waitingList);
    }


    @Override
    public void removeFromWaitingList(Long waitingListId) {
        WaitingList waitingList = wr.findById(waitingListId).orElse(null);
        assert waitingList != null;
        wr.delete(waitingList);
    }

    @Override
    public List<WaitingList> getWaitingListByFoyerAndBlock(Long idFoyer, Long idBlock) {
        List<WaitingList> waitingList = new ArrayList<>();
        BlockFoyer blockFoyer = blockFoyerRepository.findById(idBlock).orElse(null);

        if (blockFoyer != null && blockFoyer.getFoyer().getIdFoyer().equals(idFoyer)) {
            waitingList = wr.findAllByFNameBlock(blockFoyer.getNameBlock());
        }

        return waitingList;
    }


    @Override
    public List<WaitingList> getAllWaitingList() {
        return wr.findAll();
    }

    @Override
    public WaitingList getFirstOfWaitingList() {
        List<WaitingList> waitingList =wr.selectAllAsc();
       return waitingList.get(0);
    }

    @Override
    public void sendFreePlaceEmail(String email) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("nawrez.shili@esprit.tn");
        helper.setTo(email);
        helper.setSubject("Free Place");
        helper.setText("REMAKE ANOTHER MEMBERSHIP !");

        mailSender.send(message);
    }


    @Override
    public void notifyFirstUserOnWaitingList() throws MessagingException {
        WaitingList waitingList =getFirstOfWaitingList();
        sendFreePlaceEmail(waitingList.getUser().getEmail());
    }

    @Override
    public int countWaitingList() {
        List<WaitingList> waitingList = wr.findAll();
        return waitingList.size();
    }


    @Override
    public void estimateWaitTimes() {
        List<WaitingList> waitingList = wr.findAll();
        for (WaitingList item : waitingList) {
            int position = wr.findByPriorityLevelGreaterThan(item.getPriorityLevel()).size();
            int estimatedWaitTime = position * 10;
            log.info("Waiting List number" +item.getId()+ " User : "+item.getUser().getFirstName() + "Position : "+ position+ "EstimatedWaitTime : "+ estimatedWaitTime);
        }
    }
}

