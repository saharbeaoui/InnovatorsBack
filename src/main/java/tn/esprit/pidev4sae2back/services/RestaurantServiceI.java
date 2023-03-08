package tn.esprit.pidev4sae2back.services;

import tn.esprit.pidev4sae2back.entities.Restaurant;

import java.util.List;

public interface RestaurantServiceI {
    List<Restaurant> retrieveAllRestaurants();

    Restaurant addRestaurant (Restaurant r);

    Restaurant updateRestaurant (Restaurant r);

    Restaurant retrieveRestaurant(Long idRestau);

    void archive(Long idRestau);

    void unarchive(Long idRestau);
}
