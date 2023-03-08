package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Table(name = "forum")
public class Forum extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "title")
    private String title;
    @Column(name = "topic")
    private String topic;
    @Column(name = "image")
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Thread> threads = new LinkedHashSet<>();

    @OneToMany(mappedBy = "forum")
    @JsonIgnore
    private Set<Reaction> reactions;
}
