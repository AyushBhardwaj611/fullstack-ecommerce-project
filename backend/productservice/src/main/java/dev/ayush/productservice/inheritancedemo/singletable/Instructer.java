package dev.ayush.productservice.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_instructer")
@DiscriminatorValue("4")
public class Instructer extends User {

    private double AvgRating;
}
