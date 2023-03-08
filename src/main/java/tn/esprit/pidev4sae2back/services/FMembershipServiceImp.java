package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.FMembershipRepository;
import tn.esprit.pidev4sae2back.repositories.FoyerRepository;
import tn.esprit.pidev4sae2back.repositories.RoomRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;


import java.util.List;
import java.util.Set;

@Slf4j
@Service

@AllArgsConstructor
public class FMembershipServiceImp implements FMembershipServiceI {
    @Autowired
    FMembershipRepository fMembershipRepository;
    FoyerRepository fr;
    RoomRepository roomRepository;
    UserRepository userRepository;
    FoyerServiceI foyerServiceI;
    WaitingListServiceI waitingListServiceI;


    @Override
    public List<FMembership> retrieveAllFMembership() {
        return fMembershipRepository.findAll();
    }

    @Override

    public FMembership addFMembership(FMembership fm, Long idUser, Long idFoyer) {
        FNameBlock fNameBlock = null;
        Foyer foyer = fr.findById(idFoyer).orElse(null);
        User u = userRepository.findById(idUser).orElse(null);
        Set<BlockFoyer> blockFoyerList = foyer.getBlockFoyers();
        WaitingList waitingList = new WaitingList();

        if (u.getSex() == Sex.FEMALE) {
            switch (u.getTypeUser()) {
                case STUDENT:
                    fNameBlock = FNameBlock.FSTUDENTS;
                    for (BlockFoyer blockFoyer : blockFoyerList) {
                        if (blockFoyer.getNameBlock().equals(fNameBlock)) {
                            if (foyerServiceI.nbPlaceDisponiblePERBLOC(idFoyer, blockFoyer.getIdBlock()) > 1) {
                                Set<Room> rooms = blockFoyer.getRooms();
                                for (Room room1 : rooms) {
                                    if (room1.getBedNbr() > 1) {
                                        fm.setUser(u);
                                        fm.setRoom(room1);
                                        room1.setBedNbr(room1.getBedNbr() - 1);
                                        fMembershipRepository.save(fm);
                                        roomRepository.save(room1);
                                    }
                                }

                            }
                        } else {
                            waitingList.setFNameBlock(fNameBlock);
                            waitingList.setCreatedDate(fm.getStartDate());
                            waitingListServiceI.addToWaitingList(waitingList, u.getIdUser());
                        }
                    }
                    break;
                case TEACHER:
                    fNameBlock = FNameBlock.FTEACHERS;
                    for (BlockFoyer blockFoyer : blockFoyerList) {
                        if (blockFoyer.getNameBlock().equals(fNameBlock)) {
                            if (foyerServiceI.nbPlaceDisponiblePERBLOC(idFoyer, blockFoyer.getIdBlock()) > 1) {
                                Set<Room> rooms = blockFoyer.getRooms();
                                for (Room room1 : rooms) {
                                    if (room1.getBedNbr() > 1) {
                                        fm.setUser(u);
                                        fm.setRoom(room1);
                                        room1.setBedNbr(room1.getBedNbr() - 1);
                                        fMembershipRepository.save(fm);
                                        roomRepository.save(room1);
                                    }
                                }

                            }
                        } else {
                            waitingList.setFNameBlock(fNameBlock);
                            waitingList.setCreatedDate(fm.getStartDate());
                            waitingListServiceI.addToWaitingList(waitingList, u.getIdUser());
                        }
                    }
                    break;
                case GUEST:
                    fNameBlock = FNameBlock.GUESTS;
                    for (BlockFoyer blockFoyer : blockFoyerList) {
                        if (blockFoyer.getNameBlock().equals(fNameBlock)) {
                            if (foyerServiceI.nbPlaceDisponiblePERBLOC(idFoyer, blockFoyer.getIdBlock()) > 1) {
                                Set<Room> rooms = blockFoyer.getRooms();
                                for (Room room1 : rooms) {
                                    if (room1.getBedNbr() > 1) {
                                        fm.setUser(u);
                                        fm.setRoom(room1);
                                        room1.setBedNbr(room1.getBedNbr() - 1);
                                        fMembershipRepository.save(fm);
                                        roomRepository.save(room1);
                                    }
                                }

                            }
                        } else {
                            waitingList.setFNameBlock(fNameBlock);
                            waitingList.setCreatedDate(fm.getStartDate());
                            waitingListServiceI.addToWaitingList(waitingList, u.getIdUser());
                        }
                    }
                    break;
            }
        } else {
            switch (u.getTypeUser()) {
                case STUDENT:
                    fNameBlock = FNameBlock.MSTUDENTS;
                    for (BlockFoyer blockFoyer : blockFoyerList) {
                        if (blockFoyer.getNameBlock().equals(fNameBlock)) {
                            if (foyerServiceI.nbPlaceDisponiblePERBLOC(idFoyer, blockFoyer.getIdBlock()) > 1) {
                                Set<Room> rooms = blockFoyer.getRooms();
                                for (Room room1 : rooms) {
                                    if (room1.getBedNbr() > 1) {
                                        fm.setUser(u);
                                        fm.setRoom(room1);
                                        room1.setBedNbr(room1.getBedNbr() - 1);
                                        fMembershipRepository.save(fm);
                                        roomRepository.save(room1);
                                    }
                                }

                            }
                        } else {
                            waitingList.setFNameBlock(fNameBlock);
                            waitingList.setCreatedDate(fm.getStartDate());
                            waitingListServiceI.addToWaitingList(waitingList, u.getIdUser());
                        }
                    }
                    break;
                case TEACHER:
                    fNameBlock = FNameBlock.MTEACHERS;
                    for (BlockFoyer blockFoyer : blockFoyerList) {
                        if (blockFoyer.getNameBlock().equals(fNameBlock)) {
                            if (foyerServiceI.nbPlaceDisponiblePERBLOC(idFoyer, blockFoyer.getIdBlock()) > 1) {
                                Set<Room> rooms = blockFoyer.getRooms();
                                for (Room room1 : rooms) {
                                    if (room1.getBedNbr() > 1) {
                                        fm.setUser(u);
                                        fm.setRoom(room1);
                                        room1.setBedNbr(room1.getBedNbr() - 1);
                                        fMembershipRepository.save(fm);
                                        roomRepository.save(room1);
                                    }
                                }

                            }
                        } else {
                            waitingList.setFNameBlock(fNameBlock);
                            waitingList.setCreatedDate(fm.getStartDate());
                            waitingListServiceI.addToWaitingList(waitingList, u.getIdUser());
                        }
                    }
                    break;
                case GUEST:
                    fNameBlock = FNameBlock.GUESTS;
                    for (BlockFoyer blockFoyer : blockFoyerList) {
                        if (blockFoyer.getNameBlock().equals(fNameBlock)) {
                            if (foyerServiceI.nbPlaceDisponiblePERBLOC(idFoyer, blockFoyer.getIdBlock()) > 1) {
                                Set<Room> rooms = blockFoyer.getRooms();
                                for (Room room1 : rooms) {
                                    if (room1.getBedNbr() > 1) {
                                        fm.setUser(u);
                                        fm.setRoom(room1);
                                        room1.setBedNbr(room1.getBedNbr() - 1);
                                        fMembershipRepository.save(fm);
                                        roomRepository.save(room1);
                                    }
                                }

                            }
                        } else {
                            waitingList.setFNameBlock(fNameBlock);
                            waitingList.setCreatedDate(fm.getStartDate());
                            waitingListServiceI.addToWaitingList(waitingList, u.getIdUser());
                        }
                    }
                    break;
            }

        }
        return fm;
    }

    public FMembership addFMembership(FMembership fm) {
        return fMembershipRepository.save(fm);

    }

    @Override
    public FMembership updateFMembership(FMembership fm) {
        return fMembershipRepository.save(fm);
    }

    @Override
    public FMembership retrieveFMembership(Long idFMembership) {
        return fMembershipRepository.findById(idFMembership).orElse(null);
    }

    @Override
    public void deleteFMembership(Long idFMembership) {

        FMembership membership = fMembershipRepository.findById(idFMembership).orElse(null);
        assert membership != null;
        membership.setHobbie1(null);
        membership.setHobbie2(null);
        membership.setRoom(null);
        membership.setUser(null);
        fMembershipRepository.save(membership);
        fMembershipRepository.delete(membership);
    }

    @Override
    public void freeBedAndRoomAfterMembershipEnds(Long idFMembership) {
        FMembership membership = fMembershipRepository.findById(idFMembership).orElse(null);
        if (membership != null) {
            Room room = membership.getRoom();
            room.setBedNbr(room.getBedNbr()+1);
                roomRepository.save(room);
            deleteFMembership(idFMembership);
            }
    }

    @Override
    public FMembership F_MEMBERSHIPStatistics(Long idFMembership) {
        return null;
    }
/*public FMembership F_MEMBERSHIPStatistics(FMembership fMembership){
    public Map<String, Object> getMemberStatistics(FMembership)
        List<FMembership> fMembership = fMembershipRepository.; // getAllFMembers Récupère tous les membres
        LocalDate currentDate = LocalDate.now(); // Obtient la date actuelle
        Map<String, Object> statistics = new HashMap<>(); // Crée un dictionnaire pour stocker les statistiques

        int totalMembers = fMembership.size(); // Nombre total de membres
        statistics.put("totalMembers", totalMembers);

        int membersStayLessThan2Years = 0; // Nombre de membres qui restent moins de 2 ans dans le même foyer
        for (Member member : members) {
            LocalDate joinDate = member.getJoinDate(); // Obtient la date d'adhésion du membre
            Period period = Period.between(joinDate, currentDate); // Calcule la durée d'adhésion du membre
            int years = period.getYears();
            if (years < 2) { // Vérifie si la durée d'adhésion est inférieure à 2 ans
                membersStayLessThan2Years++;
            }
        }
        statistics.put("membersStayLessThan2Years", membersStayLessThan2Years);

        double percentStayLessThan2Years = (double) membersStayLessThan2Years / totalMembers * 100; // Pourcentage de membres qui restent moins de 2 ans dans le même foyer
        statistics.put("percentStayLessThan2Years", percentStayLessThan2Years);

        return statistics; // Retourne les statistiques
    }*/

}
