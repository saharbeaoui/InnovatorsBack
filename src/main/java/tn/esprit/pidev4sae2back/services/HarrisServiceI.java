package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.NutritionInformation;

public interface HarrisServiceI {
    public NutritionInformation calculateDailyNutrition(int weight, int height, int age, String gender);
}
