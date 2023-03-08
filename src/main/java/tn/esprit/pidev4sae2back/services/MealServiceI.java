package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Meal;
import tn.esprit.pidev4sae2back.entities.NutritionInformation;

import java.util.List;

public interface MealServiceI {
    List<Meal> retrieveAllMeals();

    Meal addMeal (Meal m);

    Meal updateMeal (Meal m);

    Meal retrieveMeal(Long idMeal);

    void removeMeal(Long idMeal);
    public void assignMealtoMenu (Long idMeal, Long idMenu) ;

    public void updateNutritionInformation(Long idMeal, NutritionInformation nutritionInformation);
    //public List<Meal> getMenuForToday();
}
