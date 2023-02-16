package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Meal;

import java.util.List;

public interface MealServiceI {
    List<Meal> retrieveAllMeals();

    Meal addMeal (Meal m);

    Meal updateMeal (Meal m);

    Meal retrieveMeal(Long idMeal);

    void removeMeal(Long idMeal);
}
