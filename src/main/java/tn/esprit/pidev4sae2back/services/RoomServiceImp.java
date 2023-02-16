package tn.esprit.pidev4sae2back.services;

import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Room;
import tn.esprit.pidev4sae2back.repositories.RoomRepository;

import java.util.List;

@Service
public class RoomServiceImp implements RoomServiceI{

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
}
