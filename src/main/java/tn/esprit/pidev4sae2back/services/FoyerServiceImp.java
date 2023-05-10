package tn.esprit.pidev4sae2back.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.FoyerRepository;

import java.util.*;
@Service
@Slf4j
public class FoyerServiceImp implements FoyerServiceI {
    @Autowired
    FoyerRepository fr;


    @Override
    public Foyer addFoyer(Foyer foyer){
        return fr.save(foyer);
    }



    @Override
    public Foyer getFoyer(Long idFoyer) {
        return fr.findById(idFoyer).orElse(null);
    }
    @Override
    public List<Foyer> getAllFoyers() {
        return fr.findAll();
    }
    @Override
    public Foyer updateFoyer(Foyer foyer) {
        return fr.save(foyer);
    }

    @Override
    public void removeFoyer(Long idFoyer) {
        fr.deleteById(idFoyer);
    }

    @Override
    public Integer nbPlaceDisponiblePERBLOC(Long idFoyer, Long idBlock){
        int nbLit = 0;
        Foyer f = fr.findById(idFoyer).orElse(null);
        assert f != null;
        Set<BlockFoyer> blockFoyers = f.getBlockFoyers();
        for (BlockFoyer blockFoyer1 : blockFoyers) {
            if (blockFoyer1.getIdBlock().equals(idBlock)) {
                Set<Room> rooms = blockFoyer1.getRooms();
                for (Room room : rooms) {
                    nbLit += room.getBedNbr();
                }
            }
        }
        return nbLit;
    }

    @Override
    public Boolean isFullFoyer(Long idFoyer) {
        Integer nb = 0;
        Foyer f = fr.findById(idFoyer).orElse(null);
        if (f != null) {
            Set<BlockFoyer> blockFoyers = f.getBlockFoyers();
            if (!blockFoyers.isEmpty()) {
                for (BlockFoyer blockFoyer : blockFoyers) {
                    nb += nbPlaceDisponiblePERBLOC(idFoyer, blockFoyer.getIdBlock());
                    if (nb < 0) {
                        log.info("Foyer IS FULL");
                        return true;
                    }
                }
                log.info("Place Available: " + nb);
                return false;
            } else {
                log.info("Foyer without Blocs");
            }
        }
        return null;
    }

    @Scheduled(fixedRate = 3000)
    public void checkFoyerFullness() {
        Long idFoyer = 1L;
        Foyer f = fr.findById(idFoyer).orElse(null);
        Integer nb = 0;
        if (f != null) {
            Set<BlockFoyer> blockFoyers = f.getBlockFoyers();
            if (!blockFoyers.isEmpty()) {
                for (BlockFoyer blockFoyer : blockFoyers) {
                    nb += nbPlaceDisponiblePERBLOC(idFoyer, blockFoyer.getIdBlock());
                    f.setCapacity(nb);
                    fr.save(f);
                    if (nb < 0) {
                        log.info("Foyer IS FULL");
                    }
                }
                log.info("Place Available: " + nb);
            } else {
                log.info("Foyer without Blocs");
            }
        }
    }



    @Override
    public List<FMembership> searchMembershipByDuration(Duration duration){
        return fr.findByDuration(duration);
    }

    @Override
    public List<Foyer> searchFoyers(String name, Integer capacity) {
        if (name != null && capacity != null) {
            // Search by both name and capacity
            return fr.findByNameContainingAndCapacity(name, capacity);
        } else if (name != null) {
            // Search by name only
            return fr.findByNameContaining(name);
        } else if (capacity != null) {
            // Search by capacity only
            return fr.findByCapacity(capacity);
        } else {
            // Return empty list if no search parameters are provided
            return new ArrayList<Foyer>();
        }
    }









    /*public void handleWaitingListRequest(User user, FNameBlock block) {
        waitingListService.addToWaitingList(user, block);
    }

     */
    @Override

   public void handleFMembershipRequest(User user) {
// Find all foyers with non-null capacity
            List<Foyer> availableFoyers = fr.findByCapacityNotNull();
        for (Foyer foyer : availableFoyers) {
            // Find all non-full blocks in foyer
            Set<BlockFoyer> nonFullBlocks = new HashSet<>();
            for (BlockFoyer blockFoyer : foyer.getBlockFoyers()) {
                if (blockFoyer.getRooms().size() < blockFoyer.getRoomNbr()) {
                    nonFullBlocks.add(blockFoyer);
                }
            }
            // If there is at least one non-full block in the foyer
            if (!nonFullBlocks.isEmpty()) {
                // Sort non-full blocks by number of available rooms
                List<BlockFoyer> sortedBlocks = new ArrayList<>(nonFullBlocks);
                sortedBlocks.sort(Comparator.comparingInt(bf -> bf.getRoomNbr() - bf.getRooms().size()));
                // Try to assign user to each non-full block, in order
                for (BlockFoyer blockFoyer : sortedBlocks) {
                    // Find all non-full rooms in block
                    Set<Room> nonFullRooms = new HashSet<>();

                    for (Room room : blockFoyer.getRooms()) {
                        if (!room.isArchived() && room.getFMemberships().size() < room.getBedNbr()) {
                            nonFullRooms.add(room);
                        }
                    }
                    // If there is at least one non-full room in the block
                   /* if (!nonFullRooms.isEmpty()) {
                        // Sort non-full rooms by number of available beds
                        List<Room> sortedRooms = new ArrayList<>(nonFullRooms);
                        sortedRooms.sort(Comparator.comparingInt(r -> r.getBedNbr() - r.getFMemberships().size()));




                        // Try to create FMembership for user in each non-full room, in order
                        for (Room room : sortedRooms) {
                            FMembership fMembership = new FMembership();
                            fMembership.setUser(user);
                            fMembership.setRoom(room);
                            fMembership.setStartDate(new Timestamp(System.currentTimeMillis()));
                            fMembership.setDuration(Duration.ONE_MONTH);
                            fMembership.setPrice(room.getFoyer().getPrice());
                            if (user.getFMemberships().add(fMembership) && room.getFMemberships().add(fMembership)) {
                                fr.save(foyer);
                                return;
                            }
                        }*/
                    }
                }
            }}}

