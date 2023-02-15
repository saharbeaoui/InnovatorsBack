package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "r_membership")
public class RMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRMembership", nullable = false)
    private Long idRMembership;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_membership")
    private TypeMembership typeMembership;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration")
    private Duration duration;

    @Column(name = "price", nullable = false)
    private float price;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id_user")
    private User user;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id_restau")
    private Restaurant restaurant;

}