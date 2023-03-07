package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Room;
import tn.esprit.pidev4sae2back.services.RoomServiceImp;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomServiceImp roomServiceImp;


    //http://localhost:8082/test/room/retrieveAllRooms
    @GetMapping("/retrieveAllRooms")
    public List<Room> retrieveAllRooms() {
        return roomServiceImp.retrieveAllRooms();
    }

    //http://localhost:8082/test/room/addRoom
    @PostMapping("/addRoom")
    public Room addRoom(@RequestBody Room r) {
        return roomServiceImp.addRoom(r);
    }

    //http://localhost:8082/test/room/updateRoom
    @PutMapping("/updateRoom")
    public Room updateRoom(@RequestBody Room r) {
        return roomServiceImp.updateRoom(r);
    }

    //http://localhost:8082/test/room/Room/{idRoom}
    @GetMapping("/Room/{idRoom}")
    public Room retrieveRoom(@PathVariable(value = "idRoom") Long idRoom) {
        return roomServiceImp.retrieveRoom(idRoom);
    }

    //http://localhost:8082/test/room/deleteRoom/{idRoom}
    @DeleteMapping("/deleteRoom/{idRoom}")
    public void deleteRoom(@PathVariable("idRoom") Long idRoom) {
        roomServiceImp.deleteRoom(idRoom);
    }

    @PutMapping("/assignMembershiptoRoom/{id-room}/{idFMembership}")
    @ResponseBody
    public void assignMembershiptoRoom(@PathVariable("id-room") Long idRoom, @PathVariable("idFMembership") Long idFmembership) {
        roomServiceImp.assignMembershiptoRoom(idRoom, idFmembership);

    }
}
