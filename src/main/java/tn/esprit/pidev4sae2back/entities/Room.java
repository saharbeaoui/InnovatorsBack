package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRoom", nullable = false)
    private Long idRoom;

    @Column(name = "bed_nbr")
    private Integer bedNbr;

    @Column(name = "archived", nullable = false)
    private boolean archived;

    @JsonIgnore
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FMembership> fMemberships = new LinkedHashSet<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "block_foyer_id_block")
    private BlockFoyer blockFoyer;


    public void addFMembership(String s) {
    }
    public boolean isEmpty(){

        return fMemberships.isEmpty();
    }
    public boolean isFull(){

        return fMemberships.size() ==bedNbr;
    }
    public boolean TakenNotFull(){

        return !isFull()&&!isEmpty();
    }
}