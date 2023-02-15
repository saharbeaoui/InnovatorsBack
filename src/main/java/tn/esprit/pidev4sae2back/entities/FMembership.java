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
@Table(name = "f_membership")
public class FMembership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFMembership", nullable = false)
    private Long idFMembership;

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
    @JoinColumn(name = "room_id_room")
    private Room room;

}