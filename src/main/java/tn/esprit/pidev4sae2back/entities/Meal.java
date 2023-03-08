package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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


    @OneToOne
    @JoinColumn(name = "nutrition_information_id_nut")
    private NutritionInformation nutritionInformation;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id_menu")
    private Menu menu;




}