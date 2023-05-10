package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
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

    @Column(name = "card_number", unique = true , nullable = false)
    private Long cardNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_Level")
    private MembershipLevel membershipLevel;

    @Column(name = "total_points", nullable = false)
    private int totalPoints;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "fidelityCard",cascade = CascadeType.ALL)
    private List<FidelityTransaction> transactions;

    @JsonIgnore
    @OneToMany(mappedBy = "fidelityCard", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reward> rewards = new LinkedHashSet<>();



    public void setCardNumber() {
        Random random = new Random();
        Long newCardNumber = random.nextLong();
        while (newCardNumber.equals(cardNumber)) {
            newCardNumber = random.nextLong();
        }
        cardNumber = newCardNumber;
    }


    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

}