package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Meal;
import tn.esprit.pidev4sae2back.services.MealServiceI;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
public class MealRestcontroller {
    MealServiceI mealServiceI;
    @GetMapping("/retrieveAllMeals")
    @ResponseBody
    public List<Meal> getAllMeals(){
        List<Meal> listmeals = mealServiceI.retrieveAllMeals();
        return listmeals;
    }
    @PostMapping("/addMeal")
    @ResponseBody
    public Meal addMeal(@RequestBody Meal m){
        Meal meal = mealServiceI.addMeal(m);
        return meal;
    }
    @PutMapping("/updateMeal")
    @ResponseBody
    public Meal updateMeal(@RequestBody Meal m){
        Meal meal = mealServiceI.updateMeal(m);
        return  meal;
    }
    @GetMapping("/retrievemeal/{meal-id}")
    @ResponseBody
    public Meal getMeal(@PathParam("meal-id") Long idMeal){
        Meal meal = mealServiceI.retrieveMeal(idMeal);
        return meal;
    }
    @DeleteMapping("/remove-meal/{meal-id}")
    @ResponseBody
    public void removeetudinat(@PathParam("meal-id")Long idMeal){
        mealServiceI.removeMeal(idMeal);
    }
}
