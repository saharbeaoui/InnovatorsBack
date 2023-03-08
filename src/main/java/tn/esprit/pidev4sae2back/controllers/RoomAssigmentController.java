package tn.esprit.pidev4sae2back.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Room;
import tn.esprit.pidev4sae2back.repositories.FMembershipRepository;
import tn.esprit.pidev4sae2back.services.FMembershipServiceImp;
import tn.esprit.pidev4sae2back.services.RoomAssignmentServiceI;
import tn.esprit.pidev4sae2back.services.RoomAssignmentServiceImp;
import tn.esprit.pidev4sae2back.services.RoomServiceImp;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/assign")
public class RoomAssigmentController {
    @Autowired
    RoomAssignmentServiceImp roomAssignmentServiceImp;
    @Autowired
    RoomServiceImp roomServiceImp;
    @Autowired
    FMembershipServiceImp fMembershipServiceImp;
    @PostMapping("/rooms/bestmatch/{idFMembership}")
    public Room BestMatch(@PathVariable Long idFMembership) {
       Room r= roomAssignmentServiceImp.bestMatch(fMembershipServiceImp.retrieveFMembership(idFMembership));
       roomServiceImp.assignMembershiptoRoom(r.getIdRoom(),idFMembership);
        return roomAssignmentServiceImp.bestMatch(fMembershipServiceImp.retrieveFMembership(idFMembership));
    }

  /*  public List<Room> assignRooms(@RequestBody Map<String, List<String>> hobbyGroups) {
        return roomAssignmentServiceImp.assignRooms(hobbyGroups);
    }
*/
}
