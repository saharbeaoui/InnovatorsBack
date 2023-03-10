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
@Table(name = "reward")
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReward", nullable = false)
    private Long idReward;

    @Column(name = "name_reward")
    private String nameReward;

    @Column(name = "description")
    private String description;

    @Column(name = "point_value", nullable = false)
    private int pointValue;

    @Column(name = "quantity_available", nullable = false)
    private int quantityAvailable;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fidelity_card_id")
    private FidelityCard fidelityCard;

}