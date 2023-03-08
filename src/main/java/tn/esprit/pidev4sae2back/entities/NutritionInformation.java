package tn.esprit.pidev4sae2back.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "nutrition_info")
public class NutritionInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNut", nullable = false)
    private Long idNut;
    private int calories;
    private int protein;
    private int carbohydrates;
    private int fat;

    @JsonIgnore
    @OneToOne(mappedBy = "nutritionInformation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Meal meal;


}
