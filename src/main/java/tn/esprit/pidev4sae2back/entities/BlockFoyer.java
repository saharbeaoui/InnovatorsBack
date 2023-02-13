package tn.esprit.pidev4sae2back.entities;

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
@Table(name = "block_foyer")
public class BlockFoyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBlock", nullable = false)
    private Long idBlock;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_block")
    private FNameBlock nameBlock;

    @Column(name = "room_nbr")
    private Integer roomNbr;

    @Column(name = "placement")
    private String placement;

    @OneToMany(mappedBy = "blockFoyer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms = new LinkedHashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "foyer_id_foyer")
    private Foyer foyer;

}