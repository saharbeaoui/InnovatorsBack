package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.entities.Room;

import java.util.List;

public interface RoomAssignmentServiceI {
    List<Room> retrieveAllRooms();

    Room addRoomAssignment(Room r);

    Room updateRoomAssignment(Room r);

    Room retrieveRoomAssignment(Long idRoom);

    void deleteRoomAssignment(Long idRoom);

    Room bestMatch(FMembership fMembership);

}
