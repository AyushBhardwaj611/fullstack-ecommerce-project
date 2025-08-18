package dev.ayush.productservice.inheritancedemo.mappedsupperclass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "msc_student")
public class Student extends User{
    private double psp;
    private String batch_name;
}
