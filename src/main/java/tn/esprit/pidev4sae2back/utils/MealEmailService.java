package tn.esprit.pidev4sae2back.utils;

import tn.esprit.pidev4sae2back.entities.Meal;

import java.util.List;

public interface MealEmailService extends EmailService{
    void sendMealNotification(List<String> recipients, String mealName, String description);
    public void sendMenuNotification(List<String> recipients, List<Meal> menu);

}
