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
@Table(name = "claim")
public class Claim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClaim", nullable = false)
    private Long idClaim;

    @Enumerated(EnumType.STRING)
    @Column(name = "about_claim")
    private AboutClaim aboutClaim;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description; //TEXT

    @Column(name = "date_claim")
    private Timestamp dateClaim;

    @Column(name = "solved", nullable = false)
    private boolean solved;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id_user")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "admin_id_user")
    private User admin;

}