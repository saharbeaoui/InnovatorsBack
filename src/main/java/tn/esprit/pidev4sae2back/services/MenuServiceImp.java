package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Meal;
import tn.esprit.pidev4sae2back.entities.Menu;
import tn.esprit.pidev4sae2back.entities.NutritionInformation;
import tn.esprit.pidev4sae2back.entities.Restaurant;
import tn.esprit.pidev4sae2back.repositories.MealRepository;
import tn.esprit.pidev4sae2back.repositories.MenuRepository;
import tn.esprit.pidev4sae2back.repositories.RestaurantRepository;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class MenuServiceImp implements MenuServiceI{
    MenuRepository menuRepository;
    RestaurantRepository restaurantRepository;
    MealRepository mealRepository;


    @Override
    public List<Menu> retrieveAllMenu() {
        return menuRepository.findAll();
    }

    @Override
    @Transactional
    public Menu addMenu(Menu m) {
        Restaurant restaurant= restaurantRepository.findAll().get(0);
        m.setRestaurant(restaurant);
        menuRepository.save(m);

        Set<Meal> meals= m.getMeals();
        for (Meal meall : meals){
            meall.setMenu(m);
        }

        return m;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }

    @Override
    public Menu retrieveMenu(Long idMenu) {
        return menuRepository.findById(idMenu).get();
    }

    @Override
    public void removeMenu(Long idMenu) {
        menuRepository.deleteById(idMenu);

    }

    @Override
    public void assignMenutoRestaurant(Long idMenu, Long idRestau) {
        Menu menu = menuRepository.findById(idMenu).orElse(null);
        Restaurant restaurant = restaurantRepository.findById(idRestau).orElse(null);
        menu.setRestaurant(restaurant);
        restaurant.getMenus().add(menu);
        restaurantRepository.save(restaurant);
        menuRepository.save(menu);


    }
    public List<Integer> calculateMenuCalories(Long idMenu) {
        Menu menu = menuRepository.findById(idMenu).orElseThrow(() -> new RuntimeException("Menu not found"));
        int totalCalories = 0;
        int totalproteine = 0;
        int totalcarb = 0;
        int totalfat = 0;
        for (Meal meal : menu.getMeals()) {
            totalCalories += meal.getNutritionInformation().getCalories();
            totalproteine += meal.getNutritionInformation().getProtein();
            totalcarb += meal.getNutritionInformation().getCarbohydrates();
            totalfat += meal.getNutritionInformation().getFat();
        }
        List<Integer> nutlist = new ArrayList<Integer>();
        nutlist.add(totalCalories);
        nutlist.add(totalproteine);
        nutlist.add(totalcarb);
        nutlist.add(totalfat);

        return nutlist ;
    }
    public boolean isValidMenu(Long idMenu, int minCalories, int maxCalories) {
        Menu menu = menuRepository.findById(idMenu)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menu ID: " + idMenu));
        Set<Meal> meals = menu.getMeals();
        int totalCalories = 0;
        for (Meal meal : meals) {
            totalCalories += meal.getNutritionInformation().getCalories();
        }
        return totalCalories >= minCalories && totalCalories <= maxCalories;
    }


}
