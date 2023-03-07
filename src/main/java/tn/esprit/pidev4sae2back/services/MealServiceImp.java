package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Meal;
import tn.esprit.pidev4sae2back.repositories.MealRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MealServiceImp implements MealServiceI{
    MealRepository mealRepository;
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
}
