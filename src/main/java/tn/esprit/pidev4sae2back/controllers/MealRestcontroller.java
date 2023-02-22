package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Meal;
import tn.esprit.pidev4sae2back.entities.NutritionInformation;
import tn.esprit.pidev4sae2back.entities.User;
import tn.esprit.pidev4sae2back.repositories.UserRepository;
import tn.esprit.pidev4sae2back.services.MealServiceI;
import tn.esprit.pidev4sae2back.services.NutritionInformationServiceI;
import tn.esprit.pidev4sae2back.utils.MealEmailService;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class MealRestcontroller {
    MealServiceI mealServiceI;
    NutritionInformationServiceI nutritionInformationServiceI;
    MealEmailService mealEmailService;
    UserRepository userRepository;

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
        List<User> users = userRepository.findAll();;
        List<String> recipient = new ArrayList<>();
        for (User user : users){
            recipient.add(user.getEmail());
            mealEmailService.sendMealNotification(recipient, meal.getNameMeal(), meal.getDescription());
        }
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

    @PutMapping("/assignMealtoMenu/{id-meal}/{id-menu}")
    @ResponseBody
    public void assignMealtoMenu(@PathVariable("id-meal") Long idMeal,@PathVariable("id-menu") Long idMenu ){
        mealServiceI.assignMealtoMenu(idMeal,idMenu);
    }
    @PostMapping("/createNutritionInformation /{idMeal}")
    public ResponseEntity<NutritionInformation> createNutritionInformation (@PathVariable Long idMeal, @RequestBody NutritionInformation nutritionInformation) {
        Meal meal = mealServiceI.retrieveMeal(idMeal);
        if (meal == null) {
            return ResponseEntity.notFound().build();
        }
        meal.setNutritionInformation(nutritionInformation);
        nutritionInformation.setMeal(meal);
        nutritionInformationServiceI.saveNutritionInformation(nutritionInformation);
        return ResponseEntity.ok(nutritionInformation);
    }
    @PutMapping("/updateNutritionInformation/{idMeal}")
    @ResponseBody
    public void updateNutritionInformation(@PathVariable Long idMeal, @RequestBody NutritionInformation nutritionInformation){
        mealServiceI.updateNutritionInformation(idMeal,nutritionInformation);


    }
   // @Scheduled(cron = "* * * * * ?")
    //public void sendMenuToUsers(){
      //  List<User> users = userRepository.findAll();;
        //List<Meal> menu = mealServiceI.getMenuForToday();
        //List<String> recipient = new ArrayList<>();
        //for (User user : users){
           // recipient.add(user.getEmail());
            //mealEmailService.sendMenuNotification(recipient,menu);
        //}

    //}
}
