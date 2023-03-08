package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "block_restau")
public class BlockRestau {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBlock", nullable = false)
    private Long idBlock;

    @Enumerated(EnumType.STRING)
    @Column(name = "name_block")
    private RNameBlock nameBlock;

    @Column(name = "table_nbr")
    private Integer tableNbr;

    @Column(name = "placement")
    private String placement;

    @Column(name = "archived")
    private boolean archived;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id_restau")
    private Restaurant restaurant;

    public void archiveBlock() {
        this.archived = true;
    }

    public void unarchiveBlock() {
        this.archived = false;
    }

}