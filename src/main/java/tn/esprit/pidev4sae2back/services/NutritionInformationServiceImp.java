package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.NutritionInformation;
import tn.esprit.pidev4sae2back.repositories.NutritionInformationRepository;

@Service
@AllArgsConstructor
public class NutritionInformationServiceImp implements NutritionInformationServiceI{
    NutritionInformationRepository nutritionInformationRepository;
    @Override
    public void saveNutritionInformation(NutritionInformation nutritionInformation) {
        nutritionInformationRepository.save(nutritionInformation);

    }
}
