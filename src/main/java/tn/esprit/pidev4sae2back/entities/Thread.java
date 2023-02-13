package tn.esprit.pidev4sae2back.entities;

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
@Table(name = "thread")
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idThread", nullable = false)
    private Long idThread;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description; //TEXT

    @Column(name = "date")
    private Timestamp date;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forum_id_forum")
    private Forum forum;

}