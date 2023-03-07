package tn.esprit.pidev4sae2back.services;
import tn.esprit.pidev4sae2back.entities.*;

import javax.mail.MessagingException;
import java.util.List;

public interface WaitingListServiceI {

    WaitingList addToWaitingList(WaitingList waitingList, Long userId);

    void removeFromWaitingList(Long waitingListId);

    List<WaitingList> getWaitingListByFoyerAndBlock(Long idFoyer, Long idBlock);

    List<WaitingList> getAllWaitingList();

    WaitingList getFirstOfWaitingList();

    void sendFreePlaceEmail(String email) throws MessagingException;

    void notifyFirstUserOnWaitingList() throws MessagingException;

    int countWaitingList();

    void estimateWaitTimes();
}


