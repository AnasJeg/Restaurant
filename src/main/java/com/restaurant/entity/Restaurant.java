package com.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private double latitude;
    private double longitude;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate dateCreation;

    @ManyToOne
    private Zone zone;
    @OneToMany(mappedBy = "restaurant",fetch = FetchType.EAGER)
    private List<Repas> repas;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("restaurants")
    private List<Specialite> specialites;

}
