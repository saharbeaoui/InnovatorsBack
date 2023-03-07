package tn.esprit.pidev4sae2back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.pidev4sae2back.entities.RMembership;
import tn.esprit.pidev4sae2back.entities.Restaurant;
import tn.esprit.pidev4sae2back.entities.TypeUser;
import tn.esprit.pidev4sae2back.entities.User;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RMembershipRepository extends JpaRepository<RMembership, Long> {
    List<RMembership> findAllByRestaurantAndUser_TypeUser(Restaurant restau, TypeUser typeUser);

    public List<RMembership> findByValidatedIsTrue();

    public List<RMembership> findByValidatedIsTrueAndStartDateAfterAndEndDateBefore(LocalDateTime startDate, LocalDateTime endDate);

    @Query("select m from RMembership m where   m.startDate>=?1 and m.endDate<=?2 and m.validated=true ")
    public List<RMembership> findRMembershipBetweenTwoDates(LocalDateTime startDate, LocalDateTime endDate);

    public List<RMembership> findAllByStartDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    public List<RMembership> findByUser(User u);

    public List<RMembership> findAllByUserIdUser(Long idUser);
}