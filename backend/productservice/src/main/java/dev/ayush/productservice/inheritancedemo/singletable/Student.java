package dev.ayush.productservice.inheritancedemo.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "st_student")
@DiscriminatorValue("3")
public class Student extends User {
    private double psp;
    private String batch_name;
}
