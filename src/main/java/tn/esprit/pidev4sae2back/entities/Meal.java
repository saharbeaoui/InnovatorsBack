package tn.esprit.pidev4sae2back.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMeal", nullable = false)
    private Long idMeal;

    @Column(name = "name_meal")
    private String nameMeal;

    @Column(name = "description")
    private String description; //Text

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id_menu")
    private Menu menu;

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}