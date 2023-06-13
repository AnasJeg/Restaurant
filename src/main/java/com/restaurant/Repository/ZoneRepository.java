package com.restaurant.Repository;

import com.restaurant.entity.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {

    @Query("select z from Zone z where z.ville.nom=?1 order by z.nom")
    List<Zone> findZonesByVille(String nom);
}
