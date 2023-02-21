package tn.esprit.pidev4sae2back.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "FidelityCard")
public class FidelityCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFidelityCard", nullable = false)
    private Long idFidelityCard;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_membership")
    private TypeMembership typeMembership;

    @Column(name = "total_points", nullable = false)
    private int totalPoints;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "fidelityCard")
    private List<FidelityTransaction> transactions;

    @OneToMany(mappedBy = "fidelityCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reward> rewards = new LinkedHashSet<>();

}