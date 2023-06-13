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
    @Query("select r from Repas r join r.restaurant p where p.nom=?1")
    public List<Repas> findByRestaurant(String restaurant);
    @Query("select r from Restaurant r join Ville v join Zone z where v.nom=?1 and z.nom=?2")
    public List<Restaurant> findByVilleZone(String ville, String zone);

    @Query("select r from Restaurant r join Specialite s where s.nom=?1")
    public List<Restaurant> findBySpecialite(String specialite);

    public List<Restaurant> getRestaurantsByDateCreationBefore(LocalDate date);

    public List<Restaurant> findByLatitudeAndLongitude(double latitude, double longitude);


}