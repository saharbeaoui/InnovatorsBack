package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMenu", nullable = false)
    private Long idMenu;

    @Column(name = "price_menu", nullable = false)
    private float priceMenu;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_menu")
    private TypeMenu typeMenu;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_menu")
    private Date dateMenu;


    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id_restau")
    private Restaurant restaurant;


    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Meal> meals = new LinkedHashSet<>();

}