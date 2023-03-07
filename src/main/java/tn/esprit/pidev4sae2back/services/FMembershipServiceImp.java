package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.FMembership;
import tn.esprit.pidev4sae2back.entities.Room;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.FMembershipRepository;
import tn.esprit.pidev4sae2back.repositories.RoomRepository;
import tn.esprit.pidev4sae2back.repositories.UserRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class FMembershipServiceImp implements FMembershipServiceI{
    @Autowired
    FMembershipRepository fMembershipRepository;
    RoomRepository roomRepository;
    UserRepository userRepository;
    @Override
    public List<FMembership> retrieveAllFMembership() {
        return fMembershipRepository.findAll();
    }

    public FMembership addFMembership(FMembership fm, Long idUser) {
     if (
             idUser!=0
     )  {User u=userRepository.findById(idUser).get();
        fm.setUser(u);}
        return fMembershipRepository.save(fm);
    }

    @Override
    public FMembership updateFMembership(FMembership fm) {
        return fMembershipRepository.save(fm);
    }

    @Override
    public FMembership retrieveFMembership(Long idFMembership) {
        return fMembershipRepository.findById((Long) idFMembership).orElse(null);
    }

    @Override
    public void deleteFMembership(Long idFMembership) {
        fMembershipRepository.deleteById( idFMembership);
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
