package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Room;

import java.util.List;

public interface RoomServiceI {
    List<Room> retrieveAllRooms();

    Room addRoom(Room r);

    Room updateRoom(Room r);

    Room retrieveRoom(Long idRoom);

    void deleteRoom(Long idRoom);
}
