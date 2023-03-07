package tn.esprit.pidev4sae2back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.entities.Hobbies;
import tn.esprit.pidev4sae2back.entities.Room;
import tn.esprit.pidev4sae2back.repositories.FMembershipRepository;
import tn.esprit.pidev4sae2back.repositories.RoomRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
@Slf4j
@Service


public class RoomAssignmentServiceImp {
@Autowired
    FMembershipRepository fMembershipRepository;
@Autowired
    RoomRepository roomRepository;
    private Map<String, List<String>> hobbyGroups;

    public Room bestMatch(FMembership  fMembership){
        List<Room> rooms = roomRepository.findAll();
        HashMap<Room,Integer> MatchedHobbies = new HashMap<Room,Integer>();
        for (Room r:rooms
             ) {
            if(r.TakenNotFull()){
                for (FMembership mb:
                     r.getFMemberships()) {

                    for (Hobbies hobbies:mb.getHobbies()
                         ) {
                        for (Hobbies h:fMembership.getHobbies()
                             ) {

                            if (h.equals(hobbies)) {
                                System.out.println(h.toString());
                                //ken mawjouda bch nzidoha 1 ken mch mawjouda bch nzidoha room w naatiwha valeur 1
                                if(MatchedHobbies.containsKey(r))
                                {
                                MatchedHobbies.replace(r,MatchedHobbies.get(r)+1);
                                } else MatchedHobbies.put(r,1);
                            }
                        }
                    }

                }

            }
            //ken malkina hata room matched bch nraj3oulou room jdida fergha
        }   if(MatchedHobbies.isEmpty()){ return rooms.stream().filter(Room::isEmpty).findFirst().get();  }
//          l'algorithme return max
            else {
                //initialisation bch nodkhlou nparkouriw
              Set<Room> matchedrooms = MatchedHobbies.keySet() ;
                Room bestRoom=matchedrooms.stream().findFirst().get();
                //comparaison bin rooms wnchoufou anehi akther wahda matchedhobbies
            for (Room r:matchedrooms
                 ) {
                if (MatchedHobbies.get(r)>MatchedHobbies.get(bestRoom)){ bestRoom =r;}
            }
                return bestRoom; }

    }



  /*  public List<Room> assignRooms(Map<String, List<String>>hobbyGroups) {
        this.hobbyGroups = hobbyGroups;
        Map<Integer, Room> rooms = new HashMap<>();
        AtomicInteger bedNbr = new AtomicInteger(1);

        for (List<String> FMembership : hobbyGroups.values()) {
            Collections.sort(FMembership);
            for (int i = 0; i < FMembership.size(); i += 2) {
                Room room = rooms.computeIfAbsent(bedNbr.get(), k -> new Room());
                room.addFMembership(FMembership.get(i));
                if (i + 1 < FMembership.size()) {
                    room.addFMembership(FMembership.get(i + 1));
                }
                bedNbr.incrementAndGet();
            }
        }
        return new ArrayList<>(rooms.values());
    }*/
    public FMembership AssignRooms(FMembership fMembership){

      return fMembershipRepository.save(fMembership);

    }
    }
