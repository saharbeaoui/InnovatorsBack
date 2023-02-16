package tn.esprit.pidev4sae2back.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev4sae2back.entities.Restaurant;
import tn.esprit.pidev4sae2back.services.RestaurantServiceI;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/restaurant")
public class RestaurantRestcontroller {
    RestaurantServiceI restaurantServiceI;
    @GetMapping("/retrieveAllRestaurants")
    @ResponseBody
    public List<Restaurant> getAllRestaurants(){
        List<Restaurant> listrestaurants = restaurantServiceI.retrieveAllRestaurants();
        return listrestaurants;
    }
    @PostMapping("/addRestaurant")
    @ResponseBody
    public Restaurant addRestaurant(@RequestBody Restaurant r){
        Restaurant restaurant = restaurantServiceI.addRestaurant(r);
        return restaurant;
    }
    @PutMapping("/updateRestaurant")
    @ResponseBody
    public Restaurant updateRestaurant(@RequestBody Restaurant r){
        Restaurant restaurant = restaurantServiceI.updateRestaurant(r);
        return  restaurant;
    }
    @GetMapping("/retrieveRestaurant/{restau-id}")
    @ResponseBody
    public Restaurant getRestaurant(@PathParam("restau-id") Long idRestau){
        Restaurant restaurant = restaurantServiceI.retrieveRestaurant(idRestau);
        return restaurant;
    }
    @PostMapping("/archive/{id-restau}")
    @ResponseBody
    public void archive(@PathParam("id-restau") Long idRestau ){
        restaurantServiceI.archive(idRestau);

    }
    @PostMapping("/unarchive/{id-restau}")
    @ResponseBody
    public void unarchive(@PathParam("id-restau") Long idRestau ){
        restaurantServiceI.unarchive(idRestau);

    }




}
