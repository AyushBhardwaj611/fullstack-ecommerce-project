package dev.ayush.productservice.inheritancedemo.mappedsupperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "msc_instructer")
public class Instructer extends User{

    private double AvgRating;
}
