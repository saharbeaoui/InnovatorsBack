package tn.esprit.pidev4sae2back.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.pidev4sae2back.entities.FNameBlock;
import tn.esprit.pidev4sae2back.entities.WaitingList;

import java.util.List;


@Repository
public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {
    WaitingList findFirstByOrderByCreatedDateAsc();

    @Query(" select w from WaitingList w " +
            " order by w.createdDate ASC ")
    List<WaitingList> selectAllAsc();

    @Query("select w from WaitingList w where w.fNameBlock = ?1")
    List<WaitingList> findAllByFNameBlock(@PathVariable("fNameBlock") FNameBlock fNameBlock);

    List<WaitingList> findByPriorityLevelGreaterThan(int prLevel);
}
