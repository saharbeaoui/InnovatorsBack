package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.*;
import tn.esprit.pidev4sae2back.repositories.MealRepository;
import tn.esprit.pidev4sae2back.repositories.MenuRepository;
import tn.esprit.pidev4sae2back.repositories.NutritionInformationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class MealServiceImp implements MealServiceI{
    MealRepository mealRepository;
    MenuRepository menuRepository;
    NutritionInformationRepository nutritionInformationRepository;
    @Override
    public List<Meal> retrieveAllMeals() {
        return mealRepository.findAll() ;
    }

    @Override
    public Meal addMeal(Meal m) {

        return mealRepository.save(m);
    }

    @Override
    public Meal updateMeal(Meal m) {

        return mealRepository.save(m);
    }

    @Override
    public Meal retrieveMeal(Long idMeal) {

        return mealRepository.findById(idMeal).get();
    }

    @Override
    public void removeMeal(Long idMeal) {
        mealRepository.deleteById(idMeal);

    }

    @Override
    public void assignMealtoMenu(Long idMeal , Long idMenu) {
        Meal meal = mealRepository.findById(idMeal).orElse(null);
        Menu menu = menuRepository.findById(idMenu).orElse(null);
        meal.setMenu(menu);
        menu.getMeals().add(meal);
        menuRepository.save(menu);
        mealRepository.save(meal);



    }

    @Override
    public void updateNutritionInformation(Long idMeal, NutritionInformation nutritionInformation) {
        Meal meal = mealRepository.findById(idMeal).orElse(null);
        NutritionInformation existingNutritionInformation = meal.getNutritionInformation();
        if (existingNutritionInformation !=null){
            nutritionInformation.setIdNut(existingNutritionInformation.getIdNut());
        }
        nutritionInformationRepository.save(nutritionInformation);
        meal.setNutritionInformation(nutritionInformation);
        mealRepository.save(meal);

    }

}
