package com.restaurant.Repository;

import com.restaurant.entity.Repas;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepasRepository extends JpaRepository<Repas,Integer> {

}
