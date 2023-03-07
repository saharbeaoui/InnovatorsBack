package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.NutritionInformation;

@Service
@AllArgsConstructor
public class HarrisServiceImp implements HarrisServiceI {


    // constants for calculating BMR (Basal Metabolic Rate)
    public static final double MALE_CONSTANT = 5;
    public static final double FEMALE_CONSTANT = -161;
    private static final double WEIGHT_CONSTANT = 10;
    private static final double HEIGHT_CONSTANT = 6.25;
    private static final double AGE_CONSTANT = 5;

    // constants for macronutrient ratios
    private static final double CARB_RATIO = 0.4;
    private static final double PROTEIN_RATIO = 0.3;
    private static final double FAT_RATIO = 0.3;
    private static final int CALORIES_PER_GRAM_CARBS = 4;
    private static final int CALORIES_PER_GRAM_PROTEIN = 4;
    private static final int CALORIES_PER_GRAM_FAT = 9;

    public NutritionInformation calculateDailyNutrition(int weight, int height, int age, String gender) {
        double bmr;
        double genderConstant;

        if (gender.equalsIgnoreCase("male")) {
            genderConstant = MALE_CONSTANT;
        } else if (gender.equalsIgnoreCase("female")) {
            genderConstant = FEMALE_CONSTANT;
        } else {
            throw new IllegalArgumentException("Invalid gender: " + gender);
        }

        // calculate BMR (Basal Metabolic Rate)
        bmr = (WEIGHT_CONSTANT * weight) + (HEIGHT_CONSTANT * height) - (AGE_CONSTANT * age) + genderConstant;

        // calculate macronutrient ratios
        double carbs = CARB_RATIO * bmr / CALORIES_PER_GRAM_CARBS;
        double protein = PROTEIN_RATIO * bmr / CALORIES_PER_GRAM_PROTEIN;
        double fat = FAT_RATIO * bmr / CALORIES_PER_GRAM_FAT;

        // create NutritionInformation object with calculated values
        NutritionInformation nutrition = new NutritionInformation();
        nutrition.setCalories((int) bmr);
        nutrition.setCarbohydrates((int) carbs);
        nutrition.setProtein((int) protein);
        nutrition.setFat((int) fat);

        return nutrition;
    }

}
