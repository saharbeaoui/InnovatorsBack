package tn.esprit.pidev4sae2back.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.pidev4sae2back.entities.Restaurant;
import tn.esprit.pidev4sae2back.repositories.RestaurantRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RestaurantServiceImp implements RestaurantServiceI{
    RestaurantRepository restaurantRepository;
    @Override
    public List<Restaurant> retrieveAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant addRestaurant(Restaurant r) {
        return restaurantRepository.save(r);
    }

    @Override
    public Restaurant updateRestaurant(Restaurant r) {
        return restaurantRepository.save(r);
    }

    @Override
    public Restaurant retrieveRestaurant(Long idRestau) {
        return restaurantRepository.findById(idRestau).get();
    }

    @Override
    public void archive(Long idRestau) {
        Restaurant restaurant = restaurantRepository.findById(idRestau).get();
        restaurant.archive();
        restaurantRepository.save(restaurant);

    }

    @Override
    public void unarchive(Long idRestau) {
        Restaurant restaurant = restaurantRepository.findById(idRestau).get();
        restaurant.unarchive();
        restaurantRepository.save(restaurant);

    }
}
