package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "thread")
public class Thread extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;



    @Column(name = "description")
    private String description; //TEXT


    @Enumerated(EnumType.STRING)

    CommentType type;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id_user")
    private User user;
    @ManyToOne
    private Thread parent;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "forum_id_forum")
    private Forum forum;

}
