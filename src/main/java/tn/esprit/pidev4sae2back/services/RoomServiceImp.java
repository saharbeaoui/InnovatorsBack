package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.entities.Room;
import tn.esprit.pidev4sae2back.repositories.FMembershipRepository;
import tn.esprit.pidev4sae2back.repositories.RoomRepository;

import java.util.List;
import java.util.Set;

@Service

@AllArgsConstructor
public class RoomServiceImp implements RoomServiceI{

    FMembershipRepository fMembershipRepository;
    RoomRepository roomRepository;


    @Override
    public List<Room> retrieveAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room addRoom(Room r) {
        return roomRepository.save(r);
    }

    @Override
    public Room updateRoom(Room r) {
        return roomRepository.save(r);
    }

    @Override
    public Room retrieveRoom(Long idRoom) {
        return roomRepository.findById((Long)idRoom).orElse(null);
    }

    @Override
    public void deleteRoom(Long idRoom) {
        roomRepository.deleteById(idRoom);}
    @Override
    public void assignMembershiptoRoom(Long idRoom, Long idFMembership) {
        Room room = roomRepository.findById(idRoom).orElse(null);
        FMembership fMembership = fMembershipRepository.findById(idFMembership).orElse(null);
        room.getFMemberships().add(fMembership);
        fMembership.setRoom(room);
        fMembershipRepository.save(fMembership);
        roomRepository.save(room);
   //    fMembershipRepository.save(fMembership);


    }

    @Override
    public List<Room> getEmpty() {
        return null;
    }

    @Override
    public List<Room> getFull() {
        return null;
    }
}
