package dev.ayush.productservice.inheritancedemo.tableperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_instructer")
public class Instructer extends User {

    private double AvgRating;
}
