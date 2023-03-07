package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_id_room")
    private Room room;


    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id_user")
    private User user;


    @Enumerated(EnumType.STRING)
    @Column(name = "hobbie1")
    private Hobbies hobbie1;

    @Enumerated(EnumType.STRING)
    @Column(name = "hobbie2")
    private Hobbies hobbie2;

    public List<Hobbies> getHobbies() {
        ArrayList<Hobbies> hobbies = new ArrayList<>();
        if(hobbie1!=null)
            hobbies.add(hobbie1);
        if(hobbie2!=null)
            hobbies.add(hobbie2);

        return hobbies;}

}