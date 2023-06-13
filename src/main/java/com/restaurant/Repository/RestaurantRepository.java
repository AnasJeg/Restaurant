package com.restaurant.Repository;

import com.restaurant.entity.Repas;
import com.restaurant.entity.Restaurant;
import com.restaurant.entity.Ville;
import com.restaurant.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Query("select r from Repas r where r.restaurant = ?1")
    public List<Repas> findByRestaurant(Restaurant restaurant);
    @Query("select r from Restaurant r where r.zone.ville.nom=?1 and r.zone.nom=?2")
    public List<Restaurant> findByVilleZone(String ville, String zone);

    @Query("select r from Restaurant r join Specialite s where s.nom=?1")
    public List<Restaurant> findBySpecialite(String specialite);

    public List<Restaurant> getRestaurantsByDateCreationBefore(LocalDate date);

    public List<Restaurant> findByLatitudeAndLongitude(double latitude, double longitude);


}
