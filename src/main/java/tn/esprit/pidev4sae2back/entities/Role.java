package tn.esprit.pidev4sae2back.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Setter
@Getter
@Entity
@Table(name = "roles")
public class Role {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

  
    
    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private ERole name;

    @JsonIgnore
    @ManyToMany
    List<User> users;
}
