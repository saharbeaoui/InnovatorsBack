package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Meal;
import tn.esprit.pidev4sae2back.entities.Menu;

import java.util.List;
import java.util.Map;

public interface MenuServiceI {
    List<Menu> retrieveAllMenu();

    Menu addMenu (Menu menu);

    Menu updateMenu (Menu menu);

    Menu retrieveMenu(Long idMenu);

    void removeMenu(Long idMenu);
    public void assignMenutoRestaurant (Long idMenu, Long
            idRestau) ;
    public Map<String,Integer> calculateMenuCalories(Long idMenu);

    public boolean isValidMenu(Long idMenu, int minCalories, int maxCalories);


}
