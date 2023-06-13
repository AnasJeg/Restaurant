package com.restaurant.Controller;

import com.restaurant.Repository.RestaurantRepository;
import com.restaurant.Repository.SpecialiteRepository;
import com.restaurant.Repository.ZoneRepository;
import com.restaurant.entity.Restaurant;
import com.restaurant.entity.Specialite;
import com.restaurant.entity.Zone;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private SpecialiteRepository specialiteRepository;

    @GetMapping("/zone/all")
    public List<Zone> findALlZones(){
        return zoneRepository.findAll();
    }

    @GetMapping("/restaurant/all")
    public List<Restaurant> findALlRestaurant(){
        return restaurantRepository.findAll();
    }
    @PostMapping("/zone")
    public ResponseEntity<Zone> addZone(@RequestBody Zone zone) {
        try {
            if (zoneRepository.existsById(zone.getId()))
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); //409
            else if (zone.getNom().isEmpty() || zone.getVille() == null || zone.getVille() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //400
            } else {
                zoneRepository.save(zone);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
        return ResponseEntity.status(HttpStatus.CREATED).build(); //201
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant) {
        try {
            if (restaurantRepository.existsById(restaurant.getId()))
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); //409
            else if (restaurant.getNom().isEmpty() || restaurant.getLatitude() == 0 || restaurant.getLongitude() == 0 ||
                    restaurant.getDateCreation() == null || restaurant.getZone() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //400
            } else {
                restaurantRepository.save(restaurant);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // http://localhost:8080/restaurants/proches?latitude=11&longitude=14&limit=1
    @GetMapping("/restaurants/proches")
    public ResponseEntity<List<Restaurant>> restaurantsProche(@PathParam(value = "latitude") Double latitude, @PathParam(value = "longitude") Double longitude, @PathParam(value= "limit") int limit){
        List<Restaurant> restaurantList;
        try{
            restaurantList = restaurantRepository.findByLatitudeAndLongitude(latitude,longitude);
            if(latitude == null || longitude == null ){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); //400
            }else if(restaurantList.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //404
            }
        }catch (Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(restaurantList); // 200
    }

    // http://localhost:8080/restaurants/specialites?specialite=dgrt
    @GetMapping("/restaurants/specialites")
    public ResponseEntity<List<Restaurant>> findRestaurantBySp(@PathParam(value= "specialite") String specialite) {
        List<Restaurant> listRest ;
        try {
            listRest = restaurantRepository.findBySpecialite(specialite);
            if (specialite == null || specialite.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400
            } else if (listRest.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); //404
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
        }
        return ResponseEntity.status(HttpStatus.OK).body(listRest); //200
    }

    @GetMapping("/sp/all")
    public List<Specialite> findAll(){
        return specialiteRepository.findAll();
    }
}
